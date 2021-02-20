package msc.ais.soleerp.db.util;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class StoreMetadata {

    private final StoreResult storeResult;
    private final int autoGenId;

    private StoreMetadata(int autoGenId, StoreResult storeResult) {
        this.autoGenId = autoGenId;
        this.storeResult = storeResult;
    }

    public static StoreMetadata of(int autoGenId, StoreResult storeResult) {
        return new StoreMetadata(autoGenId, storeResult);
    }

    public StoreResult getStoreResult() {
        return storeResult;
    }

    public int getAutoGenId() {
        return autoGenId;
    }
}
