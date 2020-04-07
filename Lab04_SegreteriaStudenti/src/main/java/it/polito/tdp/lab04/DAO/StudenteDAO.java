package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	/**
	 * Metodo che da una lista con tutti gli studenti
	 */
	public List<Studente> getListaStudenti() {
		final String sql="SELECT * FROM studente ";
		List<Studente> studenti=new ArrayList<Studente>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while (rs.next()) {
				Integer matricola=rs.getInt("matricola");
				String nome= rs.getString("nome");
				String cognome=rs.getString("cognome");
				String cds=rs.getString("cds");
				
				studenti.add(new Studente(matricola,cognome,nome,cds));
			}
			conn.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return studenti;
	}
	/**
	 * Metodo che trova I corsi al quale è iscritto uno studente
	 * @param studente
	 * @return
	 */
	public List<Corso> getListaCorsiPerStudente(Integer matricola) {
		final String sql="SELECT c.codins, c.nome, c.crediti, c.pd\r\n" + 
				"FROM corso AS c, iscrizione AS i,studente AS s\r\n" + 
				"WHERE c.codins=i.codins AND i.matricola=s.matricola AND s.matricola= ? ";
		List<Corso> corsi=new ArrayList<Corso>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			while (rs.next()) {

				String codins = rs.getString("c.codins");
				int numeroCrediti = rs.getInt("c.crediti");
				String nome = rs.getString("c.nome");
				int periodoDidattico = rs.getInt("c.pd");
				
				corsi.add(new Corso(codins,numeroCrediti,nome,periodoDidattico));
			}
			conn.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return corsi;
	}

}
