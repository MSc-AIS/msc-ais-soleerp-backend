package msc.ais.soleerp.model;

import java.time.LocalDate;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/1/2021.
 */
public class PriceList {

    private int id;
    private int entityId;
    private int userId;
    private String description;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
    private int status;
    private LocalDate activityUtil;

}
