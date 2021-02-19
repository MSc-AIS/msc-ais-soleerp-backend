package msc.ais.soleerp.service;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public class ServiceFactory {

    public static UserService createUserService() {
        return new UserServiceImpl();
    }

}
