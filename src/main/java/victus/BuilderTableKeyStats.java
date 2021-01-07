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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CountDownLatch;

public class BuilderTableKeyStats {
    public BuilderTableKeyStats() {

    }
    public static TableView buildingData(MysqlConnect mysqlConnect, TableView tableView) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ObservableList<ObservableList> data = FXCollections.observableArrayList();
                                    try {
                                        // SQL FOR SELECTING ALL DATA FROM STOCKTICKERS
                                        PreparedStatement preparedStatement = mysqlConnect.connect().prepareStatement(
                                                "SELECT * FROM keystat"
                                        );
                                        ResultSet resultSet = preparedStatement.executeQuery();

                                        // TABLE COLUMN ADDED DYNAMICALLY

                                        for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                                            final int j = i;

                                            TableColumn column = new TableColumn(resultSet.getMetaData().getColumnName(i+1));
                                            column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>() {
                                                @Override
                                                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                                                    try {
                                                        return new SimpleStringProperty(param.getValue().get(j).toString());
                                                    } catch (NullPointerException e) {
                                                        e.addSuppressed(new Throwable(e));
                                                    }
                                                    return null;
                                                }
                                            });
                                            tableView.getColumns().addAll(column);
                                            System.out.printf("%nColumn [%d]", i);
                                        }

                                        // Data added to ObservableList

                                        if (resultSet.next()) {
                                            do {

                                                // iterate Row
                                                ObservableList<String> row = FXCollections.observableArrayList();

                                                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {

                                                    // iterate Column
                                                    row.add(resultSet.getString(i));

                                                }
                                                System.out.printf("%nROW [1] added %s", row);
                                                data.add(row);
                                            } while (resultSet.next());
                                        }
                                        tableView.setItems(data);
                                    } catch(Exception e) {
                                        e.printStackTrace();
                                        System.out.println("Error on Building Data");
                                    }
                                } finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        service.start();
        return tableView;
    }
}
