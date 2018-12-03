package by.training.provider.pool;

import org.testng.annotations.Test;

public class ConnectionPoolTest {

    @Test
    public void destroyTest(){
        ConnectionPool.getInstance().destroyConnections();
    }
}
