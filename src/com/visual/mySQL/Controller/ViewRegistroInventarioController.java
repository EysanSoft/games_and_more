package com.visual.mySQL.Controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.visual.mySQL.Main;
import com.visual.mySQL.Adapter.AdapterMySQL;
import com.visual.mySQL.DAO.ProductsDAO;
import com.visual.mySQL.entitys.Products;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ViewRegistroInventarioController implements Initializable {

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldPrecio;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldUnidades;

    @FXML
    private Button botonAgregar;
    
    @FXML
    private Button botonSalir;
    
    @FXML
    private ComboBox<String> listaDeProductos;
    
    @FXML
    private TextField textFieldEliminar;

    @FXML
    private Button botonEliminar;
    
    @FXML
    private TextField textFieldIdVerificar1;

    @FXML
    private TextField textFieldPrecioActualizar;

    @FXML
    private Button botonActualizarPrecio;
    
    @FXML
    private Button botonReporte;

    @FXML
    private TextField textFieldIdVerificar2;

    @FXML
    private TextField textFieldUnidadesActualizar;

    @FXML
    private Button botonActualizarUnidades;

    @FXML
    void botonAgregarOnMouseClicked(MouseEvent event) {
    	
    	Products product = new Products();
    	ProductsDAO dao = new ProductsDAO();
    	
    	if(textFieldId.getText().length() !=0 && textFieldUnidades.getText().length() != 0 && textFieldPrecio.getText().length() != 0 && textFieldNombre.getText().length() != 0) {
    		String cadenaIdProducto = textFieldId.getText();
        	int idProducto = Integer.parseInt(cadenaIdProducto);
        	
        	String cadenaUnidades = textFieldUnidades.getText();
        	int unidades = Integer.parseInt(cadenaUnidades);
        	
        	String cadenaPrecio = textFieldPrecio.getText();
        	double precio = Double.parseDouble(cadenaPrecio);
        	
    		product.setIdProduct(idProducto);
        	product.setNombreDelProducto(textFieldNombre.getText());
        	product.setPrecio(precio);
        	product.setUnidadesDisponibles(unidades);
        	dao.insert(product);
        	textFieldId.clear();
        	textFieldUnidades.clear();
        	textFieldPrecio.clear();
        	textFieldNombre.clear();
    	}
    	else {
    		System.out.println("Por favor rellene todos los campos para agregar un producto");
    		textFieldId.clear();
        	textFieldUnidades.clear();
        	textFieldPrecio.clear();
        	textFieldNombre.clear();
    	}
    	   
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
    
    @FXML
    void botonEliminarOnMouseClicked(MouseEvent event) {
      	ProductsDAO dao = new ProductsDAO();
    	String cadenaIdEliminar = textFieldEliminar.getText();
    	int idEliminar = Integer.parseInt(cadenaIdEliminar);
    	dao.delete(idEliminar);
    	textFieldEliminar.clear();
    }
    
    @FXML
    void botonActualizarPrecioOnMouseClicked(MouseEvent event) {
    	ProductsDAO dao = new ProductsDAO();
    	String cadenaIdProducto = textFieldIdVerificar1.getText();
    	int idProducto = Integer.parseInt(cadenaIdProducto);
    	String cadenaPrecio = textFieldPrecioActualizar.getText();
    	double precio = Double.parseDouble(cadenaPrecio);
    	dao.update(idProducto, precio);
    	textFieldIdVerificar1.clear();
    	textFieldPrecioActualizar.clear();
    }
    
    @FXML
    void botonActualizarUnidadesOnMouseClicked(MouseEvent event) {
    	ProductsDAO dao = new ProductsDAO();
    	String cadenaIdProducto = textFieldIdVerificar2.getText();
    	int idProducto = Integer.parseInt(cadenaIdProducto);
    	String cadenaUnidadesDisponibles = textFieldUnidadesActualizar.getText();
    	int UnidadesDisponibles = Integer.parseInt(cadenaUnidadesDisponibles);
    	dao.update(idProducto, UnidadesDisponibles);
    	textFieldIdVerificar2.clear();
    	textFieldUnidadesActualizar.clear();
    }
    
    @FXML
    void botonReporteOnMouseClicked(MouseEvent event) {
    	AdapterMySQL conector = new AdapterMySQL();
    	JasperPrint jasperPrintWindow;
    	try {
    		jasperPrintWindow = JasperFillManager.fillReport(
    				"Reportes/products.jasper", null, conector.getConnection());
    		JasperExportManager.exportReportToPdfFile(jasperPrintWindow, "Informes/informeProductos.pdf");
    		JasperViewer.viewReport(jasperPrintWindow,false);		
    	} catch(JRException e){
    		e.printStackTrace();
    	}
    }

    @FXML
    void botonSalirOnMouseClicked(MouseEvent event) {
    	Main.secondStage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		desplegarLista();
	}

}
