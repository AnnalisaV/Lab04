package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.LinkedList;
import java.util.List;


import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				Corso c= new Corso(codins, numeroCrediti, nome, periodoDidattico); 
				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		String sql="SELECT studente.matricola, cognome, studente.nome " + 
				"FROM corso, iscrizione, studente " + 
				"WHERE corso.codins= iscrizione.codins AND iscrizione.matricola=studente.matricola " + 
				"AND corso.nome= ? " ; 
		
		List<Studente>studenti= new LinkedList<Studente>(); 
		
		try {
			Connection conn= ConnectDB.getConnection(); 
			PreparedStatement st= conn.prepareStatement(sql); 
			st.setString(1, corso.getNome());
			ResultSet res= st.executeQuery(); 
			
			while(res.next()) {
				Studente s= new Studente(res.getInt("matricola"), res.getString("cognome"), res.getString("nome"));
				studenti.add(s);
				
			}
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(); 
			
			
		}
		return studenti; 
	
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}


	public boolean studenteIscrittoAlCorso(Studente s, Corso c) {
		
		String sql= "SELECT studente.matricola, iscrizione.codins FROM iscrizione, studente " + 
				"WHERE studente.matricola=iscrizione.matricola " + 
				"AND studente.matricola= ? " + 
				"AND iscrizione.codins= ?";
		
		// no struttura dati perche' voglio solo sapere sì o no (esiste)
				try {
					Connection conn= ConnectDB.getConnection(); 
					PreparedStatement st= conn.prepareStatement(sql); 
					st.setInt(1, s.getMatricola());
					st.setString(2,  c.getCodins());
					
					ResultSet res= st.executeQuery(); 
					
					if(res.next()) {
						conn.close(); 
						//ok ha la riga quindi una soluzione l'ha trovata 
						return true; 
						
					}else {
						conn.close(); 
						return false; // non ha trovato manco una riga 
					}
					
					}catch(SQLException e) {
					throw new RuntimeException(); 
				}
	
	}


	public boolean iscriviStudente(Studente s, Corso c) {
		String sql ="INSERT INTO iscrizione (matricola, codins) " + 
				"VALUES (?, ?) "; 
		boolean modifica= false; 
		try {
			Connection conn= ConnectDB.getConnection(); 
			PreparedStatement st= conn.prepareStatement(sql); 
			st.setInt(1, s.getMatricola());
			st.setString(2,  c.getCodins());
			
			
			int righe= st.executeUpdate(); // ritorna il numero di righe che sono state modificate 
			
			if(righe!=0) {
				// allora ne ha modificata qualcuna quindi tutto e' andato bene 
				modifica=true; 
			}
			
			conn.close();
			}catch(SQLException e) {
			throw new RuntimeException(); 
		}
		return modifica; 
	}

}
