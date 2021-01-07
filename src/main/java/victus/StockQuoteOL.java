package victus;

import javafx.beans.property.*;

public class StockQuoteOL {
	
	private static StringProperty symbol; //   Name of the company
	private static StringProperty name; // Name of the company
	private static DoubleProperty lastPrice;// The last price of the company's stock
	private static LongProperty volume;
	private static DoubleProperty change;



	public StockQuoteOL(String symbol, String name, Double lastPrice, long volume, Double change) {
		super();
		StockQuoteOL.symbol = new SimpleStringProperty(symbol);
		StockQuoteOL.name = new SimpleStringProperty(name);
		StockQuoteOL.lastPrice = new SimpleDoubleProperty(lastPrice);
		StockQuoteOL.volume = new SimpleLongProperty(volume);
		StockQuoteOL.change = new SimpleDoubleProperty(change);
		

	}

	public StockQuoteOL(String symbol) {
		StockQuoteOL.symbol = new SimpleStringProperty(symbol);
		
		
	}
	
	public StockQuoteOL() {
	
	}
	
	
	public StringProperty getSymbol() {
		return symbol;
	}

	public void setSymbol(StringProperty symbol) {
		MysqlConnect mysqlConnect = new MysqlConnect();
		
		this.symbol = symbol;
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public DoubleProperty getLastPrice() {
		return lastPrice;
	}


	public void setLastPrice(DoubleProperty lastPrice) {
		this.lastPrice = lastPrice;
	}


	public void setVolume(LongProperty volume) {
		this.volume = volume;
	}

	public LongProperty getVolume() {
		return volume;
	}

	public DoubleProperty changeProperty() {
		return change;
	}

	public void setChange(DoubleProperty change) {
		this.change = change;
	}
	
	

}