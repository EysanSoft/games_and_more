package com.visual.mySQL.entitys;

public class Products {
	private int idProduct;
	private String nombreDelProducto;
	private double precio;
	private int unidadesDisponibles;
	
	public Products(){}
	
	public Products(int idProduct, String nombreDelProducto, double precio, int unidadesDisponibles) {
		super();
		this.idProduct = idProduct;
		this.nombreDelProducto = nombreDelProducto;
		this.precio = precio;
		this.unidadesDisponibles = unidadesDisponibles;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNombreDelProducto() {
		return nombreDelProducto;
	}

	public void setNombreDelProducto(String nombreDelProducto) {
		this.nombreDelProducto = nombreDelProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getUnidadesDisponibles() {
		return unidadesDisponibles;
	}

	public void setUnidadesDisponibles(int unidadesDisponibles) {
		this.unidadesDisponibles = unidadesDisponibles;
	}
	
	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nombreDelProducto=" + nombreDelProducto + ", precio=" + precio
				+ ", unidadesDisponibles=" + unidadesDisponibles + "]";
	}

}
