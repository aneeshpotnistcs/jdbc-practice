package com.tcs.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo {
	public static void main(String[] args) {
		String DB_URL = "jdbc:mysql://localhost/practice3";
		String DB_USER = "root";
		String DB_PASSWORD = "Nuvelabs123$";
		
		try(Connection connection = DriverManager.getConnection(DB_URL,DB_USER, DB_PASSWORD);
			Statement statement = connection.createStatement();){
			create(statement);
			retrieve(statement);
//			update(statement);
//			delete(statement);
			List<String> regions = retrieveWithCondition(statement, "A%");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	private static void delete(Statement statement) throws SQLException {
		statement.executeUpdate("Delete from REGIONS where REGION_ID=3");
	}

	private static List<String> retrieveWithCondition(Statement statement, String string) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SELECT * from regions where REGION_NAME LIKE 'A%'");
		List<String> regions = new ArrayList<String>();
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1));
			System.out.println(resultSet.getNString("REGION_NAME"));
			regions.add(resultSet.getNString("REGION_NAME"));
			System.out.println(regions);
		}
		return null;
	}

	private static void update(Statement statement) throws SQLException {
		statement.executeUpdate("Update REGIONS set REGION_NAME='South America' where REGION_ID=2");
	}

	private static void retrieve(Statement statement) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SELECT * from regions");
		List<String> regions = new ArrayList<String>();
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1));
			System.out.println(resultSet.getNString("REGION_NAME"));
			regions.add(resultSet.getNString("REGION_NAME"));
//			System.out.println(regions);
		}
	}

	private static void create(Statement statement) throws SQLException {
		statement.execute("INSERT INTO REGIONS VALUES(3,'Australia')");
		
	}
}
