package md.tekwill.jdbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    public static Connection createConnection() {
        Connection conn = null;
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1522/XEPDB1", "hr", "hr");

//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@localhost:1522/XEPDB1", "hr", "hr");

//            Class.forName("org.postgresql.Driver");
//            conn = DriverManager.getConnection(
//                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

            if (conn != null) {
                System.out.println("Connected to the DB!");
            } else {
                System.out.println("Failed to connection!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
