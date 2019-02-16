import com.google.gson.Gson;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class Api {

    public static void api(String json) {
        get(new Route("/api/snapshot/:id") {
            @Override
            public Object handle(Request request, Response response) {
                return  json;
            }
        });
    }

}