package msc.ais.soleerp.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import io.javalin.plugin.json.JavalinJackson;
import msc.ais.soleerp.api.conf.ServerConfig;

public class Application {

    public static void main(String[] args) {

        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins)
            .start(ServerConfig.getPort());

        final String baseURL = "/msc/ais/weather/api/";

        app.get(baseURL, ctx -> ctx.result("Server Is Up and Running..."));
        // app.get(baseURL + "/forecast/current", ForecastController.getCurrentWeatherForecastResponseByIP);


        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavalinJackson.configure(mapper);
    }
}
