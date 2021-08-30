package src.com.util;
import java.sql.*;
import java.util.Properties;

public class db_manage {


    public void testing() {
        System.out.println("DB Manage called. ");
    }

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = System.getenv("aws_db_endpoint");
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
    /*
    public static void test(){

        System.out.println("MySQL connect example.");
        String formattedURL = "jdbc:mysql://" + DATABASE_URL;

        Statement stmt = null;
        ResultSet rs = null;

        try {
        // 1. Get a connection to database
        Class.forName(DATABASE_DRIVER);
        Connection connection = DriverManager.getConnection(formattedURL, USERNAME, PASSWORD); 
        // 2. Create a statement

        stmt = connection.createStatement();
        System.out.println("Connected!");
        // 3. Execute SQL query
        rs = stmt.executeQuery("SELECT * FROM marketdata.sp500");

        // 4. Process the result set
        ResultSetMetaData rsmd = rs.getMetaData();

        // 4.a Get Number of cols, needed for .getColumnName() argument
        int nbCols = rsmd.getColumnCount();

        // 4b. Get the column names
        for (int i = 1; i <= nbCols; i++) {
            System.out.println(rsmd.getColumnName(i));
        }

        // 4c. Printing the values
        while (rs.next()) {
            for (int i = 1; i <= nbCols; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
        } catch(ClassNotFoundException | SQLException e)
        {
            System.err.println("SQL exception: " + e.getMessage());
            System.exit(1);
        }
    */
       
 


