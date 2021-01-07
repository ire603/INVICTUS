package victus;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class TabOverallMarketView {

	private static MysqlConnect mysqlConnect;

	public TabOverallMarketView(MysqlConnect mysqlConnect) {
		TabOverallMarketView.mysqlConnect = mysqlConnect;
	}


	public static Tab getTabPane() {

		BorderPane borderPane = new BorderPane();
		Tab tab = new Tab("Overall Market");

		TableView tableView1 = new TableView();
		TableView tableView2 = new TableView();
		tableView1.setPrefSize(1280, 377);
		tableView2.setPrefSize(1280, 377);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setVmax(500);
		scrollPane.setPrefSize(800, 400);

		BuilderTableKeyStats.buildingData(mysqlConnect, tableView1);
		BuilderTableCompanyData.buildingData(mysqlConnect, tableView2);

		borderPane.setTop(tableView2);
		borderPane.setCenter(tableView1);

		tab.setContent(borderPane);

		return tab;
	}


}