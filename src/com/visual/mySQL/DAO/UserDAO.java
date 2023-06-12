package com.visual.mySQL.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.visual.mySQL.Adapter.AdapterMySQL;
import com.visual.mySQL.entitys.User;

public class UserDAO {
	public Connection connection = null;
	private final int ACCEPT = 1;
	
	public UserDAO() {
		AdapterMySQL conector = AdapterMySQL.getInstancia();
		connection = conector.getConnection();
	}
	
	public boolean insert(User user) {
		boolean resultado = false;
		if (connection != null) {
			String sql = "insert into user values(?,?,?,?,?,?,?)";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, user.getIdUser());
				statement.setString(2, user.getNombre());
				statement.setString(3, user.getApellidoPaterno());
				statement.setString(4, user.getApellidoMaterno());
				statement.setString(5, user.getLogin());
				statement.setString(6, user.getPassword());
				statement.setBoolean(7, user.isAdministrador());
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}
	
	public User getUser(int idUser) {
		User user = null; 
		if (connection != null) {
			String sql = "select * from user where idUser=?";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, idUser);
				ResultSet results = statement.executeQuery();
				results.next();
				if (results.getRow() == ACCEPT) {
					user = new User();
					user.setIdUser(results.getInt(1));
					user.setNombre(results.getString(2));
					user.setApellidoPaterno(results.getString(3));
					user.setApellidoMaterno(results.getString(4));
					user.setLogin(results.getString(5));
					user.setPassword(results.getString(6));
					user.setAdministrador(results.getBoolean(7));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User getUser(String login) {
		User user = null;
		if (connection != null) {
			String sql = "select * from user where login=?";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, login);
				ResultSet results = statement.executeQuery();
				results.next();
				if (results.getRow() == ACCEPT) {
					user = new User();
					user.setIdUser(results.getInt(1));
					user.setNombre(results.getString(2));
					user.setApellidoPaterno(results.getString(3));
					user.setApellidoMaterno(results.getString(4));
					user.setLogin(results.getString(5));
					user.setPassword(results.getString(6));
					user.setAdministrador(results.getBoolean(7));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return user;
			
	}
	
	public boolean getUserAdministrador(int idUser) {
		boolean rol = true;
		if (connection != null) {
			String sql = "select * from user where idUser=?";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, idUser);
				ResultSet results = statement.executeQuery();
				results.next();
				if (results.getRow() == ACCEPT) {
					rol = results.getBoolean(7);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return rol;
	}
	
	public boolean getUserAdministrador(String password) {
		boolean rol = true;
		if (connection != null) {
			String sql = "select * from user where password=?";
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, password);
				ResultSet results = statement.executeQuery();
				results.next();
				if (results.getRow() == ACCEPT) {
					rol = results.getBoolean(7);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return rol;
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		
		if (connection != null) {
			String sql = "select * from user";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while (results.next()) {
					int idUser = results.getInt(1);
					String nombre = results.getString(2);
					String apellidoPaterno = results.getString(3);
					String apellidoMaterno = results.getString(4);
					String login = results.getString(5);
					String password = results.getString(6);
					Boolean administrador = results.getBoolean(7);
					User user = new User(idUser,nombre,apellidoPaterno,apellidoMaterno,login,password,administrador);
					users.add(user);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}
	
	public boolean delete(int idUser) {
		boolean resultado = false;
		
		if (connection != null) {
			String sql = "delete from user where idUser=?";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, idUser);
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}
	
	public boolean delete(String password) {
		boolean resultado = false;
		
		if (connection != null) {
			String sql = "delete from user where password=?";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, password);
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}
	
	public boolean update(User user) {
		boolean resultado = false;
		
		if (connection != null) {
			String sql = "update user set "
					+ "nombre=?, apellidoPaterno=?, apellidoMaterno=?, "
					+ "login=?, password=?, administrador=? where idUser=?";
			
			try {
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, user.getNombre());
				statement.setString(2, user.getApellidoPaterno());
				statement.setString(3, user.getApellidoMaterno());
				statement.setString(4, user.getLogin());
				statement.setString(5, user.getPassword());
				statement.setInt(6, user.getIdUser());
				statement.setBoolean(7, user.isAdministrador());
				if (statement.executeUpdate() == ACCEPT)
					resultado = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
		
	}
	
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
			case 7: sql = sql + "administrador=? "; break;
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
	//error
	public void update(int idUser, String nombre, String apellidoPaterno, String apellidoMaterno) {
		if (connection != null) {
			String sql = "update user set nombre=?, apellidoPaterno=?, apellidoMaterno=? where idUser=?";
			try {
				PreparedStatement statement = connection.prepareStatement("select * from user");
				PreparedStatement statement2 = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					statement2.setString(1,nombre);
					statement2.setString(2,apellidoPaterno);
					statement2.setString(3,apellidoMaterno);
					statement2.setInt(4,idUser);
				}
				statement2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(String password, String newPassword, String login) {
		if (connection != null) {
			String sql = "update user set password=?, login=? where password=?";
			try {
				PreparedStatement statement = connection.prepareStatement("select * from user");
				PreparedStatement statement2 = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					statement2.setString(1,newPassword);
					statement2.setString(2,login);
					statement2.setString(3,password);
				}
				statement2.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
			case 7: sql = sql + "administrador=? "; break;
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

}
