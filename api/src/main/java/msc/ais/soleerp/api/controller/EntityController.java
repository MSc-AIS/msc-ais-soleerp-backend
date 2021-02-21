package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.service.ServiceFactory;
import msc.ais.soleerp.service.exception.ServiceException;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class EntityController {

    public static Handler getEntitiesByUserTokenId =
        ctx -> ctx.json(ServiceFactory.createEntityService()
            .findEntitiesByTokenId(ctx.queryParam("tokenId")));

}
