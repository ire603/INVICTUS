package victus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Company {
	
	private String symbol;
	private String name;
	private static MysqlConnect mysqlConnect;

	public Company(String symbol, String name) {
		super();
		this.symbol = symbol;
		this.name = name;
	}

	public Company(MysqlConnect mysqlConnect) {
		Company.mysqlConnect = mysqlConnect;
	}
	
	
	public String getSymbol() {
		return symbol;
	}
	public String getName() {
		return name;
	}

	// This method returns the value that is shown in the combobox.
	public String toString() {
		return this.name;
	}
	
	public static List<Company> getListCompanies() {
		List<Company> list = new ArrayList<Company>();
		try {
			PreparedStatement preparedStatement = mysqlConnect.connect().prepareStatement(
					"SELECT * FROM screenedlist"
			);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String string = resultSet.getString(1);
//				String string1 = resultSet.getString(1);
				Company company = new Company(string, string);
				list.add(company);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}