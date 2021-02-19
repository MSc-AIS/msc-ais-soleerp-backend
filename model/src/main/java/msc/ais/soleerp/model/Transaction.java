package msc.ais.soleerp.model;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/1/2021.
 */
public class Transaction {

    private int id;
    private int entityId;
    private String title;
    private int orderNumber;
    private long createdTimestamp;
    private String paymentTerms;
    private double totalPrice;
    private int status;

}
