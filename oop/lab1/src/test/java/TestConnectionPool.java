import com.nastichichika.utils.ConnectionPoll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestConnectionPool {
    @Test
    void whenCalledgetConnection_thenCorrect() throws SQLException {
        ConnectionPoll connectionPool = null;

        connectionPool = ConnectionPoll
                    .create("jdbc:postgresql://127.0.0.1:5432/telephone_company", "postgres", "456789");


        assertTrue(connectionPool.getConnection().isValid(1));
    }
}
