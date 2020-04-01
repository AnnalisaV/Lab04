package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import javafx.scene.control.MenuItem;

public class Model {

	private CorsoDAO corsoDAO= new CorsoDAO(); 
	private StudenteDAO studenteDAO= new StudenteDAO(); 
	
	
	public List<Corso> getTuttiCorsi(){
		return corsoDAO.getTuttiICorsi(); 
	}
	
	public Studente cercaStudente(Studente s) {
		return studenteDAO.cercaStudente(s); 
	}
	
	public List<Studente> studentiIscrittiAlCorso(Corso c){
		return corsoDAO.getStudentiIscrittiAlCorso(c); 																																																														
	}

	public List<Corso> corsiDelloStudente(Studente s) {
		return studenteDAO.getCorsiStudente(s);
	}
}
