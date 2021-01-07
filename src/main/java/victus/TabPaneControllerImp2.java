package victus;//import main.martin.data.Stock;
//import main.martin.util.ReportScreener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import victus.screener.StockScreening;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabPaneControllerImp2 {
    private static Stock selectedStock;
//    private static Screener victus.screener;
    private static Stage primaryStage;
    private static int latestPrice;
    private static int week52High;
    private static int week52Low;
    private static int changeInPercent;
    private static int changePercent;
    private static int volume;
    private static int avgTotalVolume;
    private static int week52Change;
    private static int year5ChangePercent;
    private static int year2ChangePercent;
    private static int year1ChangePercent;
    private static int ytd1ChangePercent;
    private static int month6ChangePercent;
    private static int month3ChangePercent;
    private static int month1ChangePercent;
    private static int day30ChangePercent;
    private static int day5ChangePercent;
    private static List valueList;
    private static List criteriaList;
    private static List conditionList;


    public static Tab getTabPane() {


        Tab historicalDataTab = new Tab( "Screener" );

        BorderPane borderPane = new BorderPane();

        GridPane gridPane = new GridPane();
        gridPane.setPadding( new Insets( 10 ) );
        gridPane.setGridLinesVisible( true );
        gridPane.getStyleClass().add( "Stock INFO" );
        gridPane.setPrefSize( 600, 600 );
        gridPane.setAlignment( Pos.CENTER );
        ScrollPane scrollPane = new ScrollPane();
        StackPane logoPane = new StackPane();
        Circle titleHolder = new Circle();
        titleHolder.setRadius( 75 );
        titleHolder.setStroke( Color.RED );
        titleHolder.setFill( Color.WHITE );
        titleHolder.setStrokeWidth( 10 );
        Label appTitle = new Label( "Martin Screener" );
        StackPane holder = new StackPane( titleHolder, appTitle );
        logoPane.getChildren().add( holder );

        Label criteriaLabel = new Label( "Criteria" );
        Label conditionLabel = new Label( "Condition" );
        Label valueLabel = new Label( "Value" );
        ColumnConstraints criteriaColumn = new ColumnConstraints();
        ColumnConstraints conditionColumn = new ColumnConstraints();
        ColumnConstraints valueColumn = new ColumnConstraints();

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();

        criteriaColumn.setMinWidth( 200 );
        criteriaColumn.setHalignment( HPos.LEFT );

        conditionColumn.setMinWidth( 200 );
        conditionColumn.setHalignment( HPos.CENTER );

        valueColumn.setMinWidth( 200 );
        valueColumn.setHalignment( HPos.CENTER );

        ArrayList<ComboBox> conditionBox;
        conditionBox = new ArrayList<>();
        ArrayList<TextField> valueFld = new ArrayList<>();

        ArrayList<CheckBox> checkBox = new ArrayList<>();
        checkBox.add( new CheckBox( "Latest Price" ) );
        checkBox.add( new CheckBox( "Volume" ) );
        checkBox.add( new CheckBox( "Average Total Volume" ) );
        checkBox.add( new CheckBox( "Week 52 Change"));
        checkBox.add( new CheckBox( "Year 5 Change Percent"));
        checkBox.add( new CheckBox( "Year 2 Change Percent"));
        checkBox.add( new CheckBox( "Year 1 Change Percent"));
        checkBox.add( new CheckBox( "YTD 1 Change Percent"));
        checkBox.add( new CheckBox( "Month 6 Change Percent"));
        checkBox.add( new CheckBox( "Month 3 Change Percent"));
        checkBox.add( new CheckBox( "Month 1 Change Percent"));
        checkBox.add( new CheckBox( "Day 30 Change Percent"));
        checkBox.add( new CheckBox( "Day 5  Change Percent"));
        String[] conditions = {"Greater Than", "Less Than", "Greater Than or Equal To", "Equals", "Less Than or Equal To", "Contains"};

        for (int i = 0; i < 13; i++) {
            conditionBox.add( new ComboBox() );
            valueFld.add( new TextField() );
            conditionBox.get( i ).getItems().addAll( conditions );
            gridPane.addRow( i + 1, checkBox.get( i ), conditionBox.get( i ), valueFld.get( i ) );
        }
        gridPane.getColumnConstraints().addAll( criteriaColumn, conditionColumn, valueColumn );
        gridPane.addRow( 0, criteriaLabel, conditionLabel, valueLabel );
        gridPane.getRowConstraints().addAll( row1, row2 );

        Label stockLabel = new Label( "Screened Stock" );
        ListView<Stock> stockList = new ListView<>();
        stockList.setMinHeight( 400 );
        stockList.setMinWidth( 400 );
        Pane spacer = new Pane();

        HBox.setHgrow( spacer, Priority.SOMETIMES );

        Button searchButton = new Button( "Search" );
        setButton( searchButton );
        searchButton.setAlignment( Pos.BOTTOM_RIGHT );


        HBox hboxButton = new HBox();
        searchButton.setOnAction( (ActionEvent e) -> {

            StockScreening stockScreening = null;
            for (int i = 0; i < conditionBox.size(); i++) {
                String criteria = null;
                String condition = null;
                String value;

                if (checkBox.get(i).isSelected()) {
                    switch (i) {
                        case 0:
                            criteria = "latestPrice";

                            break;
                        case 1:
                            criteria = "volume";

                            break;
                        case 2:
                            criteria = "avgTotalVolume";

                            break;
                        case 3:
                            criteria = "week52Change";

                            break;
                        case 4:
                            criteria = "year5ChangePercent";

                            break;
                        case 5:
                            criteria = "year2ChangePercent";

                            break;
                        case 6:
                            criteria = "year1ChangePercent";

                            break;
                        case 7:
                            criteria = "ytd1ChangePercent";

                            break;
                        case 8:
                            criteria = "month6ChangePercent";

                            break;
                        case 9:
                            criteria = "month3ChangePercent";

                            break;
                        case 10:
                            criteria = "month1ChangePercent";

                            break;
                        case 11:
                            criteria = "day30ChangePercent";

                            break;
                        case 12:
                            criteria = "day5ChangePercent";

                            break;
                        default:
                            break;
                    }
                    switch (conditionBox.get(i).getSelectionModel().getSelectedIndex()) {
                        case 0:
                            condition = ">";
                            break;
                        case 1:
                            condition = "<";
                            break;
                        case 2:
                            condition = ">=";
                            break;
                        case 3:
                            condition = "=";
                            break;
                        case 4:
                            condition = "<=";
                            break;
                        case 5:
                            condition = "contains";
                            break;
                        case 6:
                            condition = " ";
                            break;
                    }
                    value = valueFld.get(i).getText();
                    double values = Double.parseDouble(value);
                    valueList = new ArrayList();
                    criteriaList = new ArrayList();
                    conditionList = new ArrayList();


                    for (int ii = 0; ii < 1; ii++) {
                        criteriaList.add(ii, criteria);
                        conditionList.add(ii, condition);
                        valueList.add(ii, values);
                        stockScreening = new StockScreening(criteriaList, conditionList, valueList);
//                        System.out.printf("%n%s:%s:%f", criteriaList.get(ii), conditionList.get(ii), valueList.get(ii));
                    }


                }


            }
            stockScreening.filterStocks();



        } );
        Button btnReport = new Button();
        btnReport.setPrefHeight( 40 );
        btnReport.setPrefWidth( 140 );
        btnReport.setOnAction( event -> {
            // TODO Auto-generated method stub
            final Stage dialog = ReportScreener.reportScreeneDialogStage();
            dialog.initModality( Modality.APPLICATION_MODAL );
            dialog.initOwner( primaryStage );
            dialog.show();
        } );
        stockList.setOnMouseClicked( e -> {
            if (e.getClickCount() == 1) {
                selectedStock = new Stock( stockList.getSelectionModel().getSelectedItem().getTicker(), true );
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
                } catch (SQLException ee) {
                    ee.printStackTrace();
                }


            }
        } );
        hboxButton.getChildren().addAll( searchButton, spacer, btnReport );

        borderPane.setLeft( valueLabel );
        borderPane.setBottom( hboxButton );
        borderPane.setCenter( gridPane );
        borderPane.setRight( stockList );
        scrollPane.setContent( borderPane );
        historicalDataTab.setContent( scrollPane );
        return historicalDataTab;
    }

    public static void setButton(Button button) {
        button.getStyleClass().add("button");
        button.setOnMouseEntered((MouseEvent t) -> {
            button.setStyle("-fx-background-color:blue;");
        });
        button.setOnMouseExited((MouseEvent t) -> {
            button.setStyle("-fx-background-color:rosybrown;");
        });
    }
//
//    public static void setScreener(Screener victus.screener) {
//        TabPaneControllerImp2.victus.screener = victus.screener;
//    }
}
