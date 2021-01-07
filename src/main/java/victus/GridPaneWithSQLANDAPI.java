package victus;

import victus.StockNews;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import pl.zankowski.iextrading4j.api.stocks.News;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.util.ArrayList;
import java.util.List;

public class GridPaneWithSQLANDAPI {

	private static TextField textField 				= new TextField();
	private static Button getDataSearchAction 			= new Button();
	private static Label ticker 						= new Label("Ticker");
	private static TextArea showTickerSymbol 			= new TextArea();
	private static Label companyName 					= new Label("Company Name");
	private static TextArea showCompanyName 			= new TextArea();
	private static Label volume 						= new Label("Volume");
	private static TextArea showVolume 					= new TextArea();
	private static Label primaryExchange 				= new Label("Primary Exchange");
	private static TextArea showPrimaryExchange 		= new TextArea();
	private static Label sector 						= new Label("Sector");
	private static TextArea showSector 					= new TextArea();
	private static Label calcPrice 						= new Label("Calculation Price");
	private static TextArea showCalculationPrice 		= new TextArea();
	private static Label open 							= new Label("Open");
	private static TextArea showOpen 					= new TextArea();
	private static Label openTime 						= new Label("Open Time");
	private static TextArea showOpenTime 				= new TextArea();
	private static Label close 							= new Label("Close");
	private static TextArea showClose 					= new TextArea();
	private static Label closeTime                         = new Label( "Close Time" );
	private static TextArea showCloseTime                  = new TextArea(  );
	private static Label lastPrice 						= new Label("Last Price");
	private static TextArea showLastPrice 				= new TextArea();
	private static Label latestSource 					= new Label("Last Source");
	private static TextArea showLastestSource 			= new TextArea();
	private static Label latestTime 					= new Label("Latest Time");
	private static TextArea showLatestTime 				= new TextArea();
	private static Label latestUpdate 					= new Label("Latest Update");
	private static TextArea showLastestUpdate 			= new TextArea();
	private static Label latestVolume           		= new Label("Latest Volume");
	private static TextArea showLatestVolume 			= new TextArea();
	private static Label high               = new Label("High");
	private static TextArea showHigh 		= new TextArea();
	private static Label low                = new Label("Low");
	private static TextArea showLow = new TextArea();
	
	private static TextArea showDeplayedPrice = new TextArea();
	private static Label delayedPriceTime               = new Label("D/P Time");
	private static TextArea showDeplayedPriceTime = new TextArea();
	private static Label previousClose                  = new Label("Previous Close");
	private static TextArea showPreviousClose = new TextArea();
	private static Label change                       	= new Label("Change");
	private static TextArea showChange = new TextArea();
	private static Label changePercent                  = new Label("Change Percent");
	private static TextArea showChangePercent = new TextArea();
	private static Label iexMarketPercent               = new Label("Market Percent");
	private static TextArea showIEXMarketPercent = new TextArea();
	private static Label averageTotalVolume             = new Label("AT Volume");
	private static TextArea showAvgTotalVolume = new TextArea();
	private static Label bid                            = new Label("Bid");
	private static TextArea showIEXBidPrice = new TextArea();
	private static Label ask                       		= new Label("Bid Price");
	private static TextArea showIEXAskPrice = new TextArea();
	private static Label askSize                        = new Label("Ask Price");
	private static TextArea showIEXAskSize = new TextArea();
	private static Label marketCap                      = new Label("Ask Size");
	private static TextArea showMarketCap = new TextArea();
	private static Label pe                       		= new Label("PE Ratio");
	private static TextArea showPERatio = new TextArea();
	private static Label week52high                     = new Label("52 Week High");
	private static TextArea showWeek52High = new TextArea();
	private static Label week52low                      = new Label("52 Week Low");
	private static TextArea showWeek52Low = new TextArea();
	private static Label ytd                       		= new Label("Year To Date Change");
	private static TextArea showYtdChange = new TextArea();

	private static ScrollPane scrollPane = new ScrollPane();
	private static GridPane gridPane = new GridPane();
	private static ListView showListView = new ListView();
	private static List<String> list;
	private static ListProperty<String> listProperty = new SimpleListProperty<>();
	private static List<News> newsList;
	private static ArrayList<StockNews> news;



