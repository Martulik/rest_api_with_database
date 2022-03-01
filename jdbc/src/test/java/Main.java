import jdbs.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = JDBCUtils.getNewConnection()) {
            //JDBCUtils.printPeopleList(connection);
            //JDBCUtils.printWithPrepareStatement(connection,0);
            JDBCUtils.changeGroup(connection,5,2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
