package msc.ais.soleerp.service;

import msc.ais.soleerp.db.DaoFactory;
import msc.ais.soleerp.db.TransactionDao;
import msc.ais.soleerp.model.AISTransaction;
import msc.ais.soleerp.model.response.MonthlyIncomeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final UserService userService;
    private final TransactionDao transactionDao;

    public TransactionServiceImpl() {
        this.userService = ServiceFactory.createUserService();
        this.transactionDao = DaoFactory.createTransactionDao();
    }

    @Override
    public List<AISTransaction> findTransactions(String tokenId) {
        return transactionDao.findTransactions(userService.getUserByTokenId(tokenId));
    }

    @Override
    public Optional<AISTransaction> findTransactionById(int id, String tokenId) {
        return transactionDao.findTransactionById(id, userService.getUserByTokenId(tokenId));
    }

    @Override
    public int deleteTransactionById(int id, String tokenId) {
        return transactionDao.deleteTransactionById(id, userService.getUserByTokenId(tokenId));
    }

    @Override
    public int updateTransactionById(int id, String tokenId, AISTransaction transaction) {
        return 0;
    }

    @Override
    public Optional<Double> findLastMonthIncome(String tokenId) {
        return transactionDao.findLastMonthIncome(userService.getUserByTokenId(tokenId));
    }

    @Override
    public List<MonthlyIncomeResponse> findMonthlyIncomes(String tokenId) {
        return transactionDao.findMonthlyIncomes(userService.getUserByTokenId(tokenId));
    }
}
