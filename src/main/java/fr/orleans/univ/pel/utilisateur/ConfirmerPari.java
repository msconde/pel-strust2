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
package fr.orleans.univ.pel.utilisateur;

import facade.FacadeParis;
import facade.exceptions.MatchClosException;
import facade.exceptions.ResultatImpossibleException;
import fr.orleans.univ.pel.support.PelActionSupport;

/**
 * Action gérant la confirmation d'un pari.
 * 
 * Cette action gère le formulaire apparaissant
 * après avoir cliqué sur le bouton "Parier" sur
 * la liste des matchs.
 * 
 * @author DirectX-Box
 */
public class ConfirmerPari extends PelActionSupport {
    
    /**
     * L'ID du match.
     * 
     * Struts 2 remplit automatiquement
     * ce champ lorsque l'action est invoquée.
     */
    private int idMatch;
    
    /**
     * Le vainqueur sélectionné dans
     * le formulaire.
     * 
     * Struts 2 remplit automatiquement
     * ce champ via la méthode setVainqueur()
     * lors de l'invocation de l'action.
     */
    private String vainqueur;
    
    /**
     * Le montant choisi par
     * l'utilisateur.
     * 
     * Cette variable est remplie
     * par Struts 2 lors de l'invocation
     * de l'action grâce à la méthode
     * setMontant().
     */
    private double montant;
    
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
        this.pseudonyme = (String) this.session.get("pseudonyme");
        try
        {
            modele.parier(this.pseudonyme, this.idMatch, this.vainqueur, this.montant);
        } catch (MatchClosException ex) {
            return "home";
        } catch (ResultatImpossibleException ex) {
            this.addFieldError("vainqueur", "Résultat impossible.");
            return "input";
        }
        return "success";
    }
    
    /**
     * Retourne l'ID du match.
     * @return l'ID du match.
     */
    public int getIdMatch()
    {
        return this.idMatch;
    }
    
    /**
     * Fixe l'ID du match.
     * 
     * Cette méthode est appelée
     * automatiquement par Struts 2
     * quand l'action est instanciée.
     * @param id l'ID du match.
     */
    public void setIdMatch(int id)
    {
        this.idMatch = id;
    }
    
    /**
     * Retourne le vainqueur sélectionné.
     * @return le vainqueur.
     */
    public String getVainqueur()
    {
        return this.vainqueur;
    }
    
    /**
     * Fixe le vainqueur sélectionné.
     * 
     * Cette méthode est appelée
     * automatiquement par Struts 2
     * quand l'action est invoquée.
     * @param v 
     */
    public void setVainqueur(String v)
    {
        this.vainqueur = v;
    }
    
    /**
     * Retourne le montant sélectionné.
     * @return le montant.
     */
    public double getMontant()
    {
        return this.montant;
    }
    
    /**
     * Fixe le montant sélectionné.
     * 
     * Cette méthode est appelée
     * automatiquement lorsque
     * l'action est invoquée.
     * @param m 
     */
    
    public void setMontant(double m)
    {
        this.montant = m;
    }
}
