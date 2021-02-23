package msc.ais.soleerp.db.postgres;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.ReferenceCodesDao;
import msc.ais.soleerp.db.jooq.generated.tables.records.ReferenceCodesRecord;
import msc.ais.soleerp.model.codelists.CodelistsOfBiMap;
import msc.ais.soleerp.model.enums.CountryCode;
import msc.ais.soleerp.model.internal.AISCountry;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 23/2/2021.
 */
public class PostgresReferenceCodesDaoTest {

    @Disabled
    @Test
    public void testInsertCountries() {

        final List<AISCountry> countryList = Arrays.stream(CountryCode.values())
            .map(countryCode -> AISCountry.builder()
                .countryCode(countryCode.name())
                .countryName(CodelistsOfBiMap.COUNTRY_CODE_MAP.getValueForId(countryCode.name()))
                .build())
            .collect(Collectors.toList());

        final List<ReferenceCodesRecord> recordList = new ArrayList<>(countryList.size());
        for (AISCountry aisCountry : countryList) {

            if (aisCountry.getCountryCode().equals("GR")
                || aisCountry.getCountryCode().equals("CY")) {
                continue;
            }

            ReferenceCodesRecord record = new ReferenceCodesRecord();
            record.setRefValue(aisCountry.getCountryCode());
            record.setRefMeaning(aisCountry.getCountryName());
            record.setRefDomain("country_code");
            // record.setRefAbbreviation()
            recordList.add(record);
        }

        ReferenceCodesDao referenceCodesDao = DaoFactory.createReferenceCodesDao();
        int[] rows = referenceCodesDao.insertCountries(recordList);
        System.out.println("rows inserted: " + Arrays.toString(rows));
    }

}
