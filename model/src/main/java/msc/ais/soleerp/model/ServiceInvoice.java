package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public class ServiceInvoice extends Invoice {

    @Override
    public void addItem(Item item) {
        if (item.getType() != ItemType.SERVICE) {
            throw new IllegalArgumentException("Message here");
        }
        itemList.add(item);
    }
}