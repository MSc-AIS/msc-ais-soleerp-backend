package sole.erp.backend.model;

import java.util.List;
import java.util.Map;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public abstract class Invoice {

    protected String title;
    protected String row;
    protected int number;
    protected String customerOrderNumber;
    protected NaturalEntity issuer;
    protected LegalEntity customer;
    protected LegalEntity supplier;
    protected long issueTimestamp;
    protected List<Item> itemList;
    protected Map<Integer, Double> discountList;

    abstract void addItem(Item item);

}
