package com.visual.mySQL.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.visual.mySQL.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ViewGerenteController implements Initializable {

    @FXML
    private ImageView iconoRegistroDeInventario;
    
    @FXML
    private ImageView iconoRegistroDeUsuarios;
    
    @FXML
    private ImageView iconoRegistroDeVentas;
    
    @FXML
    void iconoRegistroDeInventarioOnMouseClicked(MouseEvent event) {
    	Main.newStage("ViewRegistroInventario", "Games and More! - Registro de Inventario");
    }
    
    @FXML
    void iconoRegistroDeUsuariosOnMouseClicked(MouseEvent event) {
    	Main.newStage("ViewRegistroUsuarios", "Games and More! - Registro de Usuarios");
    }
    
    @FXML
    void iconoRegistroDeVentasOnMouseClicked(MouseEvent event) {
    	Main.newStage("ViewRegistroVentas", "Games and More! - Registro de Ventas");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iconoRegistroDeInventario.setImage(new Image("resources/registroDeInventario.png"));
		iconoRegistroDeUsuarios.setImage(new Image("resources/iconoListaDeUsuarios.png"));
		iconoRegistroDeVentas.setImage(new Image("resources/registroDeVentas.png"));
	}
}