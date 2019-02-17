import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Data {

    public static Connection connect() {
        String url = "jdbc:sqlite:src/main/db/stock_portfolio.db";
        Connection db = null;
        try {
            db = DriverManager.getConnection(url);
            System.out.println("Connected to database...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return db;
    }
}
