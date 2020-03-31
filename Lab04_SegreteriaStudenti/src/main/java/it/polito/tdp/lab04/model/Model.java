package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import javafx.scene.control.MenuItem;

public class Model {

	private CorsoDAO corsoDAO= new CorsoDAO(); 
	
	
	public List<String> getTuttiNomiCorsi(){
		List<String>nomiCorsi= new LinkedList<>(); 
		for(Corso c : corsoDAO.getTuttiICorsi()) {
			nomiCorsi.add(c.getNome()); 
		}
		return  nomiCorsi; 
	}
}
