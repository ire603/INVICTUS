package victus;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.math.BigDecimal;


public class QuoteThis {
	private String           symbol;
	private String           companyName;
	private String           primaryExchange;
	private String           sector;
	private String           calculationPrice;
	private static BigDecimal       open;
	private Long             openTime;
	private BigDecimal       close;
	private Long             closeTime;
	private BigDecimal       high;
	private BigDecimal       low;
	private BigDecimal       latestPrice;
	private String           latestSource;
	private static String           latestTime;
	private Long             latestUpdate;
	private BigDecimal       latestVolume;
	private BigDecimal       iexRealtimePrice;
	private BigDecimal       iexRealtimeSize;
	private Long             iexLastUpdated;
	private BigDecimal       delayedPrice;
	private Long             delayedPriceTime;
	private BigDecimal previousClose;
	private BigDecimal change;
	private BigDecimal changePercent;
	private BigDecimal iexMarketPercent;
	private BigDecimal iexVolume;
	private BigDecimal avgTotalVolume;
	private BigDecimal iexBidPrice;
	private BigDecimal iexBidSize;
	private BigDecimal iexAskPrice;
	private BigDecimal iexAskSize;
	private BigDecimal marketCap;
	private BigDecimal peRatio;
	private BigDecimal week52High;
	private BigDecimal week52Low;
	private BigDecimal ytdChange;
	
	public QuoteThis(String symbol) {
		this.setSymbol( symbol );
		IEXTradingClient iexTradingClient;
		Quote quote = IEX.initiateIEX().executeRequest( new QuoteRequestBuilder()
			   .withSymbol(getSymbol())
			   .build() );
		
		this.symbol = String.valueOf( quote.getWeek52High() );
		companyName = String.valueOf( quote.getCompanyName() );
		iexVolume = quote.getIexVolume();
		primaryExchange = quote.getPrimaryExchange();
		sector = quote.getSector();
		calculationPrice = quote.getCalculationPrice();
		open = quote.getOpen();
		openTime = quote.getOpenTime();
		close = quote.getClose();
		closeTime = quote.getCloseTime();
		high = quote.getHigh();
		low = quote.getLow();
		latestPrice = quote.getLatestPrice();
		latestSource = quote.getLatestSource();
		latestTime = quote.getLatestTime();
		latestUpdate = quote.getLatestUpdate();
		latestVolume = quote.getLatestVolume();
		iexRealtimePrice = quote.getIexRealtimePrice();
		iexRealtimeSize = quote.getIexRealtimeSize();
		iexLastUpdated = quote.getIexLastUpdated();
		delayedPrice = quote.getDelayedPrice();
		delayedPriceTime = quote.getDelayedPriceTime();
		previousClose = quote.getPreviousClose();
		change = quote.getChange();
		changePercent = quote.getChangePercent();
		iexMarketPercent = quote.getIexMarketPercent();
		avgTotalVolume = quote.getAvgTotalVolume();
		iexBidPrice = quote.getIexBidPrice();
		iexBidSize = quote.getIexBidSize();
		iexAskPrice = quote.getIexAskPrice();
		iexAskSize = quote.getIexAskSize();
		marketCap = quote.getMarketCap();
		peRatio = quote.getPeRatio();
		week52High = quote.getWeek52High();
		week52Low = quote.getWeek52Low();
		ytdChange = quote.getYtdChange();
		
	}
	
