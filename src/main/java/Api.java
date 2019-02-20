import spark.*;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class Api extends Data {

    private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();

    static {
        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        corsHeaders.put("Access-Control-Allow-Origin", "*");
        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        corsHeaders.put("Access-Control-Allow-Credentials", "true");
    }

    public final static void applyCORSFilter() {
        Filter filter = new Filter() {
            @Override
            public void handle(Request request, Response response) {
                corsHeaders.forEach((key, value) -> {
                    response.header(key, value);
                });
            }
        };
        Spark.after(filter);
    }

    public static void server() {
        String sql = "SELECT timeStamp, symbol, companyName, lastPrice, change, percentChange FROM stocks";
        String json = ResultSetToJson.resultSetToJson(Api.connect(), sql);
        Api.applyCORSFilter();
        get("/api/snapshots", (req, res) -> json);
        System.out.println("JSON:" + json);

        get("/api/snapshots/new", (req, res) -> {
            res.body("Access Denied");
            return 0;
        });

        post("/api/snapshots/new", (request, response) -> {
            Credentials key = new Credentials();
            String loginUrl = key.getLoginUrl();
            String username = key.getUsername();
            String password = key.getPassword();
            Auth.webClient(loginUrl, username, password);
            return "Snapshot generated...";
        });


    }
//
//    public static void generateSnapshot() {
//
//
//    }

}