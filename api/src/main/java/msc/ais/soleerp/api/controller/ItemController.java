package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.model.AISItem;
import msc.ais.soleerp.service.ServiceFactory;
import org.eclipse.jetty.http.HttpStatus;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 26/2/2021.
 */
public class ItemController {

    public static Handler insertItem = ctx -> {
        AISItem item = ctx.bodyValidator(AISItem.class).get();
        int itemId = ServiceFactory.createItemService()
            .insertItem(
                ctx.queryParam("tokenId"),
                item);

        if (itemId != -1) {
            ctx.status(HttpStatus.CREATED_201);
            ctx.json(itemId);
        } else {
            ctx.status(HttpStatus.NOT_FOUND_404);
        }
    };

    public static Handler getItems =
        ctx -> ctx.json(ServiceFactory.createItemService()
            .findItems(ctx.queryParam("tokenId")));


    public static Handler deleteItemById =
        ctx -> {
            int rowsDeleted = ServiceFactory.createItemService()
                .deleteItemById(
                    ctx.pathParam("id", Integer.class).get(),
                    ctx.queryParam("tokenId"));

            if (rowsDeleted > 0) {
                ctx.status(HttpStatus.OK_200);
            } else {
                ctx.status(HttpStatus.NOT_FOUND_404);
            }
        };

    public static Handler updateItemById =
        ctx -> {
            AISItem item = ctx.bodyValidator(AISItem.class).get();
            int rowsUpdated = ServiceFactory.createItemService()
                .updatedItemById(
                    ctx.pathParam("id", Integer.class).get(),
                    ctx.queryParam("tokenId"),
                    item);

            if (rowsUpdated == 1) {
                ctx.status(HttpStatus.OK_200);
            } else {
                ctx.status(HttpStatus.NOT_FOUND_404);
            }
        };
}