	public static ScrollPane implementController() {
		
		gridPane.add(textField, 			0, 0);
		gridPane.add(getDataSearchAction, 	0, 1);
		gridPane.add(ticker, 				0, 2);
		gridPane.add(showTickerSymbol, 	    1, 2);
		gridPane.add(companyName, 			0, 3);
        gridPane.add(showCompanyName, 		1, 3);
		gridPane.add(volume, 				0, 4);
		gridPane.add(showVolume, 			1, 4);
		gridPane.add(askSize, 				0, 5);
        gridPane.add(showIEXAskSize, 		1, 5);
		gridPane.add(marketCap, 			0, 6);
		gridPane.add(showMarketCap,         1, 6);
		gridPane.add(pe, 					0, 7);
		gridPane.add(showPERatio, 			1, 7);
		gridPane.add(week52high, 			0, 8);
		gridPane.add(showWeek52High, 		1, 8);
		gridPane.add(week52low, 			0, 9);
		gridPane.add(showWeek52Low, 		1, 9);
		gridPane.add(ytd, 					0, 10);
		gridPane.add(showYtdChange, 		1, 10);
		gridPane.add(primaryExchange, 		0, 11);
		gridPane.add(showPrimaryExchange,   1, 11);
		gridPane.add(sector, 				0, 12);
		gridPane.add(showSector, 			1, 12);
		gridPane.add(calcPrice, 			0, 13);
		gridPane.add(showCalculationPrice,  1, 13);
		gridPane.add(open, 					0, 14);
		gridPane.add(showOpen, 			    1, 14);
		gridPane.add(openTime, 				0, 15);
		//gridPane.add(showOpenTime, 		    1, 15);
		gridPane.add(lastPrice, 			0, 16);
		gridPane.add(showLastPrice, 		1, 16);
		gridPane.add(latestSource, 			0, 17);
		gridPane.add( showLastestSource, 	1, 17);
		gridPane.add(latestTime, 			0, 18);
		gridPane.add(showLatestTime, 		1, 18);
		gridPane.add(latestUpdate, 			0, 19);
		gridPane.add(showLastestUpdate, 	1, 19);
		gridPane.add(latestVolume, 			0, 20);
		gridPane.add(showLatestVolume, 	    1, 20);
		gridPane.add(low,                   0, 21);
		gridPane.add(showLow,               1, 21);
		gridPane.add(high,                  0, 22);
		gridPane.add(showHigh,              1, 22);
		gridPane.add(close, 				0, 23);
		gridPane.add(showClose, 			1, 23);
		gridPane.add(closeTime,             0, 24);
		gridPane.add(showCloseTime,         1, 24);
		gridPane.add(delayedPriceTime, 		0, 25);
		gridPane.add(showDeplayedPriceTime, 1, 25);
		gridPane.add(previousClose, 		0, 26);
		gridPane.add(showPreviousClose, 	1, 26);
		gridPane.add(change, 				0, 27);
		gridPane.add(showChange, 			1, 27);
		gridPane.add(changePercent, 		0, 28);
		gridPane.add(showChangePercent, 	1, 28);
		gridPane.add(iexMarketPercent, 		0, 29);
		gridPane.add(showIEXMarketPercent,  1, 29);
		gridPane.add(averageTotalVolume, 	0, 30);
		gridPane.add(showAvgTotalVolume, 	1, 30);
		gridPane.add(bid, 					0, 31);
		gridPane.add(showIEXBidPrice, 		1, 31);
		gridPane.add(ask, 					0, 32);
		gridPane.add(showIEXAskPrice, 		1, 32);
//
		gridPane.setPrefSize( 100, 100 );
//		gridPane.add( showListView, 10, 0, 5, 5 );
		

		for (int i = 0; i < 60; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints( 100);
			gridPane.getColumnConstraints().add( columnConstraints );
		}
		
		getDataSearchAction.setOnAction( (ActionEvent event) -> {
                    String symbol = textField.getText();
                    Quote quote = getData(symbol);
                    showTickerSymbol.setText(quote.getSymbol());
                    showCompanyName.setText(quote.getCompanyName());
                    showPrimaryExchange.setText(quote.getPrimaryExchange());
                    showSector.setText(quote.getSector());
//			showCalculationPrice.setText( quote.getCalculationPrice() );
                    showOpen.setText(String.valueOf(quote.getOpen()));
                    //showOpenTime.setText(quote.getOpenTime().toString());
                    showClose.setText(quote.getIexOpen().toString());
                    showHigh.setText(quote.getHigh().toString());
                    showLow.setText(quote.getLow().toString());
                    showCloseTime.setText(String.valueOf(quote.getCloseTime()));
                    showLastPrice.setText(quote.getLatestPrice().toString());
                    showLastestSource.setText(quote.getLatestSource());
                    showLatestTime.setText(quote.getLatestTime());
                    showLastestUpdate.setText(quote.getLatestUpdate().toString());
                    showDeplayedPrice.setText(quote.getDelayedPrice().toString());
                    showDeplayedPriceTime.setText(quote.getDelayedPriceTime().toString());
                    showPreviousClose.setText(quote.getPreviousClose().toString());
                    showChange.setText(quote.getChange().toString());
                    showChangePercent.setText(quote.getChangePercent().toString());
                    showIEXMarketPercent.setText(quote.getIexMarketPercent().toString());
                    showAvgTotalVolume.setText(quote.getAvgTotalVolume().toString());
                    showIEXBidPrice.setText(quote.getIexBidPrice().toString());
                    showIEXAskPrice.setText(quote.getIexAskPrice().toString());
                    showIEXAskSize.setText(quote.getIexAskSize().toString());
                    showMarketCap.setText(quote.getMarketCap().toString());
                    showPERatio.setText(quote.getPeRatio().toString());
                    showWeek52High.setText(String.valueOf(quote.getWeek52High()));
                    showWeek52Low.setText(String.valueOf(quote.getWeek52Low().toString()));
                    showYtdChange.setText(quote.getYtdChange().toString());


                });
		scrollPane.setContent(gridPane);
		return scrollPane;
	}
	private static Quote getData(String text) {
		
		Quote quote = IEX.initiateIEX().executeRequest(new QuoteRequestBuilder().withSymbol(text).build());
		return quote;
	}
	
}