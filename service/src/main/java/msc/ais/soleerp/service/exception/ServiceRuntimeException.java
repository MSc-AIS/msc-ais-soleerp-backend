package msc.ais.soleerp.service.exception;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 24/2/2021.
 */
public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException() {
    }

    public ServiceRuntimeException(String mes) {
        super(mes);
    }

    public ServiceRuntimeException(String mes, Throwable t) {
        super(mes, t);
    }

    public ServiceRuntimeException(Throwable t) {
        super(t);
    }
}
