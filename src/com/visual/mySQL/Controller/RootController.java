package com.visual.mySQL.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.visual.mySQL.Main;
import com.visual.mySQL.DAO.UserDAO;
import com.visual.mySQL.entitys.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RootController implements Initializable {
	
	@FXML
	private TextField textUsuario;

	@FXML
	private PasswordField textPassword;

    @FXML
    private Button iniciarSesion;

    @FXML
    private Button salir;

    @FXML
    private Label logo;
    
    @FXML
    private ImageView imagenLogo;

    @FXML
    private Label labelError;

    @FXML
    void botonSalirOnMouseClicked(MouseEvent event) {
    	System.exit(1);
    }

    @FXML
    void botonIniciarSesionOnMouseClicked(MouseEvent event) {
    	String mensajeError = "";
    	User user;
    	UserDAO dao = new UserDAO();
    	user = dao.getUser(textUsuario.getText());
    	if(user != null) {
    		if(user.getPassword().equals(textPassword.getText())) {
    			if(user.isAdministrador() == true) {
    				System.out.println("Bienvenido " + user.getNombre());
        			try {
    					Main.setFXML("ViewGerente","Games and More! - Gerente");
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				
    			}
    			else {
    				System.out.println("Bienvenido " + user.getNombre());
        			try {
    					Main.setFXML("ViewCajero","Games and More! - Cajero");
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		}
    		else {
    			mensajeError = "La contraseña es incorrecta.";
    			textPassword.clear();
    		}
    	}
    	else {
    		mensajeError = "El usuario no existe.";
    		
    		textUsuario.clear();
    		textPassword.clear();
    	}
    	labelError.setText(mensajeError);
    }
    
    @FXML
	public void initialize(URL arg0, ResourceBundle arg1) {
    	imagenLogo.setImage(new Image("resources/controlSNES3.png"));
	}

}