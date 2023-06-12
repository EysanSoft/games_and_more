package com.visual.mySQL.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.visual.mySQL.Adapter.AdapterMySQL;
import com.visual.mySQL.entitys.Ventas;

public class VentasDAO {
	public Connection connection = null;
	private final int ACCEPT = 1;
	
	public VentasDAO() {
		AdapterMySQL conector = AdapterMySQL.getInstancia();
		connection = conector.getConnection();
	}
	
	public boolean insert(Ventas ventas) {
		boolean resultado = false;
		if (connection != null) {
			String sql = "insert into ventas values(?,?,?,?,?,?)";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, ventas.getIdProduct());
				statement.setString(2, ventas.getNombreDelProducto());
				statement.setDouble(3, ventas.getSubTotal());
				statement.setInt(4, ventas.getUnidadesEnVenta());
				statement.setString(5, ventas.getFechaDeVenta());
				statement.setInt(6, ventas.getNumeroDeCompra());
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public boolean deleteVenta(int numeroDeCompra) {
		boolean resultado = false;
		if (connection != null) {
			String sql = "delete from ventas where numeroDeCompra=?";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, numeroDeCompra);
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}
	
	public int getNumVentaAlto() {
		int num = 0;
		if (connection != null) {
			String sql = "SELECT max(numeroDeCompra) FROM ventas";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				//statement.setInt(1, idUser);
				ResultSet results = statement.executeQuery();
				results.next();
				if (results.getRow() == ACCEPT) {
					num = results.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return num;
	}
}
