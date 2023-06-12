package com.visual.mySQL.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.visual.mySQL.Main;
import com.visual.mySQL.DAO.GananciasTotalesDAO;
import com.visual.mySQL.entitys.GananciasTotales;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ViewArqueoDeCajaController implements Initializable {

    @FXML
    private Label labelEstadoCaja;

    @FXML
    private Button botonRetirar;

    @FXML
    private Button botonSalir;

    @FXML
    private TextField textFieldRetirar;
  
    @FXML
    void botonRetirarOnMouseClicked(MouseEvent event) {
    	double monto;
    	GananciasTotales gananciasTotales;
		GananciasTotalesDAO dao = new GananciasTotalesDAO();
		gananciasTotales = dao.getGananciasTotales(193286);
		monto = Double.parseDouble(textFieldRetirar.getText());
		if(monto > gananciasTotales.getDineroTotalGanado()) {
			System.out.println("¡El monto a retirar es mas que lo que hay en la caja!");
		}
		else {
			dao.update(193286, gananciasTotales.getTotalProductosVendidos(), gananciasTotales.getDineroTotalGanado() - monto);
			System.out.println("Retiro Exitoso!");
			labelEstadoCaja.setText("La caja tiene en este momento: $" + gananciasTotales.getDineroTotalGanado());
		}

    }

    @FXML
    void botonSalirOnMouseClicked(MouseEvent event) {
    	Main.secondStage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GananciasTotales gananciasTotales;
		GananciasTotalesDAO dao = new GananciasTotalesDAO();
		gananciasTotales = dao.getGananciasTotales(193286);
		labelEstadoCaja.setText("La caja tiene en este momento: $" + gananciasTotales.getDineroTotalGanado());
		
	}

}