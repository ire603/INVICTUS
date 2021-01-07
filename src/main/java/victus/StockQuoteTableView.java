package victus;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Duration;
import victus.synchronize.Symbol;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class StockQuoteTableView {
    private static TableView<StockQuoteOL> stockQuoteOLTableView = new TableView<>();
    private static TableColumn<StockQuoteOL, String> symbolColumn = new TableColumn<>("Symbol");
    private static TableColumn<StockQuoteOL, String> companyNameColumn = new TableColumn<>("Company");
    private static TableColumn<StockQuoteOL, Number> lastPriceColumn = new TableColumn<>("Last Price");
    private static TableColumn<StockQuoteOL, Number> changeColumn = new TableColumn<>("Change");
    private static TableColumn<StockQuoteOL, Number> volumeColumn = new TableColumn<>("Volume");
    private static ObservableList<StockQuoteOL> stockQuoteOLS = FXCollections.observableArrayList();
    private static ScheduledService<ObservableList<StockQuoteOL>> scheduledService;

    private static Button start;
    private static TextArea enterSymbol;
    private static MysqlConnect mysqlConnect;
    private static GridPane gridPane;
    private static String symbol;
    private static StockQuote stockQuote;
    private static StockQuote[] stockQuotes;
    private static Button button;
    private static int ii = 0;
    private static int i = 0;
    private static StockQuoteOL[] stockQuoteOL1 = new StockQuoteOL[100];
    
    public StockQuoteTableView(MysqlConnect mysqlConnect) {
        StockQuoteTableView.mysqlConnect = mysqlConnect;
    }
    
   
    
    public static ScrollPane stockQuoteOLTableView() {
        ScrollPane scrollPane = new ScrollPane();
        
        stockQuoteOLTableView.setItems( stockQuoteOLS );
        symbolColumn.setCellValueFactory( new Callback<TableColumn.CellDataFeatures<StockQuoteOL, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StockQuoteOL, String> stockQuoteOLStringCellDataFeatures) {
                return stockQuoteOLStringCellDataFeatures.getValue().getSymbol();
            }
        } );
        companyNameColumn.setCellValueFactory( new Callback<TableColumn.CellDataFeatures<StockQuoteOL, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<StockQuoteOL, String> stockQuoteOLStringCellDataFeatures) {
                return stockQuoteOLStringCellDataFeatures.getValue().getName();
            }
        } );
        lastPriceColumn.setCellValueFactory( new Callback<TableColumn.CellDataFeatures<StockQuoteOL, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<StockQuoteOL, Number> stockQuoteOLNumberCellDataFeatures) {
                return stockQuoteOLNumberCellDataFeatures.getValue().getLastPrice();
            }
        } );
        volumeColumn.setCellValueFactory( new Callback<TableColumn.CellDataFeatures<StockQuoteOL, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<StockQuoteOL, Number> stockQuoteOLNumberCellDataFeatures) {
                return stockQuoteOLNumberCellDataFeatures.getValue().getVolume();
            }
        } );
        changeColumn.setCellValueFactory( new Callback<TableColumn.CellDataFeatures<StockQuoteOL, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<StockQuoteOL, Number> stockQuoteOLNumberCellDataFeatures) {
                return stockQuoteOLNumberCellDataFeatures.getValue().changeProperty();
            }
        } );
        scheduledService= new PollingService(stockQuoteOLS);
        scheduledService.maximumFailureCountProperty().set(10);
        scheduledService.setPeriod(Duration.millis(10000));
        scheduledService.setOnFailed(StockQuoteTableView::handle);
        stockQuoteOLTableView.getColumns().add(symbolColumn);
        stockQuoteOLTableView.getColumns().add(companyNameColumn);
        stockQuoteOLTableView.getColumns().add(lastPriceColumn);
        stockQuoteOLTableView.getColumns().add(volumeColumn);
        stockQuoteOLTableView.getColumns().add(changeColumn);
        stockQuoteOLTableView.setPrefSize(400, 150);
        
        scheduledService.start();
        scrollPane.setContent( stockQuoteOLTableView );
        
        return scrollPane;
    }
    public static GridPane gridPane() {
        List list = new ArrayList();
        enterSymbol = new TextArea("Enter Symbol= Stock");
        start = new Button("Start");
        button = new Button("Enter New Symbol");
        start.setOnAction(event -> {
            Thread thread = new Thread(new Symbol() {
                @Override
                public void run() {
                    try {
                        
                        //PreparedStatement preparedStatement = mysqlConnect.connect().prepareStatement(
                        //        "SELECT * FROM screenedlist"
                        //);
                        //ResultSet resultSet = preparedStatement.executeQuery();
    
                        final boolean[] ref = new boolean[100];
                        ref[ii] = false;
                        int clicked = 0;
                        
                        button.setOnAction( event -> {
                            ref[ii] = true;
                            if (ref[ii]) {
                                String text = enterSymbol.getText();
                                    
                                    
                                    List[] lists = new List[100];
                                    lists[i] = Arrays.asList(text );
                                    Iterator iterator = lists[i].iterator();
                                    while (iterator.hasNext()) {
                
                                        stockQuote = Symbol.getSymbol( String.valueOf( iterator.next() ) );
                                        stockQuotes = new StockQuote[10000];
                                        stockQuotes[i] = new StockQuote( stockQuote.getSymbol(), stockQuote.getName(),
                                                stockQuote.getLastPrice(), stockQuote.getVolume(), stockQuote.getChange() );
                                        stockQuoteOLS.addListener( new ListChangeListener<StockQuoteOL>() {
                                            @Override
                                            public void onChanged(Change<? extends StockQuoteOL> change) {
                                                System.out.printf("%nStock Added");
                                            }
                                        } );
                                        stockQuoteOL1[0] = new StockQuoteOL(stockQuotes[i].getSymbol(),
                                                stockQuotes[i].getName(),
                                                stockQuotes[i].getLastPrice(), stockQuotes[i].getVolume(),
                                                stockQuotes[i].getChange());
                                        stockQuoteOLS.add(stockQuoteOL1[0] );
                                        i++;
                                    }
    
                                
                                
                                    //for (int ii = 0; ii < list.size(); ii++) {
                                    //    List<StockQuoteOL> list1 = new ArrayList<>();
                                    //    StockQuoteOL stockQuoteOL = new StockQuoteOL();
                                    //
                                    //
                                    //
                                    //}
                                }
                            
                        });
                       
                       
                       
                       
                        //i++;
                        
                        
                        
                        
                        
                        
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();


        });
        enterSymbol.setPrefSize( 151,  11 );
        gridPane = new GridPane();
        gridPane.add(start, 1, 1);
        gridPane.add(enterSymbol, 2, 1);
        gridPane.add(button, 3, 1);
        return gridPane;
    }
    private static void handle(WorkerStateEvent event) {
        event.getSource().getException().printStackTrace();
        System.out.printf("%nError in call %s : ", event.getSource().getException().getMessage());
    }

    public static class PollingService extends ScheduledService<ObservableList<StockQuoteOL>> {

        private ObservableList<StockQuoteOL> listStockQuotes;

        PollingService(ObservableList<StockQuoteOL> listStockQuotes) {
            this.listStockQuotes = listStockQuotes;

        }

        @Override
        protected Task<ObservableList<StockQuoteOL>> createTask() {
            return new PollingTask(listStockQuotes);
        }

    }

    private static class PollingTask extends Task<ObservableList<StockQuoteOL>> {
        private ObservableList<StockQuoteOL> listStocks;


        PollingTask(ObservableList<StockQuoteOL> listStocks) {
            this.listStocks = listStocks;
        }


        @Override
        public ObservableList<StockQuoteOL> call() {
            try {
                listStocks.forEach(quote -> quote.getLastPrice().set(Symbol.getSymbol(quote.getSymbol().get()).getLastPrice()));
                listStocks.forEach(quote -> quote.getVolume().set(Symbol.getSymbol(quote.getSymbol().get()).getVolume()));
                listStocks.forEach(quote -> quote.changeProperty().set(Symbol.getSymbol(quote.getSymbol().get()).getChange()));
                return listStocks;
            } catch (ConcurrentModificationException e) {
                e.addSuppressed(new Throwable(e));
            }
            return null;
        }
    }


    private static StockQuote getStockQuote(String symbol) {

        Quote quote = IEX.initiateIEX().executeRequest( new QuoteRequestBuilder()
                .withSymbol(symbol)
                .build() );
        final Double latestPrice = quote.getLatestPrice().doubleValue();
        StockQuote stockQuote = new StockQuote();
        stockQuote.setLastPrice(latestPrice);
        stockQuote.setName(symbol);
        stockQuote.setVolume(quote.getLatestVolume().longValue());
        stockQuote.setSymbol(symbol);
        stockQuote.setChange(quote.getChange().doubleValue());

        System.out.printf("%nResult REST call: %s : %f : %d, %f", stockQuote.getSymbol(), stockQuote.getLastPrice(),
                stockQuote.getVolume(), stockQuote.getChange());

        return stockQuote;
    }
}
