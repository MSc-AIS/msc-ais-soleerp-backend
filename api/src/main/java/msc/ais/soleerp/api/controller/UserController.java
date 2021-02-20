package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.model.AISUser;
import msc.ais.soleerp.service.ServiceFactory;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Optional;

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
                    .firstName(request.firstname)
                    .lastName(request.lastname)
                    .build())
                .ifPresentOrElse(response -> {
                    ctx.json(response);
                    ctx.status(HttpStatus.CREATED_201);
                }, () -> ctx.status(HttpStatus.BAD_REQUEST_400));
        };

    public static Handler signInUser =
        ctx -> ServiceFactory.createUserService()
            .singIn(
                ctx.queryParam("username"),
                Optional.ofNullable(ctx.queryParam("password"))
                    .orElseThrow(() -> new IllegalArgumentException("Unable to find password query param"))
                    .toCharArray())
            .ifPresentOrElse(response -> {
                ctx.json(response);
                ctx.status(HttpStatus.OK_200);
            }, () -> ctx.status(HttpStatus.NOT_FOUND_404));

    public static Handler signOutUser =
        ctx -> {
            boolean isSingedOut = ServiceFactory.createUserService()
                .signOut(ctx.queryParam("tokenId"));
            if (isSingedOut) {
                ctx.status(HttpStatus.OK_200);
            } else {
                ctx.status(HttpStatus.NOT_FOUND_404);
            }
        };

    static class UserRequest {
        public String firstname;
        public String lastname;
        public String username;
        public String email;
        public String password;
    }

}
