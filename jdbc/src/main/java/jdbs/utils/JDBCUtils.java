package jdbs.utils;

import java.sql.*;

public class JDBCUtils {
    public static Connection getNewConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/db_office";
        String name = "martha";
        String password = "password";
        Connection connection = DriverManager.getConnection(dbURL, name, password);
        return connection;
    }

    public static void printPeopleList(Connection connection) throws SQLException {
        String sql = "select * FROM people";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                String people_first_name = rs.getString("first_name");
                int group_id = rs.getInt("group_id");
                int id = rs.getInt("id");
                System.out.println(id + ": " + people_first_name + ": " + group_id);
            }
        }
    }

    public static void printWithPrepareStatement(Connection connection, int id) throws SQLException {
        String sql = "select * FROM people where group_id > ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String people_first_name = rs.getString("first_name");
                    int group_id = rs.getInt("group_id");
                    int id_people = rs.getInt("id");
                    System.out.println(id_people + ": " + people_first_name + ": " + group_id);
                }
            }
        }
    }

    public static void changeGroup(Connection connection, int student_id, int group_id) throws SQLException {
        String sql = "update people set people.group_id = ? where people.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, group_id);
            statement.setInt(2, student_id);
            statement.executeUpdate();
        }
    }

    public static int addGroup(Connection connection, String name) throws SQLException {
        int id = getGroupId(connection, name);
        if (id == -1) {
            String insQuery = "insert into `member`(name) values (?)";
            try (PreparedStatement statement = connection.prepareStatement(insQuery)) {
                statement.setString(1, name);
                statement.executeUpdate();
                return getGroupId(connection, name);
            }
        }
        return id;
    }

    private static int getGroupId(Connection connection, String name) throws SQLException {
        String sql = "select * from `member` where name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        }
    }

    public static void addPeople(Connection connection,
                                 String first_name,
                                 String last_name,
                                 String pather_name, int group_id,
                                 String type) throws SQLException {
        String sql = "insert into people (first_name, last_name, pather_name, group_id, type) " +
                "values (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, pather_name);
            statement.setInt(4, group_id);
            statement.setString(5, type);
            statement.executeUpdate();
        }
    }

    private static String getRandomPeople(Connection connection) throws SQLException {
        String sql = "select * from people order by rand()"; // limit 1
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("last_name");
                } else {
                    return "no people";
                }
            }
        }
    }

    
}
