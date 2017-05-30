package htwg.test;

import java.sql.*;

public class SQLiteJDBC {
	public static void main(String args[]) {
		Connection c = null;
		try {
			Class.forName("");
			c = DriverManager.getConnection("jdbc:sqlite:WEB-INF/Datenbank.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}
