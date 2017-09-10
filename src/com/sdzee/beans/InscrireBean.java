package com.sdzee.beans;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sdzee.dao.UtilisateurDao;
import com.sdzee.entities.Utilisateur;

@ManagedBean
@ViewScoped  //ici on précise la vue et non pas @RequestScoped
public class InscrireBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Utilisateur       utilisateur;

    // Injection de notre EJB (Session Bean Stateless)
    @EJB
    private UtilisateurDao    utilisateurDao;

    // Initialisation de l'entité utilisateur
    public InscrireBean() {
        utilisateur = new Utilisateur();
    }

    // Méthode d'action appelée lors du clic sur le bouton du formulaire
    // d'inscription
    public void inscrire() {
        initialiserDateInscription();
        utilisateurDao.creer( utilisateur );
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    private void initialiserDateInscription() {
        Timestamp date = new Timestamp( System.currentTimeMillis() );
        utilisateur.setDateInscription( date );
    }
}