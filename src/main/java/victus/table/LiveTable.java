package victus.table;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import victus.table.MysqlConnect;
import victus.table.StockQuoteOL;


public class LiveTable extends Application {
	
	public LiveTable() {
	
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		StackPane root = new StackPane();
		
		//TabPane tabPane = new TabPane(
		//);
		MysqlConnect mysqlConnect = new MysqlConnect();
		new martin.victus.table.StockQuoteTableView( mysqlConnect );
		//tabPane.getTabs().add();
		root.setPadding( new Insets( 5 ) );
		BorderPane borderPane = martin.victus.table.StockQuoteTableView.stockQuoteOLTableView();
		
		root.getChildren().add( borderPane );
		Scene scene = new Scene( root, 511, 311 );
		stage.setScene( scene );
		stage.show();
	}
	
	//public static TableView<StockQuoteOL> table() {
	//	Tab tab = new Tab();
	//	SplitPane splitPane;
	//	splitPane = new SplitPane();
	//
	//	BorderPane borderPane = martin.victus.table.StockQuoteTableView.stockQuoteOLTableView();
	//	ListView listView = new ListView();
	//	splitPane.setOrientation( Orientation.VERTICAL);
	//	splitPane.getItems().add( 0, borderPane);
	//
	//	tab.setContent(splitPane);
	//	//TableView<StockQuoteOL> tableView = new TableView<StockQuoteOL>();
	//	//TableColumn<StockQuoteOL, String> symbol = new TableColumn<>("SYMBOL");
	//	//TableColumn<StockQuoteOL, Number> lastPrice = new TableColumn<>("LAST PRICE");
	//	//TableColumn<StockQuoteOL, Number> stockVolume = new TableColumn<>("VOLUME");
	//	//TableColumn<StockQuoteOL, String> changePrice = new TableColumn<>("CHANGE IN PRICE");
	//	//
	//	//tableView.getColumns().add( 0, symbol );
	//	//tableView.getColumns().add( 1, lastPrice);
	//	//tableView.getColumns().add( 2, stockVolume );
	//	//tableView.getColumns().add( 3, changePrice );
	//
	//
	//	return tableView;
	//}
	
	private static StockQuoteOL stockQuoteOL() {
		
		return  null;
	}
}
