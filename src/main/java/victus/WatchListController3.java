package victus;

import javafx.geometry.Orientation;
import javafx.scene.control.*;

public class WatchListController3 {
	public static TabPane tabPaneWL;
	
	public static Tab getWatchListBorderPane(Tab tab3) {
		SplitPane splitPane = new SplitPane();
		TableView tableView;
		tableView = new TableView();
		ListView listView = new ListView();
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.getItems().add(0, tableView);
		splitPane.getItems().add(1, listView);
		tab3.setContent(splitPane);
		return tab3;
	}
}
