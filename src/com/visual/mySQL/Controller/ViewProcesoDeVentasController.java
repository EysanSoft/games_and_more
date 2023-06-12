package com.visual.mySQL.Controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import com.visual.mySQL.Main;
import com.visual.mySQL.DAO.GananciasTotalesDAO;
import com.visual.mySQL.DAO.ProductsDAO;
import com.visual.mySQL.DAO.VentasDAO;
import com.visual.mySQL.entitys.GananciasTotales;
import com.visual.mySQL.entitys.Products;
import com.visual.mySQL.entitys.Ventas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewProcesoDeVentasController implements Initializable {
	
	 @FXML
	 private AnchorPane anchorPaneCajero;
	
	 @FXML
	 private Button botonOk;

	 @FXML
	 private TextField textFieldObtenerUnidades;

	 @FXML
	 private TextField textFieldObtenerId;
	 
	 @FXML
	 private Label labelTotal;

	 @FXML
	 private Button botonTerminar;

	 @FXML
	 private Button botonCancelar;

	 @FXML
	 private Button botonSalir;
	 
	 @FXML
	 private Label labelCambio;

	 @FXML
	 private TextField textFieldIngreso;

	 @FXML
	 private Button botonOKIngreso;
	 
	 @FXML
	 private Button botonSiguiente;
	 
	 @FXML
	 private ComboBox<String> listaDeProductos;
	 
	 @FXML
	 private ComboBox<String> listaDeVentas;

	 private double total = 0;
	 private int productosTotales = 0;
	 private int contarClicks = 0;
	 private int numVentas;
	 

	 @FXML
	 void botonOkOnMouseClicked(MouseEvent event) {
		 int idProducto = Integer.parseInt(textFieldObtenerId.getText());
		 int unidadesIngresadas = Integer.parseInt(textFieldObtenerUnidades.getText());
		 Date date = new Date();
		 SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/YYYY"); 
		 String fecha = dateForm.format(date);
		 
		 Products product;
    	 ProductsDAO dao = new ProductsDAO();
    	 product = dao.getProducts(idProducto);
    	 System.out.println(product);
    	 System.out.println(fecha);
    	 
    	 if(unidadesIngresadas > product.getUnidadesDisponibles()) {
    		 System.out.println("No hay suficientes unidades disponibles..");
    	 }
    	 else {
    		 System.out.println("Procesando..");
    		 dao.update(idProducto, product.getUnidadesDisponibles() - unidadesIngresadas);
    		 
    		 VentasDAO dao2 = new VentasDAO();
    		 Ventas ventas = new Ventas();
    		 
    		 contarClicks++;
    		 if(contarClicks == 1) {
    			 anchorPaneCajero.getChildren().remove(botonSalir);
    			 numVentas++;
    		 }
    		 
    		 ventas.setIdProduct(product.getIdProduct());
    		 ventas.setNombreDelProducto(product.getNombreDelProducto());
    		 ventas.setSubTotal(product.getPrecio() * unidadesIngresadas);
    		 ventas.setUnidadesEnVenta(unidadesIngresadas);
    		 ventas.setFechaDeVenta(fecha);
    		 ventas.setNumeroDeCompra(ventas.getNumeroDeCompra() + numVentas);
    		 
    		 dao2.insert(ventas);
    		 System.out.println(ventas);
    		 total = total + ventas.getSubTotal();
    		 productosTotales =  productosTotales + unidadesIngresadas;
    		 labelTotal.setText("Total: $" + total);
    		 textFieldObtenerId.clear();
    		 textFieldObtenerUnidades.clear();
    	 }
	 }
	 
	 @FXML
	 void botonTerminarOnMouseClicked(MouseEvent event) {
		anchorPaneCajero.getChildren().add(labelCambio);
		anchorPaneCajero.getChildren().add(botonOKIngreso);
		anchorPaneCajero.getChildren().add(textFieldIngreso);
		anchorPaneCajero.getChildren().remove(textFieldObtenerId);
		anchorPaneCajero.getChildren().remove(textFieldObtenerUnidades);
		anchorPaneCajero.getChildren().remove(botonOk);
		anchorPaneCajero.getChildren().remove(botonTerminar);
		anchorPaneCajero.getChildren().remove(botonCancelar);
	 }
	 
	 @FXML
	 void botonCancelarOnMouseClicked(MouseEvent event) {
		 VentasDAO dao = new VentasDAO();
		 dao.deleteVenta(numVentas);
		 total = 0;
		 productosTotales = 0;
		 labelTotal.setText("Total: $0");
	 }
	 
	 @FXML
	 void botonOKIngresoOnMouseClicked(MouseEvent event) {
		 GananciasTotales gananciasTotales;
		 GananciasTotalesDAO dao = new GananciasTotalesDAO();
		 gananciasTotales = dao.getGananciasTotales(193286);
		 
		 double efectivoIngresado = Double.parseDouble(textFieldIngreso.getText());
		 if(efectivoIngresado < total) {
			 System.out.println("Ingreso insuficiente, solicitele al cliente ingresar mas efectivo.");
		 }
		 else {
			 
			 dao.update(193286, gananciasTotales.getTotalProductosVendidos() + productosTotales, gananciasTotales.getDineroTotalGanado() + total);
			 total = total - efectivoIngresado;
			 if(total < 0) {
				 total = total * -1;
				 labelCambio.setText("Cambio: $" + total);
			 }
			 System.out.println("¡Venta existosa!.");
			 anchorPaneCajero.getChildren().remove(botonOKIngreso);
			 anchorPaneCajero.getChildren().remove(textFieldIngreso);
			 anchorPaneCajero.getChildren().add(botonSalir);
			 anchorPaneCajero.getChildren().add(botonSiguiente);
			 contarClicks = 0;
		 }

	 }
	 
	 @FXML
	 void botonSiguienteOnMouseClicked(MouseEvent event) {
		 anchorPaneCajero.getChildren().remove(botonSiguiente);
		 anchorPaneCajero.getChildren().remove(labelCambio);
		 anchorPaneCajero.getChildren().add(textFieldObtenerId);
		 anchorPaneCajero.getChildren().add(textFieldObtenerUnidades);
		 anchorPaneCajero.getChildren().add(botonOk);
		 anchorPaneCajero.getChildren().add(botonTerminar);
		 anchorPaneCajero.getChildren().add(botonCancelar);
		 total = 0;
		 productosTotales = 0;
		 labelCambio.setText("Cambio: $0");
		 labelTotal.setText("Total: $0");
	 }
	 
	 @FXML
	 void botonSalirOnMouseClicked(MouseEvent event) {
		 Main.secondStage.close();
	 }
	 
	 public void desplegarLista() {
	    	try {
	    		ProductsDAO dao = new ProductsDAO();
	    		String sql = "select * from products";
	    		PreparedStatement statement = dao.connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					listaDeProductos.getItems().add("ID: " + results.getInt(1) + "\nNombre del producto: "
							+ results.getString(2) + "\nPrecio Unitario: " + results.getLong(3)
							+ "\nUnidades Disponibles: " + results.getInt(4));
				}
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	 
	 public void desplegarLista2() {
	    	try {
	    		VentasDAO dao = new VentasDAO();
	    		String sql = "select * from ventas";
	    		PreparedStatement statement = dao.connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					listaDeVentas.getItems().add("ID: " + results.getInt(1) + "\nNombre del producto: "
							+ results.getString(2) + "\nSubtotal: $" + results.getLong(3)
							+ "\nUnidades Vendidas: " + results.getInt(4) + "\nFecha de Venta: " + results.getString(5)
							+ "\nVenta #" + results.getInt(6));
				}
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		VentasDAO dao = new VentasDAO();
		numVentas = dao.getNumVentaAlto();
		anchorPaneCajero.getChildren().remove(labelCambio);
		anchorPaneCajero.getChildren().remove(botonOKIngreso);
		anchorPaneCajero.getChildren().remove(textFieldIngreso);
		anchorPaneCajero.getChildren().remove(botonSiguiente);
		desplegarLista();
		desplegarLista2();
	}

}
