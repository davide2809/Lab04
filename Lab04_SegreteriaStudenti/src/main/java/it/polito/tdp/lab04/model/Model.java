package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;


public class Model {
	
	private CorsoDAO dao;
	private StudenteDAO daoS;
	
	public Model() {
		dao=new CorsoDAO();
		daoS=new StudenteDAO();
	}

	public List<Corso> getElencoCorsi() {
		return dao.getTuttiICorsi();
	}
	public List<Studente> getListaStudenti() {
		return daoS.getListaStudenti();
	}
	/**
	 * Metodo che ricerca studente per matricola
	 * @param matricola
	 * @return Studente ricercato
	 */
	public Studente getStudenteMatricola(Integer matricola) {
		for(Studente s:daoS.getListaStudenti()) {
			if(s.equals(new Studente(matricola,null,null,null)))
				return s;
		}
		return null;
	}
	public List<Studente> getIscrittiCorso(Corso corso) {
		return dao.getStudentiIscrittiAlCorso(corso);
	}
	public List<Corso> getListaCorsiPerStudente(Integer matricola) {
		return daoS.getListaCorsiPerStudente(matricola);
	}
	
	

}
