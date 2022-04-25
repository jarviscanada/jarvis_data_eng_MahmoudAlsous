package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class AccountDaoIntTest {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TraderDao traderDao;

    private Account savedAccount;

    private Trader savedTrader;

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
        savedAccount.setAmount(500.20);

        List<Account> accounts = new ArrayList<>();
        accounts.add(savedAccount);
        accountDao.saveAll(accounts);
    }

    @After
    public void tearDown() throws Exception {
        accountDao.deleteAll();
        traderDao.deleteAll();
    }

    @Test
    public void findAllById() {
        List<Account> accounts = Lists
                .newArrayList(accountDao.findAllById(Arrays.asList(savedAccount.getId())));
        assertEquals(1, accounts.size());
        assertEquals(savedAccount.getAmount(), accounts.get(0).getAmount(), 0.001);
    }

    @Test
    public void UpdateOne() {
        savedAccount.setAmount(550.20);
        accountDao.updateOne(savedAccount);
        Account changedAccount = accountDao.findById(savedAccount.getId()).get();
        assertEquals(savedAccount.getAmount(), changedAccount.getAmount());

    }

    @Test
    public void counterTest() {
        long count = accountDao.count();
        assertEquals(1, count);
    }

    @Test
    public void findAllTest() {
        List<Account> allQuotes = (List<Account>) accountDao.findAll();
        assertEquals(savedAccount.getId(), allQuotes.get(0).getId());
    }

    @Test
    public void delete() {
        try {
            accountDao.delete(savedAccount);
            fail("Not implemented");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void deleteById() {
        accountDao.deleteById(1);
        long count = accountDao.count();
        assertEquals(0, count);
    }
}