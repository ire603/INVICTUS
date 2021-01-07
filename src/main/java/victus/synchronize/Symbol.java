package victus.synchronize;

import victus.IEX;
import victus.StockQuote;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.util.ArrayList;
import java.util.List;

public class Symbol implements Runnable{
    private static List<Quote> quotes = new ArrayList<>();
    private static int i;


    public Symbol() {

    }

    public static synchronized StockQuote getSymbol(String symbol) {
        StockQuote stockQuote = new StockQuote();
        for (int ii = 0; ii < 2; ii++) {
            Quote quote = IEX.initiateIEX().executeRequest(new QuoteRequestBuilder().withSymbol(symbol).build());
            quotes.add(i, quote);
            
            stockQuote.setSymbol(quotes.get(ii).getSymbol());
            stockQuote.setChange(quotes.get(ii).getChange().doubleValue());
            stockQuote.setName(quotes.get(ii).getSymbol());
            stockQuote.setLastPrice(quotes.get(ii).getLatestPrice().doubleValue());
            stockQuote.setVolume(quotes.get(ii).getIexVolume().longValue());

            System.out.printf("%nResult REST call: %s : %f : %d, %f", stockQuote.getSymbol(), stockQuote.getLastPrice(),
                    stockQuote.getVolume(), stockQuote.getChange());
            return stockQuote;
        }
        return null;
    }

    @Override
    public void run() {

    }
}
