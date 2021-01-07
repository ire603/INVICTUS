package victus;

import javafx.geometry.Orientation;
import javafx.scene.control.*;


public class WatchListController1 {
	public static Tab getWatchListBorderPane(Tab tab1) {
		SplitPane splitPane;
		splitPane = new SplitPane();
		TableView tableView = new TableView();
		tableView = DynamicTableConstants.getTableView();
		ListView listView = new ListView();
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.getItems().add(0, tableView);
		tab1.setContent(splitPane);
		return tab1;
	}
}
