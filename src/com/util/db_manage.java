package src.com.util;
import java.sql.*;
import java.util.Properties;

public class db_manage {

    public void testing() {
        System.out.println("DB Manage called. ");
    }

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = System.getenv("aws_db_endpoint_java");
    private static final String USERNAME = System.getenv("aws_db_user");
    private static final String PASSWORD = System.getenv("aws_db_pass");

    // init connection object
    private Connection connection;

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}