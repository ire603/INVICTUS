package victus;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static javax.xml.bind.annotation.XmlAccessType.PROPERTY;

@XmlRootElement(name="StockQuote")

@XmlAccessorType(PROPERTY)
public class StockQuote extends victus.table.StockQuote implements Serializable{
	
	private static String name; //     Name of the company
	private static String symbol; //       The company's ticker symbol
	private static Double lastPrice;//     The last price of the company's stock
	private static long volume;
	private static Double change;
	
	public StockQuote(String symbol, String name, Double lastPrice, long volume, Double change) {
		super();
		StockQuote.symbol = symbol;
		StockQuote.name = name;
		StockQuote.lastPrice = lastPrice;
		StockQuote.volume = volume;
		StockQuote.change = change;
	}
	
	public StockQuote() {
	
	}
	
	public String getSymbol() {
		return StockQuote.symbol;
	}
	
	public String getName() {
		return StockQuote.name;
	}
	
	public Double getLastPrice() {
		return StockQuote.lastPrice;
	}

	public long getVolume() {
		return StockQuote.volume;
	}
	public Double getChange() {
		return StockQuote.change;
	}

	@XmlElement(name="Volume")
	public void setVolume(long volume) {
		StockQuote.volume = volume;
	}

	@XmlElement(name="Name")
	public void setName(String name) {
		StockQuote.name = name;
	}
	
	@XmlElement(name="Symbol")
	public void setSymbol(String symbol) {
		StockQuote.symbol = symbol;
	}
	
	@XmlElement(name="LastPrice")
	public void setLastPrice(Double lastPrice) {
		StockQuote.lastPrice = lastPrice;
	}

	@XmlElement(name="Change")
	public void setChange(Double change) {
		StockQuote.change = change;
	}
	
	

	@Override
	public String toString() {
		return "StockQuote [name=" + name + ", symbol=" + symbol
			   + ", lastPrice=" + lastPrice + "]";
	}
	
	
}