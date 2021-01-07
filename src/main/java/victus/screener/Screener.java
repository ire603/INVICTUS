package victus.screener;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import victus.MysqlConnect;
import victus.ReportScreener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Screener {

    private static ResultSet resultSet;
    private static MysqlConnect mysqlConnect;

    public Screener(MysqlConnect mysqlConnect) {


        this.mysqlConnect = mysqlConnect;
    }
    public static TabPane getGridPaneScreener() {
        mysqlConnect = new MysqlConnect();
        GridPane gridPane = new GridPane();
        Label latest_volume = new Label("Latest Volume");
        TextField textField1 = new TextField("Between");
        TextField textField2 = new TextField("And");
        Label month3LatestChange = new Label("Month 3 Latest Change");
        TextField textField3 = new TextField("Between");
        TextField textField4 = new TextField("And");
        Button submit = new Button("Submit First Query");
        Button submit1 = new Button("Submit Second Query");
        Button putScreenedListInTable = new Button("Execute Table");
        TableView tableView1 = new TableView();
        tableView1.setPrefSize(600, 400);

        submit.setOnAction(event -> {
            String between = textField1.getText();
            int betweenInt = Integer.parseInt(between);
            String and = textField2.getText();
            int andInt = Integer.parseInt(and);

            StockScreening stockScreening = new StockScreening(mysqlConnect);
//            stockScreening.filterStocks(criteria, condition, values);

        });

        submit1.setOnAction(event -> {

            String between1 = textField3.getText();
            double betweenInt1 = Double.parseDouble(between1);
            String and1 = textField4.getText();
            double andInt1 = Double.parseDouble(and1);

            String between = textField1.getText();
            int betweenInt = Integer.parseInt(between);
            String and = textField2.getText();
            int andInt = Integer.parseInt(and);

            StockScreening stockScreening = new StockScreening(mysqlConnect);
            stockScreening.filterStocks1(betweenInt1, andInt1, betweenInt, andInt);

        });
        putScreenedListInTable.setOnAction(event -> {
            buildData(tableView1);
        });
        gridPane.add(latest_volume,     0, 0);
        gridPane.add(textField1,        1, 0);
        gridPane.add(textField2,        2, 0);
        gridPane.add(month3LatestChange,0, 2);
        gridPane.add(textField3,        1, 2);
        gridPane.add(textField4,        2, 2);
        gridPane.add(submit,            3, 1);
        gridPane.add(submit1, 3, 2);



        TabPane tabPane = new TabPane();
        Tab screeningTab = new Tab();
        Tab tableViewOfScreener = new Tab();
        BorderPane borderPane = new BorderPane();
        GridPane gridPane1 = new GridPane();
        gridPane1.add(putScreenedListInTable, 1, 1);
        ReportScreener reportScreener = new ReportScreener();

        borderPane.setCenter(tableView1);
        borderPane.setTop(gridPane1);
        screeningTab.setContent(gridPane);
        tableViewOfScreener.setContent(borderPane);
        screeningTab.setText("Screener Tab");
        tableViewOfScreener.setText("Table of Screener Table");
        tabPane.getTabs().addAll(screeningTab, tableViewOfScreener);
        return tabPane;
    }

    private static void buildData(TableView tableView1) {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        MysqlConnect mysqlConnect = new MysqlConnect();
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement1;
        try {
            preparedStatement = mysqlConnect.connect().prepareStatement(
                "SELECT * FROM screenedlist"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                preparedStatement1 = mysqlConnect.connect().prepareStatement(
                        "SELECT * FROM companydata WHERE symbols = ?"
                );
                preparedStatement1.setString(1, resultSet.getString(1));

                ResultSet resultSet1 = preparedStatement1.executeQuery();
                
                for (int i = 0; i < resultSet1.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn tableColumn = new TableColumn(resultSet1.getMetaData().getColumnName(i+1));
                    tableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });
                    tableView1.getColumns().addAll(tableColumn);
                    System.out.printf("%nColumn [%d]", i);

                }

                if (resultSet1.next()) {
                    do {
                        ObservableList<String> row = FXCollections.observableArrayList();

                        for (int i = 1; i <= resultSet1.getMetaData().getColumnCount(); i++) {
                            row.add(resultSet1.getString(i));
                        }
                        System.out.printf("%nROW [1] added %s", row);
                        data.add(row);
                    } while (resultSet1.next());
                    tableView1.setItems(data);
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
