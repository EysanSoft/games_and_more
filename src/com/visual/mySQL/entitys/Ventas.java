package com.visual.mySQL.entitys;

public class Ventas {
	private int idProduct;
	private String nombreDelProducto;
	private double subTotal;
	private int unidadesEnVenta;
	private String fechaDeVenta;
	private int numeroDeCompra;
	
	public Ventas(){}
	
	public Ventas(int idProduct, String nombreDelProducto, double subTotal, int unidadesEnVenta, String fechaDeVenta, int numeroDeCompra) {
		super();
		this.idProduct = idProduct;
		this.nombreDelProducto = nombreDelProducto;
		this.subTotal = subTotal;
		this.unidadesEnVenta = unidadesEnVenta;
		this.fechaDeVenta = fechaDeVenta;
		this.numeroDeCompra = numeroDeCompra;
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

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getUnidadesEnVenta() {
		return unidadesEnVenta;
	}

	public void setUnidadesEnVenta(int unidadesEnVenta) {
		this.unidadesEnVenta = unidadesEnVenta;
	}

	public String getFechaDeVenta() {
		return fechaDeVenta;
	}

	public void setFechaDeVenta(String fechaDeVenta) {
		this.fechaDeVenta = fechaDeVenta;
	}
	
	public int getNumeroDeCompra() {
		return numeroDeCompra;
	}

	public void setNumeroDeCompra(int numeroDeCompra) {
		this.numeroDeCompra = numeroDeCompra;
	}

	@Override
	public String toString() {
		return "Ventas [idProduct=" + idProduct + ", nombreDelProducto=" + nombreDelProducto + ", subTotal=" + subTotal
				+ ", unidadesEnVenta=" + unidadesEnVenta + ", fechaDeVenta=" + fechaDeVenta + ", numeroDeCompra=" + numeroDeCompra + "]";
	}
}
