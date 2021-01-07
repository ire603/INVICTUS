package victus;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import victus.MysqlConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.CountDownLatch;

public class DynamicTableConstants {
	private static ObservableList<ObservableList> data;
	private static TableView tableView;
	private static MysqlConnect mysqlConnect;

	public DynamicTableConstants(MysqlConnect mysqlConnect) {
		DynamicTableConstants.mysqlConnect = mysqlConnect;
	}
	
	public static TableView getTableView() {
		tableView = new TableView();
		buildData();
		return tableView;
	}

	
	public static void buildData() {
		Service<Void> service = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws Exception {
						final CountDownLatch countDownLatch = new CountDownLatch(2);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								Connection connection;
								data = FXCollections.observableArrayList();

								try {
									connection = mysqlConnect.connect();
									String SQL = "SELECT * FROM screenedlist";
									
									ResultSet resultSet = connection.createStatement().executeQuery(SQL);

									for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
										final int j = i;

										TableColumn column =
											   new javafx.scene.control.TableColumn(resultSet.getMetaData().getColumnName(i+1));
										column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>() {
											@Override
											public ObservableValue<String> call(javafx.scene.control.TableColumn.CellDataFeatures<ObservableList, String> param) {
												return new SimpleStringProperty(param.getValue().get(j).toString());
											}
										});
										tableView.getColumns().addAll(column);
										System.out.printf("%nColumn [%d]", i);
									}

									do {
										if (!(resultSet.next())) break;

										ObservableList<String> row = FXCollections.observableArrayList();

										for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
											row.add(resultSet.getString(i));
										}

										System.out.printf("%nROW [1] added %s", row);
										data.add(row);
									} while (true);
									tableView.setItems(data);

								} catch(Exception e){
									e.printStackTrace();
									System.out.println("Error on Building Data");
								} finally {
									countDownLatch.countDown();
								}
							}
						});
						countDownLatch.await();
						return null;
					}
				};
			}
		};
		service.start();

	
	}
}
