package htwg.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.PrintWriter;
import java.sql.*;

@SuppressWarnings("serial")
public class LisasServlet extends HttpServlet {

	ResultSet result = null;
	Statement st = null;
	Connection con = null;
	String kette = null;
	Bogen bogen = null;

	public Bogen readBogen(String id) {

		try {
			con = DriverManager.getConnection("jdbc:sqlite:Datenbank.db");
			st = con.createStatement();
			result = st.executeQuery("SELECT * FROM BoBogen WHERE BoId = " + id);

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
		bogen.setId(result.getInt("BoId"));
		return bogen;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String bogenId = req.getParameter("ID");
		Bogen bogen = readBogen(bogenId);

		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		String htmlResponse = "<html>";
		htmlResponse += "<head>";
		htmlResponse += "<body>";
	    htmlResponse +=  "<h2>Ihr Benutzername lautet: " + bogen.getFrage1() + "</h2>";
		htmlResponse += "</body>";
		htmlResponse += "</html>";		
		writer.println(htmlResponse);
	}
}
