package service;

import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.*;
import com.google.gson.Gson;


import meserreurs.MonException;
import metier.*;
import persistance.DialogueBd;

@Path("/mediatheque")
public class WService {
	
	
	/***************************************************/
	/***************Partie sur les adhérents **************/
	/*****************************************************/
	@POST
	@Path("/Adherents/ajout/{unAdh}")
	@Consumes("application/json")	
	public void insertionAdherent(String unAdherent) throws MonException {
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		Gson gson = new Gson();
		Adherent unAdh = gson.fromJson(unAdherent, Adherent.class);
		try {
			String mysql = "";
			mysql = "INSERT INTO adherent (nom_adherent, prenom_adherent, ville_adherent) ";
			mysql += " VALUES ( \'" + unAdh.getNomAdherent()+ "\', \'" + unAdh.getPrenomAdherent();
			mysql+="  \', \'"  + unAdh.getVilleAdherent() +  "\') ";
			
			unDialogueBd.insertionBD(mysql);
			
		} catch (MonException e) {
			throw e;
		}
	}
	
	
	@GET
	@Path("/Adherents")
	@Produces("application/json")
	public String rechercheLesAdherents() throws MonException {
		List<Object> rs;
		List<Adherent> mesAdherents = new ArrayList<Adherent>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";

			mysql = "SELECT * FROM adherent ORDER BY id_adherent ASC";

			rs = unDialogueBd.lecture(mysql);

			while (index < rs.size()) {

				// On crï¿½e un objet Adherent
				Adherent unAdh = new Adherent();
				unAdh.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
				unAdh.setNomAdherent(rs.get(index + 1).toString());
				unAdh.setPrenomAdherent(rs.get(index + 2).toString());
				unAdh.setVilleAdherent(rs.get(index + 3).toString());
				index = index + 4;

				mesAdherents.add(unAdh);
			}

			Gson gson = new Gson();
			String json = gson.toJson(mesAdherents);
			return json;

		} catch (MonException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@DELETE
	@Path("/Adherents/suppression/{id}")
	@Produces("application/json")
	public void supprimerLesAdherents(@PathParam("id") int id) throws MonException {
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";

			mysql = "DELETE FROM adherent WHERE id_adherent="+id;

			unDialogueBd.insertionBD(mysql);
		}catch (MonException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}



	/***************************************************/
	/***************Partie sur les oeuvres **************/
	/*****************************************************/
	@GET
	@Path("/Oeuvres")
	@Produces("application/json")
	public String rechercheLesOeuvres() throws MonException {
		List<Object> rs;
		List<Oeuvrevente> mesOeuvrevente = new ArrayList<Oeuvrevente>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";

			mysql = "SELECT id_oeuvrevente, titre_oeuvrevente FROM oeuvrevente ORDER BY id_oeuvrevente ASC";

			rs = unDialogueBd.lecture(mysql);

			while (index < rs.size()) {

				// On crï¿½e un objet Adherent
				Oeuvrevente uneOeuv = new Oeuvrevente();
				uneOeuv.setIdOeuvrevente(Integer.parseInt(rs.get(index + 0).toString()));
				uneOeuv.setTitreOeuvrevente(rs.get(index+1).toString());
				index = index + 2;


				mesOeuvrevente.add(uneOeuv);
			}

			Gson gson = new Gson();
			String json = gson.toJson(mesOeuvrevente);
			return json;

		} catch (MonException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@GET
	@Path("/Oeuvres/liste")
	@Produces("application/json")
	public String listerLesOeuvres() throws MonException {
		List<Object> rs;
		List<Oeuvrevente> mesOeuvres = new ArrayList<Oeuvrevente>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";

			mysql = "SELECT * FROM oeuvrevente ORDER BY id_oeuvrevente ASC";

			rs = unDialogueBd.lecture(mysql);

			while (index < rs.size()) {

				// On crï¿½e un objet Adherent
				Oeuvrevente uneOeu = new Oeuvrevente();
				uneOeu.setIdOeuvrevente(Integer.parseInt(rs.get(index + 0).toString()));
				uneOeu.setTitreOeuvrevente(rs.get(index + 1).toString());
				uneOeu.setEtatOeuvrevente(rs.get(index + 2).toString());
				uneOeu.setPrixOeuvrevente(Float.parseFloat(rs.get(index + 3).toString()));
				index = index + 5;

				mesOeuvres.add(uneOeu);
			}

			Gson gson = new Gson();
			String json = gson.toJson(mesOeuvres);
			return json;

		} catch (MonException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}


	@GET
	@Path("/Oeuvres/{id}")
	@Produces("application/json")
	public String rechercheUneOeuvre(@PathParam("id") int id) throws MonException {
		List<Object> rs;

		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";

			mysql = "SELECT titre_oeuvrevente, etat_oeuvrevente, prix_oeuvrevente, id_proprietaire FROM oeuvrevente WHERE id_oeuvrevente="+id;

			rs = unDialogueBd.lecture(mysql);
			int index = 0;

			// On crï¿½e un objet Adherent
			Oeuvrevente uneOeuv = new Oeuvrevente();
			uneOeuv.setIdOeuvrevente(id);
			uneOeuv.setTitreOeuvrevente(rs.get(index+0).toString());
			uneOeuv.setEtatOeuvrevente(rs.get(index+1).toString());
			uneOeuv.setPrixOeuvrevente(Float.parseFloat(rs.get(index + 2).toString()));

			float idProp = Float.parseFloat(rs.get(index+3).toString())*10;
			int idProprietaire = (int) idProp/10;

			mysql = "SELECT id_proprietaire, nom_proprietaire, prenom_proprietaire FROM proprietaire WHERE id_proprietaire=" + idProprietaire;

			rs = unDialogueBd.lecture(mysql);
			index = 0;

			uneOeuv.getProprietaire().setIdProprietaire(idProprietaire);
			uneOeuv.getProprietaire().setNomProprietaire(rs.get(index+1).toString());
			uneOeuv.getProprietaire().setPrenomProprietaire(rs.get(index+2).toString());

			Gson gson = new Gson();
			String json = gson.toJson(uneOeuv);
			return json;

		} catch (MonException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@POST
	@Path("/Oeuvres/ajout/{unOeu}")
	@Consumes("application/json")
	public void insertionOeuvre(String uneOeuvre) throws MonException {
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		Gson gson = new Gson();
		Oeuvrevente uneOeu = gson.fromJson(uneOeuvre, Oeuvrevente.class);
		try {
			String mysql = "";
			mysql = "INSERT INTO oeuvrevente (titre_oeuvrevente, etat_oeuvrevente, prix_oeuvrevente, id_proprietaire) ";
			mysql += " VALUES ( \'" + uneOeu.getTitreOeuvrevente()+ "\', \'L\' ," + uneOeu.getPrixOeuvrevente();
			mysql+=","  + uneOeu.getProprietaire().getIdProprietaire() +  ")";

			unDialogueBd.insertionBD(mysql);

		} catch (MonException e) {
			throw e;
		}
	}

	@DELETE
	@Path("/Oeuvres/suppression/{id}")
	@Produces("application/json")
	public void supprimerOeuvres(@PathParam("id") int id, String uneOeuvre) throws MonException {
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";
			Gson gson = new Gson();
			Oeuvrevente uneOeu = gson.fromJson(uneOeuvre, Oeuvrevente.class);

			mysql = "DELETE FROM oeuvrevente WHERE id_oeuvrevente="+id;

			unDialogueBd.insertionBD(mysql);
		}catch (MonException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}


	@POST//a tester
	@Path("/Oeuvres/modification/{id}")
	@Produces("application/json")
	public void modifierOeuvres(@PathParam("id") int id, String uneOeuvre) throws MonException {
		Gson gson = new Gson();
		Oeuvrevente uneOeu = gson.fromJson(uneOeuvre, Oeuvrevente.class);
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";

			mysql = "UPDATE oeuvrevente SET titre_oeuvrevente=\'"+uneOeu.getTitreOeuvrevente()+"\', prix_oeuvrevente="+uneOeu.getPrixOeuvrevente()
					+", etat_oeuvrevente=\'"+uneOeu.getEtatOeuvrevente()+"\' WHERE id_oeuvrevente="+id;
			unDialogueBd.insertionBD(mysql);
		}catch (MonException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	/***************************************************/
	/***************Partie sur les proprietaire **************/
	/*****************************************************/

	@GET
	@Path("/Proprietaires")
	@Produces("application/json")
	public String rechercheProrio() throws MonException {
		List<Object> rs;
		List<Proprietaire> mesProprietaires = new ArrayList<Proprietaire>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			String mysql = "";

			mysql = "SELECT id_proprietaire, nom_proprietaire, prenom_proprietaire FROM proprietaire ORDER BY id_proprietaire ASC";

			rs = unDialogueBd.lecture(mysql);

			while (index < rs.size()) {

				// On crï¿½e un objet Adherent
				Proprietaire unProp = new Proprietaire();
				unProp.setIdProprietaire(Integer.parseInt(rs.get(index + 0).toString()));
				unProp.setNomProprietaire(rs.get(index+1).toString());
				unProp.setPrenomProprietaire(rs.get(index+2).toString());
				index = index + 3;


				mesProprietaires.add(unProp);
			}

			Gson gson = new Gson();
			String json = gson.toJson(mesProprietaires);
			return json;

		} catch (MonException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
}