	public void setVolume(BigDecimal volume) {
		this.iexVolume = volume;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/*
	 SETTERS
	 */
	public String getSymbol() {
		return symbol;
	}
	public String getCompanyName() {
		return companyName;
	}
	
	public BigDecimal getVolume() {
		return iexVolume;
	}
	
	
	public String getPrimaryExchange() {
		return primaryExchange;
	}
	
	public void setPrimaryExchange(String primaryExchange) {
		this.primaryExchange = primaryExchange;
	}
	
	public String getSector() {
		return sector;
	}
	
	public void setSector(String sector) {
		this.sector = sector;
	}
	
	public String getCalculationPrice() {
		return calculationPrice;
	}
	
	public void setCalculationPrice(String calculationPrice) {
		this.calculationPrice = calculationPrice;
	}
	
	public static BigDecimal getOpen() {
		return open;
	}
	
	public void setOpen(BigDecimal open) {
		QuoteThis.open = open;
	}
	
	public Long getOpenTime() {
		return openTime;
	}
	
	public void setOpenTime(Long openTime) {
		this.openTime = openTime;
	}
	
	public BigDecimal getClose() {
		return close;
	}
	
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	
	public Long getCloseTime() {
		return closeTime;
	}
	
	public void setCloseTime(Long closeTime) {
		this.closeTime = closeTime;
	}
	
	public BigDecimal getHigh() {
		return high;
	}
	
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	
	public BigDecimal getLow() {
		return low;
	}
	
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	
	public BigDecimal getLatestPrice() {
		return latestPrice;
	}
	
	public void setLatestPrice(BigDecimal latestPrice) {
		this.latestPrice = latestPrice;
	}
	
	public String getLatestSource() {
		return latestSource;
	}
	
	public void setLatestSource(String latestSource) {
		this.latestSource = latestSource;
	}
	
	public static String getLatestTime() {
		return latestTime;
	}
	
	public void setLatestTime(String latestTime) {
		QuoteThis.latestTime = latestTime;
	}
	
	public Long getLatestUpdate() {
		return latestUpdate;
	}
	
	public void setLatestUpdate(Long latestUpdate) {
		this.latestUpdate = latestUpdate;
	}
	
	public BigDecimal getLatestVolume() {
		return latestVolume;
	}
	
	public void setLatestVolume(BigDecimal latestVolume) {
		this.latestVolume = latestVolume;
	}
	
	public BigDecimal getIexRealtimeSize() {
		return iexRealtimeSize;
	}
	
	public void setIexRealtimeSize(BigDecimal iexRealtimeSize) {
		this.iexRealtimeSize = iexRealtimeSize;
	}
	
	public BigDecimal getIexRealtimePrice() {
		return iexRealtimePrice;
	}
	
	public void setIexRealtimePrice(BigDecimal iexRealtimePrice) {
		this.iexRealtimePrice = iexRealtimePrice;
	}
	
	public Long getIexLastUpdated() {
		return iexLastUpdated;
	}
	
	public void setIexLastUpdated(Long iexLastUpdated) {
		this.iexLastUpdated = iexLastUpdated;
	}
	
	public BigDecimal getDelayedPrice() {
		return delayedPrice;
	}
	
	public void setDelayedPrice(BigDecimal delayedPrice) {
		this.delayedPrice = delayedPrice;
	}
	
	public Long getDelayedPriceTime() {
		return delayedPriceTime;
	}
	
	public void setDelayedPriceTime(Long delayedPriceTime) {
		this.delayedPriceTime = delayedPriceTime;
	}
	
	public BigDecimal getPreviousClose() {
		return previousClose;
	}
	
	public void setPreviousClose(BigDecimal previousClose) {
		this.previousClose = previousClose;
	}
	
	public BigDecimal getChange() {
		return change;
	}
	
	public void setChange(BigDecimal change) {
		this.change = change;
	}
	
	public BigDecimal getChangePercent() {
		return changePercent;
	}
	
	public void setChangePercent(BigDecimal changePercent) {
		this.changePercent = changePercent;
	}
	
	public BigDecimal getIexMarketPercent() {
		return iexMarketPercent;
	}
	
	public void setIexMarketPercent(BigDecimal iexMarketPercent) {
		this.iexMarketPercent = iexMarketPercent;
	}
	
	public BigDecimal getAvgTotalVolume() {
		return avgTotalVolume;
	}
	
	public void setAvgTotalVolume(BigDecimal avgTotalVolume) {
		this.avgTotalVolume = avgTotalVolume;
	}
	
	public BigDecimal getIexBidPrice() {
		return iexBidPrice;
	}
	
	public void setIexBidPrice(BigDecimal iexBidPrice) {
		this.iexBidPrice = iexBidPrice;
	}
	
	public BigDecimal getIexBidSize() {
		return iexBidSize;
	}
	
	public void setIexBidSize(BigDecimal iexBidSize) {
		this.iexBidSize = iexBidSize;
	}
	
	public BigDecimal getIexAskPrice() {
		return iexAskPrice;
	}
	
	public void setIexAskPrice(BigDecimal iexAskPrice) {
		this.iexAskPrice = iexAskPrice;
	}
	
	public BigDecimal getIexAskSize() {
		return iexAskSize;
	}
	
	public void setIexAskSize(BigDecimal iexAskSize) {
		this.iexAskSize = iexAskSize;
	}
	
	public BigDecimal getPeRatio() {
		return peRatio;
	}
	
	public void setPeRatio(BigDecimal peRatio) {
		this.peRatio = peRatio;
	}
	
	public BigDecimal getMarketCap() {
		return marketCap;
	}
	
	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}
	
