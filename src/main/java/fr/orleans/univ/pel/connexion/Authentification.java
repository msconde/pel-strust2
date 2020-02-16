/*
 * The MIT License
 *
 * Copyright 2020 DirectX-Box.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.orleans.univ.pel.connexion;

import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParis;
import facade.FacadeParisStaticImpl;
import facade.exceptions.InformationsSaisiesIncoherentesException;
import facade.exceptions.UtilisateurDejaConnecteException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Utilisateur;
import org.apache.struts2.interceptor.ApplicationAware;

/**
 * Action dédiée à l'authentification.
 * 
 * Cette action permet la connexion de l'utilisateur
 * depuis le formulaire d'authentification.
 * 
 * Les couples pseudonyme/mot de passe valides sont
 * stockés en dur dans le modèle.
 * 
 * @author DirectX-Box
 */
public class Authentification extends ActionSupport implements ApplicationAware {
    
    /**
     * La Map stockant les éléments globaux de l'application.
     * 
     * Celle-ci est automatiquement créée via la méthode setApplication(),
     * qui est fournie par l'interface ApplicationAware.
     */
    private Map<String, Object> application;
    
    /**
     * Le pseudonyme de l'utilisateur.
     * 
     * Cette variable est automatiquement créée
     * quand l'utilisateur valide le formulaire.
     * 
     * Elle nécessite une méthode setPseudonyme()
     * pour fonctionner.
     */
    private String pseudonyme;
    
    /**
     * Le mot de passe de l'utilisateur.
     * 
     * Cette variable est automatiquement créée
     * quand l'utilisateur valide le formulaire.
     * 
     * Elle nécessite une méthode setMotDePasse()
     * pour fonctionner.
     */
    private String motDePasse;
    
    /**
     * Le code de l'action.
     * 
     * Cette méthode est automatiquement appelée
     * par Struts 2 lorsque l'action est invoquée.
     * 
     * @return "success" si l'action a réussi, "input" sinon.
     */
    @Override
    public String execute()
    {
        FacadeParis modele = this.getModele();
        try
        {
            Utilisateur u = modele.connexion(pseudonyme, motDePasse);
        }
        catch(UtilisateurDejaConnecteException ex)
        {
            return "success";
        }
        catch (InformationsSaisiesIncoherentesException ex) {
            return "input";
        }
        modele.deconnexion(pseudonyme);
        return "success";
    }
    
    /**
     * Retourne le pseudonyme de l'utilisateur.
     * 
     * @return le pseudonyme.
     */
    
    public String getPseudonyme()
    {
        return this.pseudonyme;
    }
    
    /**
     * Fixe le pseudonyme de l'utilisateur.
     * 
     * Cette méthode est appelée automatiquement
     * par Struts 2 lors de l'invocation de
     * l'action.
     * @param p le pseudonyme du formulaire.
     */
    public void setPseudonyme(String p)
    {
        this.pseudonyme = p;
    }
    
    /**
     * Fixe le mot de passe de l'utilisateur.
     * 
     * Cette méthode est appelée automatiquement
     * par Struts 2 lors de l'invocation de
     * l'action.
     * @param m le mot de passe du formulaire.
     */
    public void setMotDePasse(String m)
    {
        this.motDePasse = m;
    }
    
    /**
     * Retourne une instance du modèle.
     * 
     * Le modèle est stocké dans les variables globales
     * de l'application.
     * 
     * S'il est introuvable dans les variables globales,
     * alors on le crée et on l'ajoute dans ces variables.
     * 
     * @return l'instance du modèle.
     */
    public FacadeParis getModele()
    {
        FacadeParis modele = (FacadeParis) this.application.get("modele");
        if(modele == null)
        {
            modele = new FacadeParisStaticImpl();
            this.application.put("modele", modele);
        }
        return modele;
    }

    /**
     * Fixe la Map des variables globales.
     * 
     * Cette méthode est héritée de l'interface
     * ApplicationAware, et est automatiquement
     * appelée par Struts 2 lors de l'invocation
     * de l'action.
     * @param map la Map des variables globales.
     */
    @Override
    public void setApplication(Map<String, Object> map) {
        this.application = map;
    }
}
