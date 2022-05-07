package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

    //capture parameter when calling securityOrderDao.save
    @Captor
    ArgumentCaptor<SecurityOrder> captorSecurityOrder;

    //mock all dependencies
    @Mock
    private AccountDao accountDao;
    @Mock
    private SecurityOrderDao securityOrderDao;
    @Mock
    private QuoteDao quoteDao;
    @Mock
    private PositionDao positionDao;

    //injecting mocked dependencies to the testing class via constructor
    @InjectMocks
    private OrderService orderService;

    @Test
    public void testOrderService() {
        Account account = new Account();
        account.setAmount(1000000d);
        when(accountDao.findById(any())).thenReturn(Optional.of(account));

        Quote quote = new Quote();
        quote.setAskPrice(10d);
        quote.setBidPrice(10d);
        when(quoteDao.existsById(any())).thenReturn(true);
        when(quoteDao.findById(any())).thenReturn(Optional.of(quote));

        when(securityOrderDao.save(any())).thenReturn(new SecurityOrder());

        Position position = new Position();
        position.setPosition(1000);
        position.setTicker("aapl");
        when(positionDao.findAllById(any())).thenReturn(Arrays.asList(position));

        MarketOrderDto buyOrder = new MarketOrderDto();
        buyOrder.setId(1);
        buyOrder.setSize(500);
        buyOrder.setTicker("aapl");
        buyOrder.setType("BUY");

        MarketOrderDto sellOrder = new MarketOrderDto();
        sellOrder.setId(1);
        sellOrder.setSize(500);
        sellOrder.setTicker("aapl");
        sellOrder.setType("SELL");
        SecurityOrder order = orderService.executeMarketOrder(buyOrder);
        //order = orderService.executeMarketOrder(sellOrder);
        assertTrue(order.getNotes().equals("Buy request"));
    }
}