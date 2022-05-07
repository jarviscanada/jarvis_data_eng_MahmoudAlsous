package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.sql.Date.valueOf;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {

    @Autowired
    private TraderDao traderDao;

    private Trader savedTrader;

    @Before
    public void insertOne() throws Exception {
        savedTrader = new Trader();
        savedTrader.setFirst_name("John");
        savedTrader.setLast_name("Smith");
        savedTrader.setCountry("Canada");
        savedTrader.setDob((java.sql.Date) new Date(System.currentTimeMillis()));
        savedTrader.setId(1);
        savedTrader.setEmail("test@gmail.com");
        traderDao.save(savedTrader);
    }

    @After
    public void deleteOne() throws Exception {
        traderDao.deleteAll();
    }

    @Test
    public void findAllById() {
        List<Trader> traders = Lists.
                newArrayList(traderDao.findAllById(Collections.singletonList(savedTrader.getId())));
        assertEquals(1, traders.size());
        assertEquals(1, traders.get(0).getId().intValue());
        assertEquals(savedTrader.getCountry(), traders.get(0).getCountry());
    }

    @Test
    public void counterTest() {
        long count = traderDao.count();
        assertEquals(1, count);
    }

    @Test
    public void findallTest() {
        List<Trader> allQuotes = (List<Trader>) traderDao.findAll();
        assertEquals(savedTrader.getId(), allQuotes.get(0).getId());
    }

    @Test
    public void deleteById() {
        traderDao.deleteById(1);
        long count = traderDao.count();
        assertEquals(0, count);
    }
}
