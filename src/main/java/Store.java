import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Store extends Data {

    public static void data(String symbol, String companyName, String lastPrice, String change, String percentChange) {
        System.out.println("Saving data...\n");
        String sql = "INSERT INTO stocks(symbol, companyName, lastPrice, change, percentChange) VALUES(?,?,?,?,?)";
        try (Connection db = Store.connect();
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
