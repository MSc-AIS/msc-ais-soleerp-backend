package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.InvoiceDao;
import msc.ais.soleerp.db.TransactionItemDao;
import msc.ais.soleerp.db.TransactionDao;
import msc.ais.soleerp.db.util.StoreResult;
import msc.ais.soleerp.model.AISInvoice;
import msc.ais.soleerp.model.AISTransaction;
import msc.ais.soleerp.service.exception.ServiceRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 28/2/2021.
 */
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);
    private final UserService userService;
    private final TransactionDao transactionDao;
    private final EntityService entityService;
    private final InvoiceDao invoiceDao;
    private final TransactionItemDao transactionItemDao;

    public InvoiceServiceImpl() {
        this.userService = ServiceFactory.createUserService();
        this.transactionDao = DaoFactory.createTransactionDao();
        this.entityService = ServiceFactory.createEntityService();
        this.invoiceDao = DaoFactory.createInvoiceDao();
        this.transactionItemDao = DaoFactory.createTransactionItemDao();
    }

    @Override
    public Optional<String> insertInvoice(String tokenId, AISInvoice invoice) {

        String invoiceId;
        AISTransaction transaction = invoice.getTransaction();
        int userId = userService.getUserByTokenId(tokenId);

        if (entityService.isEntityBelongToUser(transaction.getEntityId(), userId)) {

            // insert the transaction
            transaction = transactionDao.insertTransaction(transaction)
                .orElseThrow(() -> new NoSuchElementException(
                    "Error... Unable to retrieve inserted transaction."));

            // insert the items of the transaction
            StoreResult storeResult = transactionItemDao.insertTransactionItems(
                transaction.getId(), invoice.getTransaction().getTransactionItemList());

            if (storeResult != StoreResult.SUCCESS) {
                throw new ServiceRuntimeException(
                    "Error... Unable to insert all transaction items to db.");
            }

            // insert the invoice
            invoiceId = invoiceDao.insertInvoice(userId, transaction.getId(), invoice)
                .orElseThrow(() -> new NoSuchElementException(
                    "Error... Unable to retrieve invoice id."));

        } else {
            throw new ServiceRuntimeException("Error... Unauthorized access. Entity with id: "
                + transaction.getEntityId() + " does not belong to user with id: "
                + userId);
        }

        return Optional.ofNullable(invoiceId);
    }

    @Override
    public List<AISInvoice> findInvoices(String tokenId) {
        return invoiceDao.findInvoices(userService.getUserByTokenId(tokenId));
    }
}
