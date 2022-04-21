package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

    @Autowired
    private QuoteDao quoteDao;

    private Quote savedQuote;

    @Before
    public void insertOne() {
        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setId("aapl");
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);
    }


    @Test
    public void saveAll() throws Exception {
        Quote quote = new Quote();
        quote.setId(String.valueOf("abc"));
        quote.setAskPrice(10d);
        quote.setAskSize(10);
        quote.setBidPrice(10.2d);
        quote.setBidSize(10);
        quote.setLastPrice(10.1d);

        Quote quote_two = new Quote();
        quote_two.setId(String.valueOf("LOL"));
        quote_two.setAskPrice(10d);
        quote_two.setAskSize(10);
        quote_two.setBidPrice(10.2d);
        quote_two.setBidSize(10);
        quote_two.setLastPrice(10.1d);
        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(quote);
        quoteList.add(quote_two);
        List<Quote> returnQuote = quoteDao.saveAll(quoteList);
        assertEquals(returnQuote, quoteList);
        quoteDao.deleteAll();
    }

    //
    @Test
    public void existsById() throws Exception {
        assertEquals(true, quoteDao.existsById(savedQuote.getId()));
    }


    @Test
    public void findAll() throws Exception {
        Quote quote = new Quote();
        quote.setId(String.valueOf("abc"));
        quote.setAskPrice(10d);
        quote.setAskSize(10);
        quote.setBidPrice(10.2d);
        quote.setBidSize(10);
        quote.setLastPrice(10.1d);

        Quote quote_two = new Quote();
        quote_two.setId(String.valueOf("LOL"));
        quote_two.setAskPrice(10d);
        quote_two.setAskSize(10);
        quote_two.setBidPrice(10.2d);
        quote_two.setBidSize(10);
        quote_two.setLastPrice(10.1d);
        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(quote);
        quoteList.add(quote_two);
        quoteList.add(savedQuote);
        quoteDao.saveAll(quoteList);
        List<Quote> returnQuote = (List<Quote>) quoteDao.findAll();
        assertEquals(returnQuote, quoteList);
    }


    @Test
    public void count() throws Exception {
        Quote quote = new Quote();
        quote.setId(String.valueOf("abc"));
        quote.setAskPrice(10d);
        quote.setAskSize(10);
        quote.setBidPrice(10.2d);
        quote.setBidSize(10);
        quote.setLastPrice(10.1d);

        Quote quote_two = new Quote();
        quote_two.setId(String.valueOf("LOL"));
        quote_two.setAskPrice(10d);
        quote_two.setAskSize(10);
        quote_two.setBidPrice(10.2d);
        quote_two.setBidSize(10);
        quote_two.setLastPrice(10.1d);
        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(quote);
        quoteList.add(quote_two);
        quoteList.add(savedQuote);
        quoteDao.saveAll(quoteList);
        assertEquals(quoteDao.count(), 3);
    }

    @Test
    public void deleteById() throws Exception {
        Quote quote = new Quote();
        quote.setId(String.valueOf("abc"));
        quote.setAskPrice(10d);
        quote.setAskSize(10);
        quote.setBidPrice(10.2d);
        quote.setBidSize(10);
        quote.setLastPrice(10.1d);

        Quote quote_two = new Quote();
        quote_two.setId(String.valueOf("LOL"));
        quote_two.setAskPrice(10d);
        quote_two.setAskSize(10);
        quote_two.setBidPrice(10.2d);
        quote_two.setBidSize(10);
        quote_two.setLastPrice(10.1d);
        List<Quote> quoteList = new ArrayList<>();
        quoteList.add(quote);
        quoteList.add(quote_two);
        quoteList.add(savedQuote);
        quoteDao.saveAll(quoteList);
        assertEquals(3, quoteDao.count());
        quoteDao.deleteById("abc");
        assertEquals(2, quoteDao.count());
        assertFalse(quoteDao.existsById("abc"));
    }

    @After
    public void deleteOne() {
        quoteDao.deleteById(savedQuote.getId());
    }
}
