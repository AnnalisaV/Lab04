package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
