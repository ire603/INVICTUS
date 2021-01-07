package victus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import pl.zankowski.iextrading4j.api.refdata.v1.ExchangeSymbol;
import pl.zankowski.iextrading4j.api.refdata.IEXSymbolDirectory;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.api.stocks.v1.KeyStats;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.refdata.IEXSymbolDirectoryRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.refdata.v1.SymbolsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.v1.KeyStatsRequestBuilder;

import javax.ws.rs.ProcessingException;
import java.util.Collections;
import java.util.List;

public class M {
	public static void main(String[] args) {
		final IEXCloudClient cloudClient = IEXTradingClient.create( IEXTradingApiVersion.IEX_CLOUD_BETA_SANDBOX,
			   new IEXCloudTokenBuilder()
					 .withPublishableToken("Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
					 .withSecretToken("Tsk_3eedff6f5c284e1a8b9bc16c54dd1af3")
					 .build());
		final List<ExchangeSymbol> exchangeSymbolList = cloudClient.executeRequest(new SymbolsRequestBuilder()
			   .build());
		final Quote quote = cloudClient.executeRequest( new QuoteRequestBuilder().withSymbol( "AAPL").build());
		System.out.print(quote);
		int i = 0;
		for (ExchangeSymbol exchangeSymbol : exchangeSymbolList) {
			String symbol = exchangeSymbol.getSymbol();
			System.out.printf("%n%d:%s",i,symbol);
			i++;
		}
		
	}
}
