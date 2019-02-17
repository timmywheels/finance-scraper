import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;

public class Api extends Data {


    public static String server() {
        String sql = "SELECT timeStamp, symbol, companyName, lastPrice, change, percentChange FROM stocks";
        String json = ResultSetToJson.resultSetToJson(Api.connect(), sql);
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