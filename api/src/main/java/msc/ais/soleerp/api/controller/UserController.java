package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.model.AISUser;
import msc.ais.soleerp.service.ServiceFactory;
import org.eclipse.jetty.http.HttpStatus;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/21.
 */
public class UserController {

    public static Handler signUpUser =
        ctx -> {
            UserRequest request = ctx.bodyValidator(UserRequest.class).get();
            ServiceFactory.createUserService()
                .signUp(AISUser.builder()
                    .username(request.username)
                    .email(request.email)
                    .password(request.password.toCharArray())
                    .build())
                .ifPresentOrElse(tokenId -> {
                    ctx.json(tokenId);
                    ctx.status(HttpStatus.CREATED_201);
                }, () -> ctx.status(HttpStatus.BAD_REQUEST_400));
        };

    static class UserRequest {
        public String username;
        public String email;
        public String password;
    }

}
