package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.service.ServiceFactory;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public class TransactionController {

    public static Handler getTransactions =
        ctx -> ctx.json(ServiceFactory.createTransactionService()
            .findTransactions(ctx.queryParam("tokenId")));

}
