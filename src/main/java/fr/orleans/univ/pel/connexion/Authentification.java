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

import facade.FacadeParis;
import facade.exceptions.InformationsSaisiesIncoherentesException;
import facade.exceptions.UtilisateurDejaConnecteException;
import fr.orleans.univ.pel.support.PelActionSupport;
import modele.Utilisateur;

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
public class Authentification extends PelActionSupport {
    
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
            Utilisateur u = modele.connexion(this.pseudonyme, motDePasse);
        }
        catch(UtilisateurDejaConnecteException ex)
        {
            return "success";
        }
        catch (InformationsSaisiesIncoherentesException ex) {
            return "input";
        }
        this.session.put("pseudonyme", this.pseudonyme);
        return "success";
    }
    
    @Override
    public void validate()
    {
        if(this.pseudonyme.length() == 0)
        {
            this.addFieldError("pseudonyme", this.getText("Connexion.pseudoRequis"));
        }
        
        if(this.motDePasse.length() == 0)
        {
            this.addFieldError("motDePasse", this.getText("Connexion.mdpRequis"));
        }
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
}
