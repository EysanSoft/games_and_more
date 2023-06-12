package com.visual.mySQL.entitys;

public class GananciasTotales {
	private int llaveGanancias;
	private int totalProductosVendidos;
	private double dineroTotalGanado;
	
	public GananciasTotales(){}
	
	public GananciasTotales(int llaveGanancias, int  totalProductosVendidos, double dineroTotalGanado) {
		super();
		this.llaveGanancias = llaveGanancias;
		this.totalProductosVendidos = totalProductosVendidos;
		this.dineroTotalGanado = dineroTotalGanado;
	}

	public int getLlaveGanancias() {
		return llaveGanancias;
	}

	public void setLlaveGanancias(int llaveGanancias) {
		this.llaveGanancias = llaveGanancias;
	}

	public int getTotalProductosVendidos() {
		return totalProductosVendidos;
	}

	public void setTotalProductosVendidos(int totalProductosVendidos) {
		this.totalProductosVendidos = totalProductosVendidos;
	}

	public double getDineroTotalGanado() {
		return dineroTotalGanado;
	}

	public void setDineroTotalGanado(double dineroTotalGanado) {
		this.dineroTotalGanado = dineroTotalGanado;
	}
	
}
