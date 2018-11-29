package by.training.provider;

import by.training.provider.connection.ConnectionPool;
import by.training.provider.connection.ProxyConnection;
import by.training.provider.encrypt.Encrypt;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            System.out.println("--------------");
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ConnectionPool.getInstance().destroyConnections();
    }
}
