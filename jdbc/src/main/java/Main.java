import jdbs.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = JDBCUtils.getNewConnection()) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
