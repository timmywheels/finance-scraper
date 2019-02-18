import java.sql.*;

public class Store extends Data {

    public static void data(Timestamp timeStamp, String symbol, String companyName, String lastPrice, String change, String percentChange) {
        System.out.println("Saving data...\n");
        String sql = "INSERT INTO stocks(timeStamp, symbol, companyName, lastPrice, change, percentChange) VALUES(?,?,?,?,?,?)";
        try (Connection db = Store.connect();
             PreparedStatement pstmt = db.prepareStatement(sql)) {
                pstmt.setTimestamp(1, timeStamp);
                pstmt.setString(2, symbol);
                pstmt.setString(3, companyName);
                pstmt.setString(4, lastPrice);
                pstmt.setString(5, change);
                pstmt.setString(6, percentChange);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
