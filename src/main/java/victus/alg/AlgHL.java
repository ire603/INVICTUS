package victus.alg;

import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;
import victus.IEX;
import victus.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlgHL {
	
	public static void main(String[] args) {
		MysqlConnect mysqlConnect = new MysqlConnect();
	}
	public static void begin(MysqlConnect mysqlConnect) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = mysqlConnect.connect().prepareStatement( "SELECT * FROM symbol" );
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Quote quote =
					   IEX.initiateIEX().executeRequest( new QuoteRequestBuilder().withSymbol(  resultSet.getString( 1 )).build());
				
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
