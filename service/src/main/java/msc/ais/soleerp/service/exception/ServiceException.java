package msc.ais.soleerp.service.exception;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class ServiceException extends Exception {

    public ServiceException() {
    }

    public ServiceException(String mes) {
        super(mes);
    }

    public ServiceException(String mes, Throwable t) {
        super(mes, t);
    }

    public ServiceException(Throwable t) {
        super(t);
    }

}
