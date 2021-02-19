package msc.ais.soleerp.model;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 17/1/2021.
 */
public abstract class Entity {

    protected int id;
    protected String name;
    protected EntityRole role;
    protected String taxId;
    protected TaxOffice taxOffice;
    protected Address address;
    protected String phoneNumber;
    protected String email;
    protected String website;
    /**
     * Entity's main activity description
     */
    protected final String activity;
    protected List<BankAccount> bankAccountList;

    protected Entity(String activity) {
        this.activity = activity;
    }

}
