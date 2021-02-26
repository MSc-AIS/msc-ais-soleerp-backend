package msc.ais.soleerp.model;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public abstract class Invoice {

    protected String id;
    protected Transaction transaction;
    protected AISUser user;
    protected String row;
    protected String notes;

    protected List<AISItem> itemList;

    public abstract void addItem(AISItem item);

}
