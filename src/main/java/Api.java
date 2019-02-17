import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;

public class Api extends Auth {

    public static void api(String json) {
        get(new Route("/api/snapshot/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return  json;
            }
        });
    }

    String sql = "SELECT symbol, companyName, lastPrice, change, percentChange FROM stocks";



}