	public BigDecimal getWeek52High() {
		return week52High;
	}
	
	public void setWeek52High(BigDecimal week52High) {
		this.week52High = week52High;
	}
	
	public BigDecimal getWeek52Low() {
		return week52Low;
	}
	
	public void setWeek52Low(BigDecimal week52Low) {
		this.week52Low = week52Low;
	}
	
	public BigDecimal getYtdChange() {
		return ytdChange;
	}
	
	public void setYtdChange(BigDecimal ytdChange) {
		this.ytdChange = ytdChange;
	}
}



//			try (Connection connection = DriverManager.getConnection(url, user, password)) {
//				try {
//					String sql = "INSERT INTO mattingly_and_martin.quote (id, SYMBOL, COMPANYNAME, PRIMARYEXCHANGE, SECTOR, CALCULATIONPRICE, OPEN, OPENTIME, CLOSE, CLOSETIME, HIGHPRICE, LOWPRICE, LATESTPRICE, LATESTSOURCE, LATESTTIME, LATESTUPDATE, LATESTVOLUME, IEXREALTIMEPRICE, IEXREALTIMESIZE, IEXLASTUPDATED, DELAYEDPRICE, DELAYEDPRICETIME, PREVIOUSCLOSE, CHANGEPRICE, CHANGEPERCENT, IEXMARKETPERCENT, IEXVOLUME, AVGTOTALVOLUME, IEXBIDPRICE, IEXBIDSIZE, IEXASKPRICE, IEXASKSIZE, MARKETCAP, PERATIO, WEEK52HIGH, WEEK52LOW, YTDCHANGE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//					try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//						preparedStatement.setInt(1, 1);
//						preparedStatement.setString(2,  symbols);
//						preparedStatement.setString(3,  getCompanyName());
//						preparedStatement.setString(4,  getPrimaryExchange());
//						preparedStatement.setString(5,  getSector());
//						preparedStatement.setString(6,  getCalculationPrice());
//						preparedStatement.setBigDecimal(7,  getOpen());
//						preparedStatement.setLong(7,  getOpenTime());
//						preparedStatement.setBigDecimal(8,  getClose());
//						preparedStatement.setLong(9,  getCloseTime());
//						preparedStatement.setBigDecimal(10,  getHigh());
//						preparedStatement.setBigDecimal(11,  getLow());
//						preparedStatement.setBigDecimal(12,  getLatestPrice());
//						preparedStatement.setString(13,  getLatestSource());
//						preparedStatement.setString(14,  getLatestTime());
//						preparedStatement.setLong(15,  getLatestUpdate());
//						preparedStatement.setBigDecimal(16,  getLatestVolume());
//						preparedStatement.setBigDecimal(17,  getIexRealtimePrice());
//						preparedStatement.setBigDecimal(18,  getIexRealtimeSize());
//						preparedStatement.setLong(19,  getIexLastUpdated());
//						preparedStatement.setLong(19,  getDelayedPriceTime());
//						preparedStatement.setBigDecimal(20,  getPreviousClose());
//						preparedStatement.setBigDecimal(21,  getChange());
//						preparedStatement.setBigDecimal(22,  getChangePercent());
//						preparedStatement.setBigDecimal(23,  getIexMarketPercent());
//						preparedStatement.setBigDecimal(24,  getIexVolume());
//						preparedStatement.setBigDecimal(25,  getAvgTotalVolume());
//						preparedStatement.setBigDecimal(26,  getIexBidPrice());
//						preparedStatement.setBigDecimal(27,  getIexBidSize());
//						preparedStatement.setBigDecimal(28,  getIexAskPrice());
//						preparedStatement.setBigDecimal(29,  getIexAskSize());
//						preparedStatement.setBigDecimal(30,  getMarketCap());
//						preparedStatement.setBigDecimal(31,  getPeRatio());
//						preparedStatement.setBigDecimal(32,  getWeek52High());
//						preparedStatement.setBigDecimal(35,  getWeek52Low());
//						preparedStatement.setBigDecimal(36,  ytdChange);
//
//						preparedStatement.executeUpdate();
//						preparedStatement.close();
//					}
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//
//
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}
//				   connection.prepareStatement("INSERT INTO quote (SYMBOL, COMPANYNAME, PRIMARYEXCHANGE, SECTOR, CALCULATIONPRICE, OPEN, OPENTIME, CLOSE, CLOSETIME, LATESTPRICE, LATESTSOURCE, LATESTTIME, LATESTUPDATE, LATESTVOLUME, IEXREALTIMEPRICE, IEXREALTIMESIZE, DELAYEDPRICE, DELAYEDPRICETIME, PREVIOUSCLOSE, CHANGEPRICE, CHANGEPERCENT, IEXMARKETPERCENT, IEXVOLUME, AVGTOTALVOLUME, IEXBIDPRICE, IEXBIDSIZE, IEXASKPRICE, IEXASKSIZE, MARKETCAP, PERATIO, WEEK52HIGH, WEEK52LOW) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");



