package martin.victus.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import victus.table.IEX;
import victus.table.MysqlConnect;
import victus.synchronize.Symbol;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;
import victus.table.StockQuote;
import victus.table.StockQuoteOL;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;

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
    private static MysqlConnect mysqlConnect;
    private static GridPane gridPane;
    private static String symbol;
    
    public StockQuoteTableView(MysqlConnect mysqlConnect) {
        this.mysqlConnect = mysqlConnect;
    }
    
    public static BorderPane stockQuoteOLTableView() {
        
        stockQuoteOLTableView.setItems(stockQuoteOLS);
        symbolColumn.setCellValueFactory(cellData -> cellData.getValue().getSymbol());
        companyNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        lastPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getLastPrice());
        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().getVolume());
        changeColumn.setCellValueFactory(cellData -> cellData.getValue().changeProperty());
        
        scheduledService = new PollingService(stockQuoteOLS);
        scheduledService.maximumFailureCountProperty().set(10);
        scheduledService.setPeriod(Duration.millis(500));
        scheduledService.setOnFailed(StockQuoteTableView::handle);
        stockQuoteOLTableView.getColumns().add(symbolColumn);
        stockQuoteOLTableView.getColumns().add(companyNameColumn);
        stockQuoteOLTableView.getColumns().add(lastPriceColumn);
        stockQuoteOLTableView.getColumns().add(volumeColumn);
        stockQuoteOLTableView.getColumns().add(changeColumn);
        stockQuoteOLTableView.setPrefSize(400, 150);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter( stockQuoteOLTableView);
        GridPane gridPane = gridPane();
        Button button = new Button("HI");
        borderPane.setTop( gridPane );
        
        scheduledService.start();
        return borderPane;
    }
    public static GridPane gridPane() {
        start = new Button("Start");
        start.setOnAction(event -> {
            //Thread thread = new Thread(new Symbol() {
            //    @Override
            //    public void run() {
                    try {
                        PreparedStatement preparedStatement = mysqlConnect.connect().prepareStatement(
                                "SELECT * FROM screenedlist"
                        );
                        ResultSet resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            Quote stockQuote =
                                    victus.IEX.initiateIEX().executeRequest(
                                            new QuoteRequestBuilder().withSymbol( resultSet.getString( 1 ) ).build() );
                            
                            stockQuoteOLS.add(new StockQuoteOL(stockQuote.getSymbol(),
                                    stockQuote.getSymbol(),
                                    stockQuote.getLatestPrice().doubleValue(),
                                    stockQuote.getIexVolume().longValue(),
                                    stockQuote.getChange().doubleValue()));
                            
                            //symbol = stockQuote.getSymbol();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                //}
            //});
            //thread.start();
            
            
        });
        gridPane = new GridPane();
        gridPane.add(start, 1, 1);
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
    
    
    //private static StockQuote getStockQuote(String symbol) {
    //
    //    Quote quote = IEX.initiateIEX().executeRequest( new QuoteRequestBuilder()
    //            .withSymbol(symbol)
    //            .build() );
    //    final Double latestPrice = quote.getLatestPrice().doubleValue();
    //    StockQuote stockQuote = new StockQuote();
    //    stockQuote.setLastPrice(latestPrice);
    //    stockQuote.setName(symbol);
    //    stockQuote.setVolume(quote.getLatestVolume().longValue());
    //    stockQuote.setSymbol(symbol);
    //    stockQuote.setChange(quote.getChange().doubleValue());
    //
    //    System.out.printf("%nResult REST call: %s : %f : %d, %f", stockQuote.getSymbol(), stockQuote.getLastPrice(),
    //            stockQuote.getVolume(), stockQuote.getChange());
    //
    //    return stockQuote;
    //}
}
