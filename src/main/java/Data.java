import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Data {

    private Connection connect(){
        String url = "jdbc:sqlite:src/main/db/stock_portfolio.db";
        Connection db = null;

        try {
            db = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return db;

    }

    public void insert(String symbol, String companyName, String lastPrice, String change, String percentChange) {
        System.out.println("Saving data...");
        String sql = "INSERT INTO stocks(symbol, companyName, lastPrice, change, percentChange) VALUES(?,?,?,?,?)";
        try (Connection db = this.connect();
             PreparedStatement pstmt = db.prepareStatement(sql)) {
                pstmt.setString(1, symbol);
                pstmt.setString(2, companyName);
                pstmt.setString(3, lastPrice);
                pstmt.setString(4, change);
                pstmt.setString(5, percentChange);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
