package com.visual.mySQL.Controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.visual.mySQL.Main;
import com.visual.mySQL.DAO.GananciasTotalesDAO;
import com.visual.mySQL.DAO.VentasDAO;
import com.visual.mySQL.entitys.GananciasTotales;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ViewRegistroVentasController implements Initializable {

    @FXML
    private ComboBox<String> listaDeVentasTotales;

    @FXML
    private Button botonSalir;

    @FXML
    private Button botonGenerarReporte;
    
    @FXML
    private Label labelEstadoCaja;
    
    @FXML
    private Label labelEstadoProductos;

    @FXML
    void botonGenerarReporteOnMouseClicked(MouseEvent event) {
    	//Integrar_Metodo
    }

    @FXML
    void botonSalirOnMouseClicked(MouseEvent event) {
     	Main.secondStage.close();
    }
    
    public void desplegarLista() {
    	try {
    		VentasDAO dao = new VentasDAO();
    		String sql = "select * from ventas";
    		PreparedStatement statement = dao.connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				listaDeVentasTotales.getItems().add("ID: " + results.getInt(1) + "\nNombre del producto: "
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
		desplegarLista();
		GananciasTotales gananciasTotales;
		GananciasTotalesDAO dao = new GananciasTotalesDAO();
		gananciasTotales = dao.getGananciasTotales(193286);
		labelEstadoCaja.setText("La caja tiene en este momento: $" + gananciasTotales.getDineroTotalGanado());
		labelEstadoProductos.setText("El numero de productos vendidos es de: " + gananciasTotales.getTotalProductosVendidos());
	}

}