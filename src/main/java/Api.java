import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.get;

public class Api {
    public static void api() {
        get(new Route("/api/snapshot/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return  "User: username=test, email=test@test.net";
            }
        });
    }
}