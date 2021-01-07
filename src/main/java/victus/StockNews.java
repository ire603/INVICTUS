package victus;

import java.io.Serializable;


public class StockNews implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final String title;
    private final String url;
    private final String date;

    /**
     *
     * @param title The title of the news article
     * @param url the URL of the news article
     * @param date the date of the news article
     * 
     */
    public StockNews(String title, String url, String date) {
        this.title = title;
        this.url = url;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }
    /**
     * To string override to format the stock news in the listView on the stockInfo Scene
     * @return 
     */

    @Override
    public String toString() {
        String formatNews = "%s\n%s";
        return String.format(formatNews, title, date);
    }

}