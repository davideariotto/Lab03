package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	
	private Dictionary model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label lblRed;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTime;

    @FXML
    void doClearText(ActionEvent event) {

    	txtResult.clear();
    	txtInput.clear();
    	lblRed.setText("");
        model.clear();
        lblTime.setText("");
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {

    	model.loadDictionary(comboBox.getValue());
    	List<String> lista = new ArrayList<String>();
    	String temp = txtInput.getText();
    	temp.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	for(String p: temp.split(" "))
    		lista.add(p.toLowerCase());
    	double time1 = System.nanoTime();
    	List<RichWord> lista2 = model.spellCheckText(lista);
    	double time2 = System.nanoTime();
    	double diff = (time2 - time1)/1000000;
    	lblTime.setText("Il controllo ha impiegato " + diff + " millisecondi");
    	txtResult.setText(model.mostraErrori(lista2));
    	lblRed.setText("Hai commesso " + model.contaErrori(lista2) + " errori!");	
    	
    }
    
    void setModel(Dictionary m) {
    	
    	this.model= m;
    	String languages[] = {"English","Italian"};
    	comboBox.getItems().addAll(languages);
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblRed != null : "fx:id=\"lblRed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

