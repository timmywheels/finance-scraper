import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Fetch extends Data {

    public static void data() {
        System.out.println("Saving data...\n");
        String sql = "SELECT timeStamp, symbol, companyName, lastPrice, change, percentChange FROM stocks";
        try (Connection db = Fetch.connect();
             Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
