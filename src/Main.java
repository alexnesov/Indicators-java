import src.com.util.db_manage;
import java.sql.*;

public class Main {
    
    // Compile: javac -d out src/Main.java
    // Run: java -cp ./out:/usr/share/java/mysql-connector-java-8.0.25.jar Main

    public static void main(String[] args) {
        System.out.println("Main called. ");
        
    
        db_manage mysqlConnect = new db_manage();
        mysqlConnect.testing();

        String sql = "SELECT * FROM marketdata.sp500";

        try {
            Statement stmt = null;
            ResultSet rs = null;
            
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            rs = statement.executeQuery();
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }
    
}

