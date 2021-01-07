package victus.table;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static javax.xml.bind.annotation.XmlAccessType.PROPERTY;

@XmlRootElement(name="StockQuote")

@XmlAccessorType(PROPERTY)
public class StockQuote implements Serializable{
	
	private String name; //     Name of the company
	private String symbol; //       The company's ticker symbol
	private Double lastPrice;//     The last price of the company's stock
	private long volume;
	private Double change;

	public String getSymbol() {
		return this.symbol;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Double getLastPrice() {
		return this.lastPrice;
	}

	public long getVolume() {
		return this.volume;
	}
	public Double getChange() {
		return change;
	}

	@XmlElement(name="Volume")
	public void setVolume(long volume) {
		this.volume = volume;
	}

	@XmlElement(name="Name")
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="Symbol")
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@XmlElement(name="LastPrice")
	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	@XmlElement(name="Change")
	public void setChange(Double change) {
		this.change = change;
	}
	
	

	@Override
	public String toString() {
		return "StockQuote [name=" + name + ", symbol=" + symbol
			   + ", lastPrice=" + lastPrice + "]";
	}
	
	
}