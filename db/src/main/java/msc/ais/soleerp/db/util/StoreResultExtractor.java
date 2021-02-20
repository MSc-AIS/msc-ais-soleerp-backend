package msc.ais.soleerp.db.util;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public interface StoreResultExtractor {

    default StoreResult extractStoreResult(int storeResult) {

        switch (storeResult) {

            case 0:
                return StoreResult.UNNECESSARY;

            case 1:
                return StoreResult.SUCCESS;

            default:
                return StoreResult.FAILURE;

        }
    }

    default StoreMetadata extractStoreMetadata(int storeResult, int autoGenId) {
        return StoreMetadata.of(autoGenId, extractStoreResult(storeResult));
    }

}
