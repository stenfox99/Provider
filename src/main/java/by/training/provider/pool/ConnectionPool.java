package by.training.provider.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);
    private static final int CONNECTION_COUNT = 10;
    private static ConnectionPool instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static boolean isCreated;

    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOG.fatal("Can't install driver");
            throw new RuntimeException("Can't install driver");
        }
    }

    private BlockingQueue<ProxyConnection> allConnections = new LinkedBlockingDeque<>();

    private ConnectionPool() {
        init();
    }

    public static ConnectionPool getInstance() {
        if (!isCreated) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated = true;
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    private void init() {
        for (int i = 0; i < CONNECTION_COUNT; ++i) {
            try {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(
                        DbParameter.DB_URL,
                        DbParameter.DB_USER,
                        DbParameter.DB_PASSWORD));

                addConnection(connection);
            } catch (SQLException e) {
                LOG.fatal("Can't create pool", e);
                throw new RuntimeException(e);
            }
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = allConnections.take();
        } catch (InterruptedException e) {
            LOG.error("", e);
        }
        return connection;
    }

    void addConnection(ProxyConnection connection) {
        try {
            allConnections.put(connection);
        } catch (InterruptedException e) {
            LOG.error("Can't add connection", e);
        }
    }

    public void destroyConnections() {
        try {
            for (int i = 0; i < CONNECTION_COUNT; ++i) {
                ProxyConnection connection = allConnections.take();
                connection.realClose();
            }
        } catch (SQLException | InterruptedException e) {
            LOG.error("Can't close connection", e);
        }
        DriverManager.drivers().forEach(x -> {
            try {
                DriverManager.deregisterDriver(x);
            } catch (SQLException e) {
                LOG.error("Can't deregister driver", e);
            }
        });
    }
}
