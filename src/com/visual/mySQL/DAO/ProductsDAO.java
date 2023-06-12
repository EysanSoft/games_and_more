package com.visual.mySQL.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.visual.mySQL.Adapter.AdapterMySQL;
import com.visual.mySQL.entitys.Products;

public class ProductsDAO {
	public Connection connection = null;
	private final int ACCEPT = 1;
	
	public ProductsDAO() {
		AdapterMySQL conector = AdapterMySQL.getInstancia();
		connection = conector.getConnection();
	}
	
	public boolean insert(Products products) {
		boolean resultado = false;
		if (connection != null) {
			String sql = "insert into products values(?,?,?,?)";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, products.getIdProduct());
				statement.setString(2, products.getNombreDelProducto());
				statement.setDouble(3, products.getPrecio());
				statement.setInt(4, products.getUnidadesDisponibles());
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}
	
	public Products getProducts(int idProduct) {
		Products products = null; 
		if (connection != null) {
			String sql = "select * from products where idProduct=?";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, idProduct);
				ResultSet results = statement.executeQuery();
				results.next();
				if (results.getRow() == ACCEPT) {
					products = new Products();
					products.setIdProduct(results.getInt(1));
					products.setNombreDelProducto(results.getString(2));
					products.setPrecio(results.getDouble(3));
					products.setUnidadesDisponibles(results.getInt(4));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return products;
	}
	
	public List<Products> getAllUsers() {
		List<Products> productsL = new ArrayList<>();
		
		if (connection != null) {
			String sql = "select * from products";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while (results.next()) {
					int idProduct = results.getInt(1);
					String nombreDelProducto = results.getString(2);
					Double precio = results.getDouble(3);
					int unidadesDisponibles = results.getInt(4);
					Products products = new Products(idProduct,nombreDelProducto,precio,unidadesDisponibles);
					productsL.add(products);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productsL;
	}

	public boolean delete(int idProduct) {
		boolean resultado = false;
		
		if (connection != null) {
			String sql = "delete from products where idProduct=?";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, idProduct);
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public boolean update(Products products) {
		boolean resultado = false;
		
		if (connection != null) {
			String sql = "update user set "
					+ "nombre=?, apellidoPaterno=?, apellidoMaterno=?, "
					+ "login=?, password=? where idProduct=?";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, products.getIdProduct());
				statement.setString(2, products.getNombreDelProducto());
				statement.setDouble(3, products.getPrecio());
				statement.setInt(4, products.getUnidadesDisponibles());
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
		
	}
	
	public void update(int idProduct, double precio) {
		//boolean resultado = false;
		if (connection != null) {
			String sql = "update products set precio=? where idProduct=?";
			try {
				PreparedStatement statement = connection.prepareStatement("select * from products");
				PreparedStatement statement2 = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					statement2.setDouble(1,precio);
					statement2.setInt(2,idProduct);
				}
				statement2.executeUpdate();
				//if (statement.executeUpdate() == ACCEPT)
					//resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//return resultado;
	}
	
	public void update(int idProduct, int unidadesDisponibles) {
		//boolean resultado = false;
		if (connection != null) {
			String sql = "update products set unidadesDisponibles=? where idProduct=?";
			try {
				PreparedStatement statement = connection.prepareStatement("select * from products");
				PreparedStatement statement2 = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					statement2.setInt(1,unidadesDisponibles);
					statement2.setInt(2,idProduct);
				}
				statement2.executeUpdate();
				//if (statement.executeUpdate() == ACCEPT)
					//resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//return resultado;
	}
	/*
	public boolean update(int indexColumn, String value, int idUser) {
		boolean resultado = false;
		
		if (connection != null) {
			String sql = "update user set ";
			switch(indexColumn) {
			case 2: sql = sql + "nombre=? "; break;
			case 3: sql = sql + "apellidoPaterno=? "; break;
			case 4: sql = sql + "apellidoMaterno=? "; break;
			case 5: sql = sql + "login=? "; break;
			case 6: sql = sql + "password=? "; break;
			}
			sql = sql + " where idUser=?";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, value);
				statement.setInt(2, idUser);
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
		
	}

	public boolean update(int indexColumn, String value, String nombre) {
		boolean resultado = false;
		
		if (connection != null) {
			String sql = "update user set ";
			switch(indexColumn) {
			case 2: sql = sql + "nombre=? "; break;
			case 3: sql = sql + "apellidoPaterno=? "; break;
			case 4: sql = sql + "apellidoMaterno=? "; break;
			case 5: sql = sql + "login=? "; break;
			case 6: sql = sql + "password=? "; break;
			}
			sql = sql + " where nombre=?";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, value);
				statement.setString(2, nombre);
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
		
	}
*/
}
