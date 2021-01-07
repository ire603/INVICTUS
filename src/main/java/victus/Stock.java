package victus;


//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.io.Serializable;
import java.util.ArrayList;


public final class Stock implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String ticker;
    private String name;
    private String business_address;
    private String company_url;
    private String hq_country;
    private String sector;
    private String industry_category;
    private String long_description;
    private ArrayList<StockNews> news;


    /**
     * Create an instance of the Stock and fetch information live from the API
     *
     * @param ticker Stock ticker to be fetched
     * @param fetch
     */
    public Stock(String ticker, boolean fetch) {

        StringBuilder sb = new StringBuilder();
        sb.append(ticker.toUpperCase());
        this.ticker = sb.toString();

    }

    public String getLong_description() {
        return long_description;
    }

    public ArrayList<StockNews> getNews() {
        return news;
    }

    public void setNews(ArrayList<StockNews> news) {
        this.news = news;
    }

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public String getCompany_url() {
        return company_url;
    }


    public String getHq_country() {
        return hq_country;
    }

    public String getSector() {
        return sector;
    }

    public String getIndustry_category() {
        return industry_category;
    }

    @Override
    public String toString() {
        return ticker;
    }

}