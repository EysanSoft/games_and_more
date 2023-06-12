package com.visual.mySQL.Controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.visual.mySQL.Main;
import com.visual.mySQL.DAO.ProductsDAO;
import com.visual.mySQL.DAO.UserDAO;
import com.visual.mySQL.entitys.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ViewRegistroUsuariosController implements Initializable {

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldPaterno;

    @FXML
    private TextField textFieldMaterno;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldNuevoId;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button botonConfirmar;

    @FXML
    private CheckBox checkBoxCajero;

    @FXML
    private CheckBox checkBoxAdministrador;
    
    @FXML
    private Button botonEliminarUsuario;

    @FXML
    private PasswordField passwordFieldVerificarContraseña;
    
    @FXML
    private TextField textFieldIdVerificacion;

    @FXML
    private TextField textFieldNuevoPaterno;

    @FXML
    private TextField textFieldNuevoNombre;

    @FXML
    private TextField textFieldNuevoMaterno;

    @FXML
    private Button botonActualizarDatosPersonales;
    
    @FXML
    private PasswordField passwordFieldVerificarContraseña2;

    @FXML
    private TextField textFieldNuevoPassword;

    @FXML
    private TextField textFieldNuevoLogin;

    @FXML
    private Button botonActualizarPasswordLogin;
    
    @FXML
    private ComboBox<String> listaDeUsuarios;

    @FXML
    private Button botonSalir;
    
    private boolean eleccion;
    
    @FXML
    void botonConfirmarOnMouseClicked(MouseEvent event) {
    	User user = new User();
    	UserDAO dao = new UserDAO();
    	
    	if(textFieldNombre.getText().length() !=0 && textFieldPaterno.getText().length() != 0 && textFieldMaterno.getText().length() != 0
    			&& textFieldLogin.getText().length() != 0 && textFieldNuevoId.getText().length() != 0  && textFieldPassword.getText().length() != 0) {
    		
    		String cadenaIdUsuario = textFieldNuevoId.getText();
        	int idUsuario = Integer.parseInt(cadenaIdUsuario);
    		
			user.setIdUser(idUsuario);
			user.setNombre(textFieldNombre.getText());
			user.setApellidoPaterno(textFieldPaterno.getText());
			user.setApellidoMaterno(textFieldMaterno.getText());
			user.setLogin(textFieldLogin.getText());
			user.setPassword(textFieldPassword.getText());
			user.setAdministrador(eleccion);
			dao.insert(user);
			textFieldNombre.clear();
    		textFieldPaterno.clear();
    		textFieldMaterno.clear();
    		textFieldLogin.clear();
    		textFieldNuevoId.clear();
    		textFieldPassword.clear();
    		checkBoxCajero.setSelected(false);
    		checkBoxAdministrador.setSelected(false);
    		System.out.println("Ingreso Exitoso");
    	}
    	else {
    		System.out.println("Por favor rellene todos los campos para agregar un nuevo usuario");
    		textFieldNombre.clear();
    		textFieldPaterno.clear();
    		textFieldMaterno.clear();
    		textFieldLogin.clear();
    		textFieldNuevoId.clear();
    		textFieldPassword.clear();
    		checkBoxCajero.setSelected(false);
    		checkBoxAdministrador.setSelected(false);
    	}

    }

    @FXML
    void checkBoxAdministradorOnMouseClicked(MouseEvent event) {
    	checkBoxCajero.setSelected(false);
    	eleccion = true;
    }

    @FXML
    void checkBoxCajeroOnMouseClicked(MouseEvent event) {
    	checkBoxAdministrador.setSelected(false);
    	eleccion = false;
    }
    
    @FXML
    void botonEliminarUsuarioOnMouseClicked(MouseEvent event) {
    	UserDAO dao = new UserDAO();
    	String cadenaIdEliminar = passwordFieldVerificarContraseña.getText();
    	dao.delete(cadenaIdEliminar);
    	passwordFieldVerificarContraseña.clear();
    }
    
    @FXML
    void botonActualizarDatosPersonalesOnMouseClicked(MouseEvent event) {
    	String cadenaIdVerificar = textFieldIdVerificacion.getText();
    	int idUsuarioVerificar = Integer.parseInt(cadenaIdVerificar);
    	
    	UserDAO dao = new UserDAO();
    	boolean verificarAdministrador = dao.getUserAdministrador(idUsuarioVerificar);
    	
    	if(verificarAdministrador == false) {
    		System.out.println("Este ID pertenece a un cajero, no a un administrador");
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
    	boolean verificarAdministrador = dao.getUserAdministrador(passwordFieldVerificarContraseña2.getText());
    	
    	if(verificarAdministrador == false) {
    		System.out.println("Esta contraseña pertenece a un cajero, no a un administrador");
    		passwordFieldVerificarContraseña2.clear();
            textFieldNuevoPassword.clear();
            textFieldNuevoLogin.clear();
    	}
    	else {
    		//error
    		dao.update(passwordFieldVerificarContraseña2.getText(), textFieldNuevoPassword.getText(), textFieldNuevoLogin.getText());
            passwordFieldVerificarContraseña2.clear();
            textFieldNuevoPassword.clear();
            textFieldNuevoLogin.clear();
    	}
    }
    
    @FXML
    void botonSalirOnMouseClicked(MouseEvent event) {
    	Main.secondStage.close();
    }
    
    public void desplegarLista() {
    	try {
    		UserDAO dao = new UserDAO();
    		String sql = "select * from user";
    		PreparedStatement statement = dao.connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				listaDeUsuarios.getItems().add("ID: " + results.getInt(1) + "\nNombre: "
						+ results.getString(2) + "\nApellido Paterno: " + results.getString(3)
						+ "\nApellidoMaterno: " + results.getString(4));
			}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		desplegarLista();
	}
	
}

