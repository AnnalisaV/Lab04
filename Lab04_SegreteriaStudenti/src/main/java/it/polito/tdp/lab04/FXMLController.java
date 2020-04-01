package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model; 
	
	public void setModel(Model model) {
		
		this.model= model; 
		
		//popolare il menu a tendina
	  comboBoxCorsi.getItems().addAll(this.model.getTuttiCorsi()); 
	  comboBoxCorsi.getItems().add(new Corso("", 0, "", 0)); // aggiungo una riga vuota
      comboBoxCorsi.setValue(null); // cosa viene visualizato di partenza 
		
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboBoxCorsi;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCheck;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    /**
     * Data la matricola di uno {@code Studente}, elenca i corsi ai quali e' iscritto
     * @param event
     */
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
          String matricolaInput= txtMatricola.getText(); 
    	
    	if (matricolaInput.length()==0) {
    		txtRisultato.appendText("Campo matricola vuoto! Inserire una matricola. \n");
    		return; 
    	}
    	int matricola; 
    	
        try {
    	       matricola= Integer.parseInt(matricolaInput); 
    		
    	}catch(NumberFormatException nfe) {
    		
    		txtRisultato.appendText("Devi inserire una matricola (NUMERI) !! \n");
    		return; 
    	}
    	
    	
    	Studente s= this.model.cercaStudente(new Studente (matricola,null,null)); 
    	if (s== null) {
    		txtRisultato.appendText("ERORE, Studente inesistente!\n");
    	}
    	else {
    		// esiste lo studente 
    		txtRisultato.appendText("Studente "+s.getNome()+" "+s.getCognome()+" iscritto ai corsi:\n");
        	List<Corso> listaCorsi= this.model.corsiDelloStudente(s);

        	if (listaCorsi.size()==0) {
        		txtRisultato.appendText("Non vi sono corsi ai quali lo studente e' iscritto \n");
        	}
        	else {
        	for (Corso c : listaCorsi) {
        		txtRisultato.appendText(c+"\n");
        	}
    		
    	}
    	}
    	
    	
    	
    	

    }

    /** 
     * Selezionando un corso, viene fornito l'elenco degli studenti ad esso iscritti
     * @param event
     */
    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {

    	if (this.comboBoxCorsi.getValue()==null) {
    		txtRisultato.appendText("SELEZIONARE UN CORSO !!! \n");
    		return; 
    	}
    	
    	// e' stato selezionato un corso
    	txtRisultato.clear();
    	txtRisultato.appendText("Studenti iscritti al corso di  : "+this.comboBoxCorsi.getValue().getNome()+"\n");
    	List<Studente> listaStudenti= this.model.studentiIscrittiAlCorso(this.comboBoxCorsi.getValue()); 
    	
    	if (listaStudenti.size()==0) {
    		txtRisultato.appendText("Non vi sono studenti iscritti al corso \n");
    	}
    	else {
    	for (Studente s : listaStudenti) {
    		txtRisultato.appendText(s+"\n");
    	}
    	}
    }

    /**
     * Completamento automatico : data una matricola viene fornito il nome ed il cognome dello
     * studente corrispondente 
     * @param event 
     */
    @FXML
    void doCheck(ActionEvent event) {

    	String matricolaInput= txtMatricola.getText(); 
    	
    	if (matricolaInput.length()==0) {
    		txtRisultato.appendText("Campo matricola vuoto! Inserire una matricola. \n");
    		return; 
    	}
    	int matricola; 
    	
    	try {
    		
    		matricola= Integer.parseInt(matricolaInput); 
    		
    	}catch(NumberFormatException nfe) {
    		
    		txtRisultato.appendText("Devi inserire una matricola (NUMERI) !! \n");
    		return; 
    	}
    	
    	
    	Studente s= this.model.cercaStudente(new Studente (matricola,null,null)); 
    	if (s== null) {
    		txtRisultato.appendText("Studente non trovato!\n");
    	}
    	else {
    		// esiste lo studente quindi si fa auto compilazione
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	}
    	
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    /**
     * Pulizia di tutti i campi
     * @param event
     */
    @FXML
    void doReset(ActionEvent event) {

    	txtMatricola.clear(); 
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear(); 
    	
    }

    @FXML
    void initialize() {
        assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

