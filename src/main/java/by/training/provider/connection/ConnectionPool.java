package by.training.provider.connection;

import by.training.provider.exception.BusinessLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {

    private static final Logger LOG;
    private static ConnectionPool instance;
    private static ReentrantLock locker;
    private static boolean isCreated;
    private static final int CONNECTION_COUNT = 10;
    private BlockingQueue<ProxyConnection> connections;

    static {
        LOG = LoggerFactory.getLogger(ConnectionPool.class);
        try {
            Class.forName(DBConstant.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
    }

    private ConnectionPool() {
        connections = new LinkedBlockingDeque<>();
        locker = new ReentrantLock();
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

    private void init(){
        for (int i = 0; i < CONNECTION_COUNT; ++i){
            try {
                addConnection(new ProxyConnection(DriverManager.getConnection(
                        DBConstant.DB_URL,
                        DBConstant.DB_USER,
                        DBConstant.DB_PASSWORD)));
            } catch (BusinessLogicException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ProxyConnection getConnection() throws BusinessLogicException {
        ProxyConnection connection = null;
        locker.lock();
        try {
            while (connection == null) {
                try {
                    if (this.connections.isEmpty()) {
                        connection = new ProxyConnection(DriverManager.getConnection(
                                DBConstant.DB_URL,
                                DBConstant.DB_USER,
                                DBConstant.DB_PASSWORD));
                    } else {
                        connection = this.connections.take();
                        if (!connection.isValid(0)) {
                            connection = null;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new BusinessLogicException(e.getMessage());
                } catch (SQLException e) {
                    throw new BusinessLogicException(e.getMessage());
                }
            }
        } finally {
            locker.unlock();
        }
        return connection;
    }

    void addConnection(final ProxyConnection connection) throws BusinessLogicException {
        try {
            this.connections.put(connection);
        } catch (InterruptedException e) {
            throw new BusinessLogicException(e.getMessage());
        }
    }

}
