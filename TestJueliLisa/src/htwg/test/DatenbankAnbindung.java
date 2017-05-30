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
	Bogen bogen = null;

	
	public Bogen readBogen(String id) {
		bogen = new Bogen();
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

		Bogen bog =  new Bogen();
		bog.setFrage1(result.getString("BoFrage1"));
		bog.setId(result.getInt("BoID"));
		return bog;
	}
	
//	public static void main(String[] args) {
//		Bogen bogen = new Bogen();
//		DatenbankAnbindung dba = new DatenbankAnbindung(bogen);
//		bogen = dba.readBogen("1");
//		System.out.println(bogen.getFrage1());
//		
//	}
}
