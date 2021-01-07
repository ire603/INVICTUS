package victus;//import martin.Screener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ReportScreener {


	private static Stock stock;
	private static String filename;
	private static boolean view;
    private static ArrayList<String> stockArray;

    public static Stage reportScreeneDialogStage() {
		// TODO Auto-generated method stub
		Stage dialog = new Stage();
		VBox dialogRoot = new VBox(5);

		HBox vbButtonsRight = new HBox(5);

		// Buttons
		TextField fileNameInput = new TextField();
		fileNameInput.setPrefSize(140, 40);

		Button btnAddAll = new Button("Add All");
		btnAddAll.setPrefWidth(140);
		btnAddAll.setPrefHeight(40);

		Button btnGenRep = new Button("Report on selected companies");
		btnGenRep.setPrefWidth(280);
		btnGenRep.setPrefHeight(40);

		vbButtonsRight.getChildren().addAll(btnAddAll, btnGenRep, fileNameInput);

		ObservableList<String> oListStock = null;
		try {
			MysqlConnect mysqlConnect = new MysqlConnect();
			PreparedStatement preparedStatement = mysqlConnect.connect().prepareStatement(
					"SELECT * FROM companydata"
			);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PreparedStatement preparedStatement1 = mysqlConnect.connect().prepareStatement(
						"SELECT * FROM screenedlist WHERE symbol = ?"
				);
				preparedStatement1.setString(1, resultSet.getString(1));
				ResultSet resultSet1 = preparedStatement1.executeQuery();
				oListStock = FXCollections.observableArrayList();

				while (resultSet1.next()) {

					System.out.printf("%nResults : %s", resultSet1.getString(1));
					for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
						oListStock.add(resultSet1.getString(1));
					}

				}


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		final ListView<String> stockListView = new ListView<String>(oListStock);

		stockListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Label lblExplain = new Label("Use Ctrl to select singly and use Shift to select multiple");

		btnGenRep.setOnAction(new EventHandler<ActionEvent>() {


			private ObservableList<String> selectedTickers;

			@Override
			public void handle(ActionEvent arg0) {

				setSelectedTickers(stockListView.getSelectionModel().getSelectedItems());
				Connection connection;
				try {
					MysqlConnect mysqlConnect = new MysqlConnect();
					connection = mysqlConnect.connect();
					PreparedStatement preparedStatement;

					String theinsertstring = "INSERT INTO screenedlist (screenedticker) VALUES (?)";

					preparedStatement = connection.prepareStatement(theinsertstring);

					for (String observableList : selectedTickers) {
						String tickers = observableList;
						preparedStatement.setString(1, tickers);
						preparedStatement.executeUpdate();

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				generateReport(selectedTickers, fileNameInput);

			}

			@SuppressWarnings("unused")
			public ObservableList<String> getSelectedTickers() {
				return selectedTickers;
			}

			void setSelectedTickers(ObservableList<String> selectedTickers) {
				this.selectedTickers = selectedTickers;
			}

		});

		btnAddAll.setOnAction(event -> stockListView.getSelectionModel().selectAll());
		dialogRoot.getChildren().addAll(stockListView, vbButtonsRight, lblExplain);

		Scene dialogScene = new Scene(dialogRoot, 400, 480);
		dialog.setScene(dialogScene);

		return dialog;
	}



	private static void generateReport(ObservableList<String> selectedTickers, TextField fileName) {
		String path = "C:\\Users\\igori\\IdeaProjects\\Stock-Application-Platform\\src\\main\\resources\\";
		String allTogether = path + fileName.getText() + ".txt";
		try {
			FileWriter writer = new FileWriter(allTogether);
			PrintWriter out = new PrintWriter (writer);

			for (String stock : selectedTickers) {
				out.println( stock );
				System.out.print( stock );
			}
			out.close();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Report Created");
			alert.setHeaderText(null);
			alert.setContentText("Report generated and saved:%s" + fileName.getText());
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}



	public static Stock getStock() {
		return stock;
	}



	public static void setStock(Stock stock) {
		ReportScreener.stock = stock;
	}



	public static String getFilename() {
		return filename;
	}



	public static void setFilename(String filename) {
		ReportScreener.filename = filename;
	}



	public static boolean isView() {
		return view;
	}



	public static void setView(boolean view) {
		ReportScreener.view = view;
	}

}
