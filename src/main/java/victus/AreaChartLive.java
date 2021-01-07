package victus;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import victus.synchronize.Symbol;


import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javafx.scene.chart.XYChart.Data;
import static javafx.scene.chart.XYChart.Series;

/**
 * @author isaacmartin
 */
public class AreaChartLive {
	
	private static final int MAX_DATA_POINTS = 50;
	private static ConcurrentLinkedQueue<Number> dataQueue;
	private static Series<Object, Object> series1;
	
	
	
	private static StockQuote symbols;
	private static ObservableList<Company> listCompany = FXCollections.observableArrayList();
	private static ComboBox<Company> companyComboBox = new ComboBox<Company>();
	private static Series<String, Number> series;
	private static ExecutorService executorService;
	private static Line line;
	private static CategoryAxis x;
	//private static ChartPanManager panner;
	//private static ChartZoomManager zoomManager;
	private static BorderPane borderPane;
	private static String symbol;

	static {
		dataQueue = new ConcurrentLinkedQueue<>();
	}

	public static BorderPane initializeAreaChart() {
		Pane stockPane = new Pane();
		symbol = "";
		HBox hBox = new HBox();
		int tick = 0;

		x = new CategoryAxis();

		listCompany.addAll(Company.getListCompanies());
		companyComboBox.setItems(listCompany);
		NumberAxis y = new NumberAxis();
		x.setAutoRanging(true);
		x.setTickMarkVisible(true);

		y.setForceZeroInRange(false);
		y.setAutoRanging(true);
		y.setTickUnit(.10);

		AreaChart<String, Number> chart = new AreaChart<String, Number>( x, y ) {
			protected void addStockItem(Series<String, Number> series, int itemIndex, Data<String, Number> item) {
			}
		};
		
		chart.setPrefSize( 1000, 500);
		chart.setAnimated( false );

		series = new Series<>();
		
		series1 = new Series<>();

		chart.getData().addAll( series);
		line = new Line();
		
		Button button = new Button();
		
		TextField inputStock = new TextField();
		inputStock.setPromptText("ENTER STOCK (Ticker)");
		button.setOnAction( e -> {
			if (inputStock != null) {
				symbol = inputStock.getText();
				start();
			}
		} );
		companyComboBox.getSelectionModel().selectedItemProperty()
			   .addListener((ChangeListener<? super Company>)
				      (ObservableValue<? extends Company> observable,
				       Company oldValue, Company newValue) -> {
						symbol = newValue.getName();
						start();

		});

		stockPane.getChildren().addAll(chart,line );

		borderPane = new BorderPane();
		hBox.getChildren().addAll(inputStock, button, companyComboBox);
		borderPane.setTop(hBox);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent( stockPane );
		borderPane.setCenter( scrollPane );
		return borderPane;
	}



	private static void start() {

		executorService = Executors.newCachedThreadPool( r -> {
			Thread thread = new Thread( r );
			thread.setDaemon( true );
			return thread;
		});
		
		AddToQueue addToQueue = new AddToQueue();
		executorService.execute( addToQueue );
		prepareTimeLine();
		
	}
	
/**
 * @author isaacmartin
 */
public static class AddToQueue implements Runnable {
	private double last;

	@Override
	public void run() {
		try {
			Thread thread = new Thread(new Symbol() {
				@Override
				public void run() {
					symbols = Symbol.getSymbol( symbol);
				}
			});
			thread.run();
			last = symbols.getLastPrice();
			dataQueue.add(last);
			Thread.sleep( 1000 );
			executorService.execute( this );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

	private static void prepareTimeLine() {
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				addDataToSeries();
			}
		}.start();

	}

	private static void addDataToSeries() {
		try {
			int ii = 0;
			for (int i = 0; i < 1000; i++) {
				if (dataQueue.isEmpty()) {
					break;
				}


				Date date = new Date();
				String date2 = String.format("%s:%s:%s%n%.2f",
						date.getHours(), date.getMinutes(), date.getSeconds(),
						dataQueue.element().doubleValue());

				series.getData().add(new Data(date2, dataQueue.remove()));
			}

			if (series.getData().size() > MAX_DATA_POINTS) {
				series.getData().remove( ii++, series.getData().size() - MAX_DATA_POINTS );
			}



		}catch (NumberFormatException e) {
			e.addSuppressed(new Throwable(e));
		}
	}
}