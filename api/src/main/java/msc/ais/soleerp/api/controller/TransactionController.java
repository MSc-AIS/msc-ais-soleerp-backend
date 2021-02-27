package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.service.ServiceFactory;
import org.eclipse.jetty.http.HttpStatus;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public class TransactionController {

    public static Handler getTransactions =
        ctx -> ctx.json(ServiceFactory.createTransactionService()
            .findTransactions(ctx.queryParam("tokenId")));


    public static Handler getTransactionById =
        ctx -> ServiceFactory.createTransactionService()
            .findTransactionById(
                ctx.pathParam("id", Integer.class).get(),
                ctx.queryParam("tokenId"))
            .ifPresentOrElse(response -> {
                ctx.json(response);
                ctx.status(HttpStatus.OK_200);
            }, () -> ctx.status(HttpStatus.NOT_FOUND_404));
}
