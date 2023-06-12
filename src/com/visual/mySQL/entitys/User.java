package com.visual.mySQL.entitys;

public class User {
	private int idUser;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String login;
	private String password;
	private boolean administrador;
	
	public User() {}

	public User(int idUser, String nombre, String apellidoPaterno, String apellidoMaterno, String login,
			String password, boolean administrador) {
		super();
		this.idUser = idUser;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.login = login;
		this.password = password;
		this.administrador = administrador;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", login=" + login + ", password=" + password + ", administrador=" + administrador + "]";
	}
	
}
