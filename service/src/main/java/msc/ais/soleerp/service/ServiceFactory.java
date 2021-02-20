package msc.ais.soleerp.service;

import msc.ais.soleerp.service.transofmration.TransformationService;
import msc.ais.soleerp.service.transofmration.TransformationServiceImpl;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public class ServiceFactory {

    public static UserService createUserService() {
        return new UserServiceImpl();
    }

    public static TransformationService createTransformationService() {
        return new TransformationServiceImpl();
    }

}
