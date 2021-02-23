package msc.ais.soleerp.db;

import msc.ais.soleerp.db.jooq.generated.tables.records.ReferenceCodesRecord;
import msc.ais.soleerp.model.internal.AISCountry;

import java.util.List;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 23/2/2021.
 */
public interface ReferenceCodesDao {

    int[] insertCountries(List<ReferenceCodesRecord> recordList);

    List<AISCountry> findCountries();

}
