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
import facade.FacadeParisStaticImpl;
import fr.orleans.univ.pel.support.PelActionSupport;
import java.util.ArrayList;
import java.util.Collection;
import modele.Match;

/**
 * Action gérant le clic sur
 * le lien "Parier" sur la liste
 * des matchs.
 * 
 * @author DirectX-Box
 */
public class FairePari extends PelActionSupport {
    
    /**
     * L'ID du match sur lequel parier.
     * 
     * Ce champ est automatiquement rempli
     * par Struts 2 via la méthode setIdMatch()
     * quand l'action est invoquée.
     */
    private long idMatch;
    
    /**
     * L'objet du Match sur lequel parier.
     * 
     * Ce champ est automatiquement
     * récupéré pour la génération
     * de la JSP.
     */
    private Match match;
    
    /**
     * La collection des équipes du match.
     * 
     * Ce champ est utilisé pour générer
     * le menu déroulant des vainqueurs
     * à travers la méthode getEquipes().
     */
    Collection<String> equipes;
    
    
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
        if(this.pseudonyme == null) return "home";
        this.match = modele.getMatch(this.idMatch);
        String equipe1 = match.getEquipe1();
        String equipe2 = match.getEquipe2();
        this.equipes = new ArrayList<>();
        this.equipes.add(equipe1);
        this.equipes.add(equipe2);
        return "success";
    }
    
    /**
     * Retourne l'ID du match.
     * 
     * Cette méthode est nécessaire
     * pour permettre au formulaire
     * de la JSP de connaître
     * l'ID du match.
     * 
     * @return l'ID du match.
     */
    public long getIdMatch()
    {
        return this.idMatch;
    }
    
    /**
     * Fixe l'ID du match.
     * 
     * Cette méthode est appelée
     * par Struts 2 quand l'utilisateur
     * clique sur "Parier".
     * @param id l'ID du match.
     */
    public void setIdMatch(long id)
    {
        this.idMatch = id;
    }
    
    /**
     * Retourne l'objet Match.
     * 
     * Cette méthode permet à la JSP
     * de récupérer les attributs
     * du Match.
     * 
     * @return l'objet Match.
     */
    public Match getMatch()
    {
        return this.match;
    }
    
    /**
     * Retourne la collection d'équipes.
     * 
     * Cette méthode est appelée par Struts 2
     * afin de permettre à la JSP de compléter
     * le menu déroulant du formulaire.
     * @return la collection d'équipes.
     */
    public Collection<String> getEquipes()
    {
        return this.equipes;
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
}
