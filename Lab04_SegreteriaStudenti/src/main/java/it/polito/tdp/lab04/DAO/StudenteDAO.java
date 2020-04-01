package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	
	/**
	 * Dato una studente di cui si conosce la matricola, restituisce lo Studente se presente nel db
	 * @param s Studente da ricercare 
	 * @return Studnete corrispondente a quella matricola
	 */
	public Studente cercaStudente(Studente s) {
		
		String sql= "SELECT matricola, cognome, nome FROM studente WHERE MATRICOLA= ?"; 
		Studente sTemp=null; 
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1,  s.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				sTemp= new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome")); 
			}
			conn.close();
			return sTemp; 
				}catch (SQLException e) {
					// e.printStackTrace();
					throw new RuntimeException("Errore Db", e);
				}
			
	
	}
	
	/**
	 * Dato uno {@code Studente} fornisce l'elenco dei corsi ai quali egli e' iscritto
	 * @param s {@code Studente} 
	 * @return elenco corsi ai quali {@code Studente} e' iscritto
	 */
	public List<Corso> getCorsiStudente(Studente s){
		
		String sql="SELECT corso.codins, corso. nome, corso. crediti, corso.pd " + 
				"FROM corso, iscrizione, studente " + 
				"WHERE corso.codins=iscrizione.codins AND studente.matricola=iscrizione.matricola " + 
				"AND studente.matricola= ?";
		
		List<Corso>corsi= new LinkedList<Corso>(); 
		
		try {
			Connection conn= ConnectDB.getConnection(); 
			PreparedStatement st= conn.prepareStatement(sql); 
			st.setInt(1, s.getMatricola());
			ResultSet res= st.executeQuery(); 
			
			while(res.next()) {
				Corso c= new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
				corsi.add(c);
				
			}
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(); 
			
			
		}
		return corsi; 
	
	}
}
