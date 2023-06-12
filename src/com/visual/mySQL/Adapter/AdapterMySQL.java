package com.visual.mySQL.Adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdapterMySQL {
	private static AdapterMySQL instancia = null;
	private static Connection connection = null;
	
	public static AdapterMySQL getInstancia() {
		if(instancia == null) {
			instancia = new AdapterMySQL();
			instancia.Connection();
		}
		return instancia;
	}
	
	public void Connection() {
		String url = "jdbc:mysql://localhost:3307/ventas?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
		String user = "user.javafx";
		String password = "08162001eyssy";
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println(connection.getClass().getCanonicalName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

}
