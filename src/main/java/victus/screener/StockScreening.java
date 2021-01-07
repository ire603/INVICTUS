package victus.screener;


import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import victus.MysqlConnect;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class StockScreening {
    private static int startValueForLatestVolume;
    private static int endValueForLatestVolume;
    private static MysqlConnect mysqlConnect;

    private static String volume;
    private static String volumeCondition;
    private static Double volumeValue;
    private static String month3;
    private static String month3Condtion;
    private static Double month3Value;
    private static String latestPrice;
    private static String latestPriceCondition;
    private static Double latestPriceValue;
    private static String changePercent;
    private static String changePercentCondition;
    private static Double changePercentValue;
    private static String week52High;
    private static String week52HighCondition;
    private static Double week52HighValue;
    private static String week52Low;
    private static String week52LowCondition;
    private static Double week52LowValue;
    private static String changeInPrice;
    private static String changeInPriceCondition;
    private static Double changeInPriceValue;
    private static String avgTotalVolume;
    private static String avgTotalVolumeCondition;
    private static Double avgTotalVolumeValue;
    private static String week52Change;
    private static String week52ChangeCondition;
    private static Double week52ChangeValue;
    private static String year5ChangePercent;
    private static String year5ChangePercentCondition;
    private static Double year5ChangePercentValue;
    private static String year2ChangePercent;
    private static String year2ChangePercentCondition;
    private static Double year2ChangePercentValue;
    private static String year1ChangePercent;
    private static String year1ChangePercentCondition;
    private static Double year1ChangePercentValue;
    private static String ytd1ChangePercent;
    private static String ytd1ChangePercentCondition;
    private static Double ytd1ChangePercentValue;
    private static String month6;
    private static String month6Condtion;
    private static Double month6Value;
    private static String month1;
    private static String month1Condtion;
    private static Double month1Value;
    private static String day30;
    private static String day30Condition;
    private static Double day30Value;
    private static String day5;
    private static String day5Condition;
    private static Double day5Value;
    private ResultSet rsLatestPrice;
    private ResultSet rsWeek52High;
    private ResultSet rsWeek52Low;
    private ResultSet rsChangeInPrice;
    private ResultSet rsChangePercent;
    private ResultSet rsVolume;
    private ResultSet rsMonth3;




    public StockScreening(MysqlConnect mysqlConnect) {

        this.mysqlConnect = mysqlConnect;
    }


    public StockScreening(List<String> criteriaList, List<String> conditionList, List<Double> valueList) {
        Iterator criteriaListIterator = criteriaList.iterator();
        Iterator conditionListIterator = conditionList.iterator();
        Iterator  valueListIterator = valueList.iterator();
        Object[] next = new Object[criteriaList.size()];
        Object[] next1 = new Object[criteriaList.size()];
        Object[] next2 = new Object[criteriaList.size()];
        while (conditionListIterator.hasNext()) {
            for (int i = 0; i < criteriaList.size(); i++ ) {
                next[i] = criteriaListIterator.next();
                next1[i] = conditionListIterator.next();
                next2[i] = valueListIterator.next();
                System.out.printf("%n%s:%s:%s", next[i], next1[i], next2[i]);
                if (next[i].equals("latestPrice")) {
                    latestPrice = String.valueOf(next[i]);
                    latestPriceCondition = String.valueOf(next1[i]);
                    latestPriceValue = Double.valueOf(String.valueOf(next2[i]));
                }
                if (next[i].equals("volume")) {
                    volume = String.valueOf(next[i]);
                    volumeCondition = String.valueOf(next1[i]);
                    volumeValue = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", volume, volumeCondition, volumeValue);
                }
                if (next[i].equals("avgTotalVolume")) {
                    avgTotalVolume = String.valueOf(next[i]);
                    avgTotalVolumeCondition = String.valueOf(next1[i]);
                    avgTotalVolumeValue = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", volume, volumeCondition, volumeValue);
                }
                if (next[i].equals("week52Change")) {
                    week52Change = String.valueOf(next[i]);
                    week52ChangeCondition = String.valueOf(next1[i]);
                    week52ChangeValue = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", volume, volumeCondition, volumeValue);
                }
                if (next[i].equals("year5ChangePercent")) {
                    year5ChangePercent = String.valueOf(next[i]);
                    year5ChangePercentCondition = String.valueOf(next1[i]);
                    year5ChangePercentValue = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", volume, volumeCondition, volumeValue);
                }
                if (next[i].equals("year2ChangePercent")) {
                    year2ChangePercent = String.valueOf(next[i]);
                    year2ChangePercentCondition = String.valueOf(next1[i]);
                    year2ChangePercentValue = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", volume, volumeCondition, volumeValue);
                }
                if (next[i].equals("year1ChangePercent")) {
                    year1ChangePercent = String.valueOf(next[i]);
                    year1ChangePercentCondition = String.valueOf(next1[i]);
                    year1ChangePercentValue = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", volume, volumeCondition, volumeValue);
                }
                if (next[i].equals("ytd1ChangePercent")) {
                    ytd1ChangePercent = String.valueOf(next[i]);
                    ytd1ChangePercentCondition = String.valueOf(next1[i]);
                    ytd1ChangePercentValue = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", volume, volumeCondition, volumeValue);
                }
                if (next[i].equals("month6ChangePercent")) {
                    month6 = String.valueOf(next[i]);
                    month6Condtion = String.valueOf(next1[i]);
                    month6Value = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", month3, month3Condtion, month3Value);
                }
                if (next[i].equals("month3ChangePercent")) {
                    month3 = String.valueOf(next[i]);
                    month3Condtion = String.valueOf(next1[i]);
                    month3Value = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", month3, month3Condtion, month3Value);
                }
                if (next[i].equals("month1ChangePercent")) {
                    month1 = String.valueOf(next[i]);
                    month1Condtion = String.valueOf(next1[i]);
                    month1Value = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", month3, month3Condtion, month3Value);
                }
                if (next[i].equals("day30ChangePercent")) {
                    day30 = String.valueOf(next[i]);
                    day30Condition = String.valueOf(next1[i]);
                    day30Value = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", month3, month3Condtion, month3Value);
                }
                if (next[i].equals("day5ChangePercent")) {
                    day5 = String.valueOf(next[i]);
                    day5Condition = String.valueOf(next1[i]);
                    day5Value = Double.valueOf(String.valueOf(next2[i]));
                    System.out.printf("%n%s%s:%f", month3, month3Condtion, month3Value);
                }
            }

        }
    }

    public static void filterStocks() {


        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        CountDownLatch countDownLatch = new CountDownLatch(2);
                        Platform.runLater(() -> {
                            PreparedStatement preparedStatement;
                            try {
                                preparedStatement = mysqlConnect.connect().prepareStatement(
                                        "SELECT * FROM sym_table"
                                );
                                ResultSet resultSet = preparedStatement.executeQuery();
                                while (resultSet.next()) {
                                    PreparedStatement psLatestPrice = mysqlConnect.connect().prepareStatement(
                                            "SELECT * FROM companydata WHERE companydata.symbols = ? " +
                                                    "AND companydata.latestPrice " + latestPriceCondition + " ?"
                                    );
                                    psLatestPrice.setString(1, resultSet.getString(1));
                                    psLatestPrice.setDouble(2, latestPriceValue);
                                    ResultSet rsLatestPrice = psLatestPrice.executeQuery();
                                    while (rsLatestPrice.next()) {
                                        PreparedStatement preparedStatement1 = mysqlConnect.connect().prepareStatement(
                                                "SELECT * FROM companydata WHERE companydata.symbols = ? AND companydata.latestVolume " + volumeCondition + " ?"
                                        );

                                        preparedStatement1.setString(1, rsLatestPrice.getString(1));
                                        preparedStatement1.setDouble(2, volumeValue);
                                        ResultSet resultSet1 = preparedStatement1.executeQuery();
                                        while (resultSet1.next()) {
                                            //System.out.printf("%n%s", resultSet1.getString( 1 ));
                                            PreparedStatement preparedStatement2 = mysqlConnect.connect().prepareStatement(
                                                    "SELECT * FROM keystat WHERE symbols = ?" +
                                                            "AND keystat.month3ChangePercent " + month3Condtion + " ?"
                                            );
                                            preparedStatement2.setString(1, resultSet1.getString(1));
                                            preparedStatement2.setDouble(2, month3Value);
                                            ResultSet resultSet2 = preparedStatement2.executeQuery();
                                            while (resultSet2.next()) {
                                                System.out.printf("%n%s", resultSet2.getString( 1 ));
                                                PreparedStatement psDay30ChangePercent = mysqlConnect.connect().prepareStatement(
                                                        "SELECT * FROM keystat WHERE symbols = ?" +
                                                                "AND keystat.day30ChangePercent " + day30Condition + " ?"
                                                );
                                                psDay30ChangePercent.setString(1, resultSet2.getString(1));
                                                psDay30ChangePercent.setDouble(2, day30Value);
                                                ResultSet rsDay30Change = psDay30ChangePercent.executeQuery();

                                                while (rsDay30Change.next()) {
                                                    
                                                    System.out.printf("%nData: %s : Latest Price : %f : Latest Volume : %f : Month3 Change Percent : %f " +
                                                                    "%nDay 30 Change Percent : %f",
                                                            resultSet1.getString(1),
                                                            resultSet1.getBigDecimal(6),
                                                            resultSet1.getBigDecimal(8),
                                                            resultSet2.getBigDecimal(8),
                                                            rsDay30Change.getBigDecimal(11));
                                                            PreparedStatement preparedStatement3 = mysqlConnect.connect().prepareStatement(
                                                                    "INSERT INTO screenedlist (symbol, name, volume) " +
                                                                            "VALUES (?, ?, ?)"
                                                            );
                                                            preparedStatement3.setString(1, resultSet1.getString(1));
                                                            preparedStatement3.setString( 2,resultSet1.getString(1));
                                                            preparedStatement3.setBigDecimal(3,
                                                                    resultSet1.getBigDecimal(8));
                                                            preparedStatement3.executeUpdate();
                                                }
                                            }
                                        }
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            } finally {
                                countDownLatch.countDown();
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
    public static void filterStocks1(double between1, double and1, int between2, int and2) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = mysqlConnect.connect().prepareStatement(
                    "SELECT * FROM sym_table"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 1;
            while (resultSet.next()) {
                PreparedStatement preparedStatement1 = mysqlConnect.connect().prepareStatement(
                        "SELECT * FROM keystat WHERE symbols = ?" +
                                "AND keystat.month3ChangePercent BETWEEN " + between1 + " AND " + and1
                );
                preparedStatement1.setString(1, resultSet.getString(1));
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {

                    BigDecimal bigDecimal = resultSet1.getBigDecimal(8);
                    Double double1 = bigDecimal.doubleValue();
                    Double convertValue = (100 * double1);
                    PreparedStatement preparedStatement2 = mysqlConnect.connect().prepareStatement(
                            "SELECT * FROM companydata WHERE symbols = ?" +
                                    "AND companydata.latestVolume BETWEEN " + between2 +" AND " + and2
                    );
                    preparedStatement2.setString(1, resultSet1.getString(1));
                    ResultSet resultSet2 = preparedStatement2.executeQuery();
                    while (resultSet2.next()) {
                        System.out.printf("%n%d : Data %s : 3 Month Change Percent %f : Latest Volume %f : Latest Price %f", i++,
                                resultSet1.getString(1), convertValue, resultSet2.getBigDecimal(9), resultSet2.getBigDecimal(7));
                    }


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public static void filterStocks2() {
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement1;
        try {
            preparedStatement = mysqlConnect.connect().prepareStatement(
                    "SELECT * FROM sym_table"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 1;
            while (resultSet.next()) {
                preparedStatement1 = mysqlConnect.connect().prepareStatement(
                        "SELECT * FROM companydata WHERE symbols = ?"
                );
                preparedStatement1.setString(1, resultSet.getString(1));
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                while (resultSet1.next()) {
                    try {

                        BigDecimal week52High = resultSet1.getBigDecimal(13);
                        BigDecimal week52Low = resultSet1.getBigDecimal(14);
                        BigDecimal latestPrice = resultSet1.getBigDecimal(7);
                        Double latestPrice1 = latestPrice.doubleValue();
                        Double week52High1 = week52High.doubleValue();
                        Double week52Low1 = week52Low.doubleValue();
                        Double changeFrom52WeekHighAndLow = (((week52High1 - week52Low1)/(week52Low1))*100);

                        Double percentOff52WeekHigh  = (1 - (latestPrice1/week52High1));
                        Double percentOff52WeekLow  = ((latestPrice1/week52Low1) - 1);
                        BigDecimal volume = resultSet1.getBigDecimal(12);
                        Double latestVolume = volume.doubleValue();
                        if (changeFrom52WeekHighAndLow <= 4.00) {
                            if (latestPrice1 <= 25.00) {

                                    System.out.printf("%n %d : Symbol : %s : Price : %f : Average Volume : %f : Change From 52 Week High And Low : %f",
                                            i++, resultSet1.getString(1),latestPrice1, latestVolume, changeFrom52WeekHighAndLow);

            //                      System.out.printf("%n Data : %s : Change Percent From 52 Week High And Low : %f : Percent off -> 52 Week high : %f : Percent off -> 52 Week low : %f",
            //                                resultSet1.getString(1), changeFrom52WeekHighAndLow, percentOff52WeekHigh, percentOff52WeekLow);
//                                    System.out.printf("%n %d : Symbol : %s : Price : %f : Latest Volume : %f : Change From 52 Week High And Low : %f",
//                                            i++, resultSet1.getString(1),latestPrice1, resultSet1.getBigDecimal(9), changeFrom52WeekHighAndLow);


                            }
                            //                            findVolumeBasedOffSymbols(resultSet1.getString(1), changeFrom52WeekHighAndLow);
                        }


                    } catch (NullPointerException e) {
                        e.addSuppressed(new Throwable(e));
                    }
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }

    private static void findVolumeBasedOffSymbols(String string, Double changeFrom52WeekHighAndLow) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = mysqlConnect.connect().prepareStatement(
                    "SELECT * FROM companydata WHERE symbols = ?" +
                            "AND companydata.latestVolume BETWEEN 0 AND 1"
            );
            preparedStatement.setString(1, string);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%nSymbol : %s : Change From 52 Week High And Low : %f : And Volume : %f", string, changeFrom52WeekHighAndLow, resultSet.getBigDecimal(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getStartValueForLatestVolume() {
        return startValueForLatestVolume;
    }

    public static void setStartValueForLatestVolume(int startValueForLatestVolume) {
        StockScreening.startValueForLatestVolume = startValueForLatestVolume;
    }

    public static int getEndValueForLatestVolume() {
        return endValueForLatestVolume;
    }

    public static void setEndValueForLatestVolume(int endValueForLatestVolume) {
        StockScreening.endValueForLatestVolume = endValueForLatestVolume;
    }
}
