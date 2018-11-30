package by.training.provider.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);
    private static final int CONNECTION_COUNT = 10;
    private static ConnectionPool instance;
    private static ReentrantLock locker = new ReentrantLock();
    private static boolean isCreated;

    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOG.error("Can't install driver");
            throw new RuntimeException("Can't install driver");
        }
    }

    private BlockingQueue<ProxyConnection> connections = new LinkedBlockingDeque<>();

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
                        DbConstant.DB_URL,
                        DbConstant.DB_USER,
                        DbConstant.DB_PASSWORD));

                addConnection(connection);
            } catch (SQLException e) {
                LOG.error("Can't create connection", e);
                throw new RuntimeException(e);
            }
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOG.error("", e);
        }
        return connection;
    }

    void addConnection(ProxyConnection connection) {
        try {
            this.connections.put(connection);
        } catch (InterruptedException e) {
            LOG.error("", e);
        }
    }

    public void destroyConnections() {
        try {
            for (int i = 0; i < CONNECTION_COUNT; ++i) {
                ProxyConnection connection = connections.take();
                connection.realClose();
            }
        } catch (SQLException | InterruptedException e) {
            LOG.error("", e);
        }
        DriverManager.drivers().forEach(x -> {
            try {
                DriverManager.deregisterDriver(x);
            } catch (SQLException e) {
                LOG.error("", e);
            }
        });
    }
}
