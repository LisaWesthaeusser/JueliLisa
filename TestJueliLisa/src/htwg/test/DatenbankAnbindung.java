package htwg.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatenbankAnbindung {

	ResultSet result = null;
	Statement st = null;
	Connection con = null;
	String kette = null;
	Bogen bogen;

	public Bogen readBogen(String id) {

		try {
			con = DriverManager.getConnection("jdbc:sqlite:war/WEB-INF/Datenbank.db");
			st = con.createStatement();
			result = st.executeQuery("SELECT * FROM BoBogen WHERE BoID = " + id);

			if (result.next()) {
				bogen = SQLSelect(id, result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bogen;
	}

	private Bogen SQLSelect(String id, ResultSet result) throws SQLException {

		
		bogen.setFrage1(result.getString("BoFrage1"));
		bogen.setId(result.getInt("BoID"));
		return bogen;
	}
	
	public static void main(String[] args) {
		DatenbankAnbindung dba = new DatenbankAnbindung();
		Bogen bogen = dba.readBogen("1");
		System.out.println(bogen.getFrage1());
		
	}
}
