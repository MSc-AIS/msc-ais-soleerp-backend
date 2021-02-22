package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.service.ServiceFactory;
import org.eclipse.jetty.http.HttpStatus;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class EntityController {

    public static Handler getEntitiesByUserTokenId =
        ctx -> ctx.json(ServiceFactory.createEntityService()
            .findEntitiesByTokenId(ctx.queryParam("tokenId")));

    public static Handler getEntityById =
        ctx -> ServiceFactory.createEntityService()
            .findEntityById(
                ctx.pathParam("id", Integer.class).get(),
                ctx.queryParam("tokenId"))
            .ifPresentOrElse(response -> {
                ctx.json(response);
                ctx.status(HttpStatus.OK_200);
            }, () -> ctx.status(HttpStatus.NOT_FOUND_404));

}
