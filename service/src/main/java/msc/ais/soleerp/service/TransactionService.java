package msc.ais.soleerp.service;

import msc.ais.soleerp.model.AISTransaction;
import msc.ais.soleerp.model.response.MonthlyIncomeResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 27/2/2021.
 */
public interface TransactionService {

    List<AISTransaction> findTransactions(String tokenId);

    Optional<AISTransaction> findTransactionById(int id, String tokenId);

    int deleteTransactionById(int id, String tokenId);

    int updateTransactionById(int id, String tokenId, AISTransaction transaction);

    Optional<Double> findLastMonthIncome(String tokenId);

    List<MonthlyIncomeResponse> findMonthlyIncomes(String tokenId);

}
