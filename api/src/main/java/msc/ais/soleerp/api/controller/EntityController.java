package msc.ais.soleerp.api.controller;

import io.javalin.http.Handler;
import msc.ais.soleerp.db.jooq.generated.tables.records.EntityRecord;
import msc.ais.soleerp.service.ServiceFactory;
import org.eclipse.jetty.http.HttpStatus;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 21/2/21.
 */
public class EntityController {

    public static Handler getEntitiesByUserTokenId =
        ctx -> ctx.json(ServiceFactory.createEntityService()
            .findEntitiesByTokenId(ctx.queryParam("tokenId")));

    public static Handler getEntityById =
        ctx -> ServiceFactory.createEntityService()
            .findEntityById(
                ctx.pathParam("id", Integer.class).get(),
                ctx.queryParam("tokenId"))
            .ifPresentOrElse(response -> {
                ctx.json(response);
                ctx.status(HttpStatus.OK_200);
            }, () -> ctx.status(HttpStatus.NOT_FOUND_404));

    public static Handler deleteEntityById =
        ctx -> {
            boolean isDeleted = ServiceFactory.createEntityService()
                .deleteEntityById(
                    ctx.pathParam("id", Integer.class).get(),
                    ctx.queryParam("tokenId"));

            if (isDeleted) {
                ctx.status(HttpStatus.OK_200);
            } else {
                ctx.status(HttpStatus.NOT_FOUND_404);
            }
        };

    public static Handler insertEntityById = ctx -> {
        Entity entity = ctx.bodyValidator(Entity.class).get();

        EntityRecord record = new EntityRecord();
        record.setName(entity.name);
        record.setRole(entity.role);
        record.setTaxId(entity.taxId);
        record.setTaxOfficeCode(entity.taxOfficeCode);
        record.setStreet(entity.street);
        record.setStreetNo(entity.streetNumber);
        record.setZipCode(entity.zipCode);
        record.setCity(entity.city);
        record.setArea(entity.area);
        record.setCountryCode(entity.countryCode);
        record.setPhone(entity.phone);
        record.setWebsite(entity.website);
        record.setActivity(entity.activity);
        record.setCompanyId(entity.companyId);
        record.setEmail(entity.email);

        // record.from(entity);


        int entityId = ServiceFactory.createEntityService()
            .insertEntity(
                ctx.queryParam("tokenId"),
                record);

        if (entityId != -1) {
            ctx.status(HttpStatus.CREATED_201);
            ctx.json(entityId);
        } else {
            ctx.status(HttpStatus.NOT_FOUND_404);
        }
    };

    public static Handler updateEntityById =
        ctx -> {
            Entity entity = ctx.bodyValidator(Entity.class).get();

            EntityRecord record = new EntityRecord();
            record.setName(entity.name);
            record.setRole(entity.role);
            record.setTaxId(entity.taxId);
            record.setTaxOfficeCode(entity.taxOfficeCode);
            record.setStreet(entity.street);
            record.setStreetNo(entity.streetNumber);
            record.setZipCode(entity.zipCode);
            record.setCity(entity.city);
            record.setArea(entity.area);
            record.setCountryCode(entity.countryCode);
            record.setPhone(entity.phone);
            record.setWebsite(entity.website);
            record.setActivity(entity.activity);
            record.setCompanyId(entity.companyId);
            record.setEmail(entity.email);

            // record.from(entity);


            int rowsUpdated = ServiceFactory.createEntityService()
                .updateEntityById(
                    ctx.pathParam("id", Integer.class).get(),
                    ctx.queryParam("tokenId"),
                    record);

            if (rowsUpdated == 1) {
                ctx.status(HttpStatus.OK_200);
            } else {
                ctx.status(HttpStatus.NOT_FOUND_404);
            }
        };

    static class Entity {
        // public Integer id;
        public String name;
        // public String companyFlag;
        public String role;
        public Long taxId;
        public String taxOfficeCode;
        public String street;
        public String streetNumber;
        public String zipCode;
        public String city;
        public String area;
        public String countryCode;
        public String phone;
        public String website;
        // public Integer userId;
        public String activity;
        public Integer companyId;
        public String email;
    }

}
