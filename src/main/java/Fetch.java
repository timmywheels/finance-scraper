import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Fetch extends Data {

    public static void data() {

        System.out.println("Saving data...\n");
        String sql = "SELECT symbol, companyName, lastPrice, change, percentChange FROM stocks";

        try (Connection db = Fetch.connect();
             Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println("==========");
                System.out.println(rs.getString("symbol"));
                System.out.println(rs.getString("companyName"));
                System.out.println("$" + rs.getString("lastPrice"));
                System.out.println(rs.getString("change"));
                System.out.println(rs.getString("percentChange"));
                System.out.println("==========");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
