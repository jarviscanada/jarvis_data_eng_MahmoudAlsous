package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.PortfolioView;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DashboardService {

    private TraderDao traderDao;
    private PositionDao positionDao;
    private AccountDao accountDao;
    private QuoteDao quoteDao;

    @Autowired
    public DashboardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao,
                            QuoteDao quoteDao) {
        this.traderDao = traderDao;
        this.positionDao = positionDao;
        this.accountDao = accountDao;
        this.quoteDao = quoteDao;
    }

    /**
     * Create and return a traderAccountView by trader ID
     * - get trader account by id
     * - get trader info by id
     * - create and return a traderAccountView
     *
     * @param traderId  must not be null
     * @return traderAccountView
     * @throws IllegalArgumentException if traderId is null or not found
     * */
    public TraderAccountView getTraderAccount(Integer traderId) {
        if (traderId == null || !traderDao.findById(traderId).isPresent()) {
            throw new IllegalArgumentException("Invalid trader ID.");
        }

        Trader trader = traderDao.findById(traderId).get();
        Account account = accountDao.findById(traderId).get();
        return new TraderAccountView(trader.getId(), trader.getFirst_name(), trader.getLast_name(),
                trader.getDob(), trader.getCountry(), trader.getEmail(), account.getId(),
                account.getAmount());
    }

    /**
     * Create and return portfolioView by traderId
     * - get account by traderId
     * - get positions by account_id
     * - create and return a portfolioView
     *
     * @param traderId must not be null
     * @return portfolioView
     * @throws IllegalArgumentException if traderId is null or not found
     * */
    public PortfolioView getPortfolioViewByTraderId(Integer traderId) {
        if (traderId == null || !accountDao.findById(traderId).isPresent() || !(
                positionDao.findAllById(Arrays.asList(traderId)).size() > 0)) {
            throw new IllegalArgumentException("Invalid Trader ID.");
        }

        Account account = accountDao.findById(traderId).get();
        List<Position> positions = positionDao.findAllById(Arrays.asList(traderId));
        return new PortfolioView(account, positions);
    }

    /**
     * @throws IllegalArgumentException if traderId is not found
     * @param traderId
     * @return
     */
    private Account findAccountByTraderId(Integer traderId){
        if (traderId == null || !accountDao.findById(traderId).isPresent() || !(
                positionDao.findAllById(Arrays.asList(traderId)).size() > 0)) {
            throw new IllegalArgumentException("Invalid Trader ID.");
        }

        Account account = accountDao.findById(traderId).get();
        return account;
    }
}