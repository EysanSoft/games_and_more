package com.visual.mySQL.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.visual.mySQL.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ViewCajeroController implements Initializable {

    @FXML
    private ImageView iconoProcesoDeVentas;
    
    @FXML
    private ImageView iconoActualizarPerfil;
    
    @FXML
    private ImageView iconoArqueoDeCaja;


    @FXML
    void iconoProcesoDeVentasOnMouseClicked(MouseEvent event) {
    	Main.newStage("ViewProcesoDeVentas", "Games and More! - Proceso de Ventas");
    }
    
    @FXML
    void iconoActualizarPerfilOnMouseClicked(MouseEvent event) {
    	Main.newStage("ViewActualizarPerfil", "Games and More! - Actualizar Perfil");
    }
    
    @FXML
    void iconoArqueoDeCajaOnMouseClicked(MouseEvent event) {
    	Main.newStage("ViewArqueoDeCaja", "Games and More! - Arqueo de Caja");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iconoProcesoDeVentas.setImage(new Image("resources/CajaRegistadora.png"));
		iconoActualizarPerfil.setImage(new Image("resources/actualizarPerfil.png"));
		iconoArqueoDeCaja.setImage(new Image("resources/arqueoDeCaja.png"));
	}
}
