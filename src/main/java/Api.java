import spark.*;

import java.util.HashMap;

import static spark.Spark.get;

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

    public static String server() {
        String sql = "SELECT timeStamp, symbol, companyName, lastPrice, change, percentChange FROM stocks";
        String json = ResultSetToJson.resultSetToJson(Api.connect(), sql);
        Api.applyCORSFilter();
        get(new Route("/api/snapshot/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return json;
            }
        });
        System.out.println("JSON:" + json);
        return json;
    }
}