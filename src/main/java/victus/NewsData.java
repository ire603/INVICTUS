//package victus;
//
//import pl.zankowski.iextrading4j.api.stocks.News;
//import pl.zankowski.iextrading4j.client.IEXTradingClient;
//import pl.zankowski.iextrading4j.client.rest.request.stocks.NewsRequestBuilder;
//
//import java.time.OffsetDateTime;
//import java.util.List;
//
//public class NewsData {
//
//	private static List<News> news;
//
//	public static void main(String[] args) {
//		final List<News> newsList = IEX.initiateIEX().executeRequest(new News( OffsetDateTime.MAX,
//			   args[args.length ], args[args.length], args[args.length],args[args.length], ).withSymbol("AAPL")
//				.build());
//        String headline = newsList.get(0).getHeadline();
//        System.out.println(headline);
//	}
//	public static List<News> getNews(String symbol) {
//
//		news = IEX.initiateIEX().executeRequest(new NewsRequestBuilder().withSymbol(symbol).build());
//		System.out.printf("%n%s", news.get(0).getHeadline());
//		return news;
//	}
//}
