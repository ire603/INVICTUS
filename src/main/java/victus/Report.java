//import java.io.FileWriter;
//import java.io.PrintWriter;
//
//import com.martin_mattingly.martin.controller.StockTableView;
//import com.martin_mattingly.portfolio.Company;
//
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.SelectionMode;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//
//public class Report {
//	public static Stage reportDialogStage(){
//		final Stage dialog = new Stage();
//		VBox dialogRoot = new VBox(5);
//
//		HBox vbButtonsRight = new HBox(5);
//	    
//		// Buttons
//
//		Button btnAddAll = new Button("Add All");
//		btnAddAll.setPrefWidth(140);
//		btnAddAll.setPrefHeight(40);
//
//		Button btnGenRep = new Button("Report on selected companies");
//		btnGenRep.setPrefWidth(280);
//		btnGenRep.setPrefHeight(40);
//
//		vbButtonsRight.getChildren().addAll(btnAddAll,btnGenRep);
//
//		// Explanation
//
//		Label lblExplain = new Label("Use Ctrl to select singly and use Shift to select multiple");
//	    
//		//List Left 
//
//	    ObservableList<Company> companies = StockTableView.getCompanies();
//	    final ListView<Company> companyListView = new ListView<Company>(companies);
//	    companyListView.setItems(companies);
//	    companyListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//	    //Need to fix when close report window that focus remains on that list view
//
//	   	btnGenRep.setOnAction(new EventHandler<ActionEvent>(){
//	   		@Override
//	   		public void handle(ActionEvent event) {
//	   			ObservableList<Company> selectedCompanies = companyListView.getSelectionModel().getSelectedItems();
//	   			generateReport(selectedCompanies);
//
//	   			dialog.hide();
//	   		}
//	   	});
//
//	   	btnAddAll.setOnAction(new EventHandler<ActionEvent>(){
//	   		@Override
//	   		public void handle(ActionEvent event) {
//	   			companyListView.getSelectionModel().selectAll();
//	   		}
//	   	});
//
//
//		dialogRoot.getChildren().addAll(companyListView,vbButtonsRight,lblExplain);
//
//
//
//		Scene dialogScene = new Scene(dialogRoot, 400, 480);
//		dialog.setScene(dialogScene);
//		return dialog;
//	}
//
//	public static void generateReport(ObservableList<Company> companies){
//		for (int i = 0; i < companies.size(); i++) {
//			String filename = "C:\\Users\\isaacmartin\\workspace\\martin\\src\\main\\java\\com\\Reports\\"+(i+1)+".txt";
//
//			
//			try {
//				FileWriter writer = new FileWriter(filename);
//				PrintWriter out = new PrintWriter( writer );
//				for (int j = 0; j <companies.size(); j++){
//					Company company = companies.get(j);
//					out.println(String.format("%14s: %d","Number", (i +1)));
//					out.println(String.format("%14s: %s","Stock Symbol", company.getStockSymbol()));
//					out.println(String.format("%14s: %s","Company Name", company));
//					out.println(String.format("%14s: %s","Highest", company.getHighest()));
//					out.println(String.format("%14s: %s","Lowest", company.getLowest()));
//					out.println(String.format("%14s: %.1f","Average Close", company.getAverageClose()));
//					out.println(String.format("%14s: %.1f ","Close", company.getMostRecentClose())+"\n");
//	
//				}
//				out.close();
//				Alert alert = new Alert(AlertType.INFORMATION);
//				alert.setTitle("Report Created");
//				alert.setHeaderText(null);
//				alert.setContentText("Report generated and saved:\n" + filename);
//				alert.showAndWait();
//	
//			} catch ( Exception e ) {
//				e.printStackTrace();
//				System.out.println( e );
//			}
//
//		}
//
//
//	}
//
//
//}
