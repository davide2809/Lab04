package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model=new Model();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCorso"
    private ComboBox<Corso> boxCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscrittiCorso"
    private Button btnCercaIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="lblMatricola"
    private TextField lblMatricola; // Value injected by FXMLLoader
    
    @FXML // fx:id="checkBox"
    private CheckBox checkBox; // Value injected by FXMLLoader

    @FXML // fx:id="lblNome"
    private TextField lblNome; // Value injected by FXMLLoader

    @FXML // fx:id="lblCognome"
    private TextField lblCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String matr=lblMatricola.getText();
    	if(matr.equals("")) {
    		txtResult.appendText("Inserisci una matricola\n");
    		return;
    	}	
    	Integer matricola;
    	try {
    		matricola=Integer.parseInt(matr);
    	} catch(NumberFormatException e) {
    		txtResult.appendText("Metti numeri per la matricola!\n");
    		return;
    	}
    	List<Corso> listaCorsi=new ArrayList<Corso>();
    	listaCorsi.addAll(model.getListaCorsiPerStudente(matricola));
    	for(Corso c:listaCorsi) {
    		txtResult.appendText(c.toString1()+"\n");
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	Corso corso=boxCorso.getValue();
    	if(corso==null) {
    		txtResult.appendText("Inserisci corso!\n");
    		return;
    	}
    	List<Studente> studenti=new LinkedList<Studente>();
    	studenti.addAll(model.getIscrittiCorso(corso));
    	for(Studente s:studenti) {
    		txtResult.appendText(s.toString()+"\n");
    	}
    	return;
    	}

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	lblNome.clear();
    	lblCognome.clear();
    	txtResult.clear();
    	lblMatricola.clear();
    }
    
    @FXML
    void doCheckBox(ActionEvent event) {
    	String matr=lblMatricola.getText();
    	if(matr.equals("")) {
    		txtResult.appendText("Inserisci una matricola\n");
    		return;
    	}	
    	Integer matricola;
    	try {
    		matricola=Integer.parseInt(matr);
    	} catch(NumberFormatException e) {
    		txtResult.appendText("Metti numeri per la matricola!\n");
    		return;
    	}
    	try {
    	String nome=model.getStudenteMatricola(matricola).getNome();
    	String cognome=model.getStudenteMatricola(matricola).getCognome();
    	lblNome.setText(nome);
    	lblCognome.setText(cognome);
    	}catch(Exception e) {
    		txtResult.appendText("Studente Inesistente!\n");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCorso != null : "fx:id=\"boxCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblMatricola != null : "fx:id=\"lblMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblNome != null : "fx:id=\"lblNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblCognome != null : "fx:id=\"lblCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
    	this.model=model;
    	boxCorso.getItems().addAll(this.model.getElencoCorsi());
    }
    
}