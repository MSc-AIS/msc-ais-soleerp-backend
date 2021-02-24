package msc.ais.soleerp.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import io.javalin.plugin.json.JavalinJackson;
import msc.ais.soleerp.api.conf.ServerConfig;
import msc.ais.soleerp.api.controller.EntityController;
import msc.ais.soleerp.api.controller.UserController;

public class Application {

    public static void main(String[] args) {

        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins)
            .start(ServerConfig.getPort());

        final String baseURL = "/msc/ais/soleerp/api/";

        app.get(baseURL, ctx -> ctx.result("Server Is Up and Running..."));

        app.post(baseURL + "/user/signup", UserController.signUpUser);
        app.get(baseURL + "/user/signin", UserController.signInUser);
        app.delete(baseURL + "/user/signout", UserController.signOutUser);

        app.get(baseURL + "/entities", EntityController.getEntitiesByUserTokenId);
        app.get(baseURL + "/entity/id/:id", EntityController.getEntityById);
        app.delete(baseURL + "/entity/id/:id", EntityController.deleteEntityById);
        app.put(baseURL + "/entity/id/:id", EntityController.updateEntityById);
        app.post(baseURL + "/entity/id/:id", EntityController.insertEntityById);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.findAndRegisterModules();
        JavalinJackson.configure(mapper);
    }
}
