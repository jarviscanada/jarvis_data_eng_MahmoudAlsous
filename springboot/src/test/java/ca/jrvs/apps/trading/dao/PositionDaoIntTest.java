package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class PositionDaoIntTest {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TraderDao traderDao;

    @Autowired
    private SecurityOrderDao securityOrderDao;

    @Autowired
    private QuoteDao quoteDao;

    @Autowired
    private PositionDao positionDao;

    private Account savedAccount;

    private SecurityOrder savedSecurityOrder;

    private Trader savedTrader;

    private Quote savedQuote;

    private Position savedPosition;

    @Before
    public void setUp() throws Exception {

        savedTrader = new Trader();
        savedTrader.setFirst_name("John");
        savedTrader.setLast_name("Smith");
        savedTrader.setCountry("Canada");
        savedTrader.setDob(new Date(System.currentTimeMillis()));
        savedTrader.setId(1);
        savedTrader.setEmail("test@gmail.com");
        traderDao.save(savedTrader);

        savedAccount = new Account();
        savedAccount.setTrader_id(1);
        savedAccount.setAmount(500.20d);

        List<Account> accounts = new ArrayList<>();
        accounts.add(savedAccount);
        accountDao.saveAll(accounts);

        savedQuote = new Quote();
        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setId("AAPL");
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);

        savedSecurityOrder = new SecurityOrder();
        savedSecurityOrder.setAccount_id(1);
        savedSecurityOrder.setStatus("FILLED");
        savedSecurityOrder.setTicker("AAPL");
        savedSecurityOrder.setSize(2);
        savedSecurityOrder.setPrice(10.2);
        savedSecurityOrder.setNotes("apple");
        securityOrderDao.save(savedSecurityOrder);

        savedPosition = new Position();
    }

    @After
    public void tearDown() throws Exception {
        //Must be deleted in order due to constraints
        securityOrderDao.deleteAll();
        quoteDao.deleteAll();
        accountDao.deleteAll();
        traderDao.deleteAll();
    }

    @Test
    public void existsById() {

    }

    @Test
    public void findById() {
        Position position = positionDao.findById(savedSecurityOrder.getId()).get();
        assertEquals(savedSecurityOrder.getTicker(), position.getTicker());
    }

    @Test
    public void findAllById() {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(savedSecurityOrder.getId());

        assertEquals(savedSecurityOrder.getTicker(), positionDao.findAllById(ids).get(0).getTicker());
    }

    @Test
    public void findAll() {
        List<Position> positions = (List<Position>) positionDao.findAll();

        assertEquals(1, positions.size());
        assertEquals(savedSecurityOrder.getSize(), positions.get(0).getPosition(), 0.001);
    }

    @Test
    public void count() {
        assertEquals(2, positionDao.count());
    }
}