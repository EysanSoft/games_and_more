package com.visual.mySQL.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.visual.mySQL.Adapter.AdapterMySQL;
import com.visual.mySQL.entitys.GananciasTotales;

public class GananciasTotalesDAO {
	public Connection connection = null;
	private final int ACCEPT = 1;
	
	public GananciasTotalesDAO() {
		AdapterMySQL conector = AdapterMySQL.getInstancia();
		connection = conector.getConnection();
	}
	
	public void update(int llaveGanancias, int totalProductosVendidos, double dineroTotalGanado) {
		if (connection != null) {
			String sql = "update gananciasTotales set totalProductosVendidos=?, dineroTotalGanado=? where llaveGanancias=?";
			try {
				PreparedStatement statement = connection.prepareStatement("select * from products");
				PreparedStatement statement2 = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					statement2.setInt(1,totalProductosVendidos);
					statement2.setDouble(2,dineroTotalGanado);
					statement2.setInt(3,llaveGanancias);
				}
				statement2.executeUpdate();
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
		}
	}
	
	public GananciasTotales getGananciasTotales(int llaveGanancias) {
		GananciasTotales gananciasTotales = null; 
		if (connection != null) {
			String sql = "select * from gananciasTotales where llaveGanancias=?";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, llaveGanancias);
				ResultSet results = statement.executeQuery();
				results.next();
				if (results.getRow() == ACCEPT) {
					gananciasTotales = new GananciasTotales();
					gananciasTotales.setLlaveGanancias(results.getInt(1));
					gananciasTotales.setTotalProductosVendidos(results.getInt(2));
					gananciasTotales.setDineroTotalGanado(results.getDouble(3));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gananciasTotales;
	}
	
}