//
//			preparedStatement.setString(1,  quote.getSymbol());
//			preparedStatement.setString(2,  quote.getCompanyName());
//			preparedStatement.setString(3,  quote.getPrimaryExchange());
//			preparedStatement.setString(4,  quote.getSector());
//			preparedStatement.setString(5,  quote.getCalculationPrice());
//			preparedStatement.setBigDecimal(6,  quote.getOpen());
//			preparedStatement.setLong(7,  quote.getOpenTime());
//			preparedStatement.setBigDecimal(8,  quote.getClose());
//			preparedStatement.setLong(9,  quote.getCloseTime());
//			preparedStatement.setBigDecimal(10,  quote.getHigh());
//			preparedStatement.setBigDecimal(11,  quote.getLow());
//			preparedStatement.setBigDecimal(12,  quote.getLatestPrice());
//			preparedStatement.setString(13,  quote.getLatestSource());
//			preparedStatement.setString(14,  quote.getLatestTime());
//			preparedStatement.setLong(15,  quote.getLatestUpdate());
//			preparedStatement.setBigDecimal(16,  quote.getLatestVolume());
//			preparedStatement.setBigDecimal(17,  quote.getIexRealtimePrice());
//			preparedStatement.setBigDecimal(18,  quote.getIexRealtimeSize());
////			preparedStatement.setLong(19,  quote.getIexLastUpdated());
//
//			preparedStatement.setLong(19,  quote.getDelayedPriceTime());
//			preparedStatement.setBigDecimal(20,  quote.getPreviousClose());
//			preparedStatement.setBigDecimal(21,  quote.getChange());
//			preparedStatement.setBigDecimal(22,  quote.getChangePercent());
//			preparedStatement.setBigDecimal(23,  quote.getIexMarketPercent());
//			preparedStatement.setBigDecimal(24,  quote.getIexVolume());
//			preparedStatement.setBigDecimal(25,  quote.getAvgTotalVolume());
//			preparedStatement.setBigDecimal(26,  quote.getIexBidPrice());
//			preparedStatement.setBigDecimal(27,  quote.getIexBidSize());
//			preparedStatement.setBigDecimal(28,  quote.getIexAskPrice());
//			preparedStatement.setBigDecimal(29,  quote.getIexAskSize());
//			preparedStatement.setBigDecimal(30,  quote.getMarketCap());
//			preparedStatement.setBigDecimal(31,  quote.getPeRatio());
//			preparedStatement.setBigDecimal(32,  quote.getWeek52High());
////			preparedStatement.setBigDecimal(35,  quote.getWeek52Low());
////			preparedStatement.setBigDecimal(36,  quote.getYtdChange());
//
//			preparedStatement.execute();
//			connection.close();
		
	
	


