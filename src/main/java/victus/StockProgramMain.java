package victus;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import victus.screener.Main;
import victus.screener.Screener;
import victus.screener.StockScreening;

import java.sql.SQLException;

/**
 * @author isaacmartin
 */
public class StockProgramMain extends Application {

	private TabPane main1;
	private Tab controllerTab;
	private TabOverallMarketView tabOverallMarketView;
	private Tab tabWithScreener;
	private Tab tabWithTableView;
	private Tab tabWithSecondScreener;
	private DynamicTableConstants dynamicTableConstants;
	private Main main;
	private BorderPane rootNode;
	private Company company;
	private StockQuoteTableView stockQuoteTableView;
	private TabPaneControllerImp2 screener;
	@Override
	public void start(Stage primaryStage) throws SQLException {
		MysqlConnect mysqlConnect = new MysqlConnect();
		rootNode = new BorderPane();
		main1 = new TabPane();
		Scene myScene = new Scene( rootNode, 1500, 800 );
		rootNode.prefWidthProperty().bind( myScene.widthProperty() );
		/*
		MySQL Route Connection to Classes
		 */
		new Screener(mysqlConnect);
		main = new Main(mysqlConnect);
		company = new Company(mysqlConnect);
		dynamicTableConstants = new DynamicTableConstants(mysqlConnect);
		stockQuoteTableView = new StockQuoteTableView(mysqlConnect);
		tabOverallMarketView = new TabOverallMarketView(mysqlConnect);
		new StockScreening(mysqlConnect);
		/*
		Connect Classes to Tabs
		 */
		controllerTab = new Tab();
		tabWithScreener = new Tab();
		tabWithTableView = new Tab();
		tabWithSecondScreener = new Tab();

		controllerTab = StockCanvas.multiChart();
		controllerTab.setText( "Multi-chart Canvas" );

		tabWithScreener = main.tab();
		tabWithScreener.setText("Screener");

		tabWithTableView = tabOverallMarketView.getTabPane();
		tabWithTableView.setText( "Market View" );

		tabWithSecondScreener = screener.getTabPane();
		tabWithSecondScreener.setText("Second Screener");


		main1.getTabs().add(controllerTab);
		main1.getTabs().add( tabWithTableView );
		main1.getTabs().add(tabWithScreener);
		main1.getTabs().add(tabWithSecondScreener);



		ScrollPane sp = new ScrollPane();
		
		Group gro = new Group();
		
		gro.getChildren().add( sp );
		
		sp.setLayoutY( 28 );
		sp.setPrefWidth( 500 );
		sp.setHbarPolicy( ScrollBarPolicy.ALWAYS );
		sp.setVbarPolicy( ScrollBarPolicy.ALWAYS );
		sp.setPannable( true );
		sp.setFitToHeight( true );
		sp.setStyle( "-fx-background: black;" );
		
		BorderPane borderPane1 = new BorderPane();

		Button btnClose = new Button( "Exit" );
		btnClose.setPrefWidth( 140 );
		btnClose.setPrefHeight( 40 );
		
		btnClose.setOnAction( e -> primaryStage.hide());
		
		TableView tableView;
		TabPane tabPaneWL = new TabPane();
		tableView = victus.DynamicTableConstants.getTableView();
		tabPaneWL.setPrefSize(385, 100);
		ScrollPane scrollPane;

		scrollPane = GridPaneWithSQLANDAPI.implementController();
		Tab tab1 = new Tab( "Screened List" );
		Tab tab2 = new Tab( "Screened: WatchList" );
		Tab tab3 = new Tab( "Company Info" );

		tab3.setContent(scrollPane);


		tab1 = WatchListController1.getWatchListBorderPane( tab1 );

		tab2 = WatchListController2.getWatchListBorderPane( tab2 );

		tabPaneWL.getTabs().add(tab3);
		tabPaneWL.getTabs().add(tab2);
		tabPaneWL.getTabs().add(tab1);
		
		rootNode.setLeft( tabPaneWL );
		rootNode.setBottom( scrollPane );
		
		rootNode.setRight( borderPane1 );
		rootNode.setCenter( main1 );
		
		primaryStage.setScene( myScene );
		primaryStage.setTitle( "Stock App" );
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch( args );
		System.out.println("\u2606");
		System.exit(1);
	}
	
	
}

