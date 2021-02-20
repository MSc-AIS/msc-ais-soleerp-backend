package msc.ais.soleerp.db.exception;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class DataException extends Exception {

    public DataException() {
    }

    public DataException(String mes) {
        super(mes);
    }

    public DataException(String mes, Throwable t) {
        super(mes, t);
    }

    public DataException(Throwable t) {
        super(t);
    }

}
