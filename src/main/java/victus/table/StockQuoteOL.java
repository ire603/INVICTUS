package victus.table;

import javafx.beans.property.*;

public class StockQuoteOL {

	private StringProperty symbol; //   Name of the company
	private StringProperty name; // Name of the company
	private DoubleProperty lastPrice;// The last price of the company's stock
	private LongProperty volume;
	private DoubleProperty change;



	public StockQuoteOL(String symbol, String name, Double lastPrice, long volume, Double change) {
		super();
		this.symbol = new SimpleStringProperty(symbol);
		this.name = new SimpleStringProperty(name);
		this.lastPrice = new SimpleDoubleProperty(lastPrice);
		this.volume = new SimpleLongProperty(volume);
		this.change = new SimpleDoubleProperty(change);
		

	}

	public StockQuoteOL() {
		
	}


	public StringProperty getSymbol() {
		return symbol;
	}

	public void setSymbol(StringProperty symbol) {
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