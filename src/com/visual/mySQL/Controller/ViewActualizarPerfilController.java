package com.visual.mySQL.Controller;

import com.visual.mySQL.Main;
import com.visual.mySQL.DAO.UserDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ViewActualizarPerfilController {

    @FXML
    private TextField textFieldIdVerificacion;

    @FXML
    private TextField textFieldNuevoNombre;

    @FXML
    private TextField textFieldNuevoPaterno;

    @FXML
    private TextField textFieldNuevoMaterno;

    @FXML
    private Button botonActualizarDatosPersonales;

    @FXML
    private PasswordField passwordFieldVerificarContrase�a2;

    @FXML
    private TextField textFieldNuevoPassword;

    @FXML
    private TextField textFieldNuevoLogin;

    @FXML
    private Button botonActualizarPasswordLogin;
    
    @FXML
    private Button botonSalir;

    @FXML
    void botonActualizarDatosPersonalesOnMouseClicked(MouseEvent event) {
    	String cadenaIdVerificar = textFieldIdVerificacion.getText();
    	int idUsuarioVerificar = Integer.parseInt(cadenaIdVerificar);
    	
    	UserDAO dao = new UserDAO();
    	boolean verificarAdministrador = dao.getUserAdministrador(idUsuarioVerificar);
    	
    	if(verificarAdministrador == true) {
    		System.out.println("Este ID pertenece a un administrador, no a un cajero");
    		textFieldIdVerificacion.clear();
        	textFieldNuevoNombre.clear();
        	textFieldNuevoPaterno.clear();
        	textFieldNuevoMaterno.clear();
    	}
    	else {
    		//error
    		dao.update(idUsuarioVerificar, textFieldNuevoNombre.getText(), textFieldNuevoPaterno.getText(), textFieldNuevoMaterno.getText());
        	textFieldIdVerificacion.clear();
        	textFieldNuevoNombre.clear();
        	textFieldNuevoPaterno.clear();
        	textFieldNuevoMaterno.clear();
    	}
    }

    @FXML
    void botonActualizarPasswordLoginOnMouseClicked(MouseEvent event) {
    	UserDAO dao = new UserDAO();
    	boolean verificarAdministrador = dao.getUserAdministrador(passwordFieldVerificarContrase�a2.getText());
    	
    	if(verificarAdministrador == true) {
    		System.out.println("Esta contrase�a pertenece a un administrador, no a un cajero");
    		passwordFieldVerificarContrase�a2.clear();
            textFieldNuevoPassword.clear();
            textFieldNuevoLogin.clear();
    	}
    	else {
    		dao.update(passwordFieldVerificarContrase�a2.getText(), textFieldNuevoPassword.getText(), textFieldNuevoLogin.getText());
            passwordFieldVerificarContrase�a2.clear();
            textFieldNuevoPassword.clear();
            textFieldNuevoLogin.clear();
    	}

    }
    
    @FXML
    void botonSalirOnMouseClicked(MouseEvent event) {
    	Main.secondStage.close();
    }
}