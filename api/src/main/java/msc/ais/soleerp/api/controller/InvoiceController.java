package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.model.AISInvoice;
import msc.ais.soleerp.service.ServiceFactory;
import org.eclipse.jetty.http.HttpStatus;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 28/2/2021.
 */
public class InvoiceController {

    public static Handler insertInvoice = ctx -> {
        AISInvoice invoice = ctx.bodyValidator(AISInvoice.class).get();
        ServiceFactory.createInvoiceService()
            .insertInvoice(
                ctx.queryParam("tokenId"),
                invoice)
            .ifPresentOrElse(invoiceId -> {
                ctx.json(invoiceId);
                ctx.status(HttpStatus.CREATED_201);
            }, () -> ctx.status(HttpStatus.NOT_FOUND_404));
    };

    public static Handler getInvoices =
        ctx -> ctx.json(ServiceFactory.createInvoiceService()
            .findInvoices(ctx.queryParam("tokenId")));

}
