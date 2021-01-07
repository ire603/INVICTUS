package victus;

import javafx.geometry.Orientation;
import javafx.scene.control.*;

public class WatchListController2 {
	public static Tab getWatchListBorderPane(Tab tab2) {
		SplitPane splitPane;
		splitPane = new SplitPane();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane = StockQuoteTableView.stockQuoteOLTableView();
		ListView listView = new ListView();
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.getItems().add(0, scrollPane);
		tab2.setContent(splitPane);
		return tab2;
	}
}
