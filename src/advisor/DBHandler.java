package advisor;

import java.sql.*;

public class DBHandler {

    private final static String DBPATH = "C:\\Users\\PC\\Desktop\\Advisor.db";
    public static Connection connection;

    public static void init() throws SQLException {
        String url = "jdbc:sqlite:" + DBPATH;
        Connection conn = DriverManager.getConnection(url);
        connection = conn;
    }
}
