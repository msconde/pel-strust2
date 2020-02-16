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

import com.opensymphony.xwork2.ActionSupport;
import facade.FacadeParis;
import facade.FacadeParisStaticImpl;
import facade.exceptions.MatchClosException;
import facade.exceptions.ResultatImpossibleException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Match;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author DirectX-Box
 */
public class FairePari extends ActionSupport implements
        ApplicationAware, SessionAware {
    
    /**
     * La Map stockant les éléments globaux de l'application.
     * 
     * Celle-ci est automatiquement créée via la méthode setApplication(),
     * qui est fournie par l'interface ApplicationAware.
     */
    private Map<String, Object> application;
    
    /**
     * La Map stockant les éléments de session.
     * 
     * Celle-ci est automatiquement créée via la méthode setSession(),
     * qui est fournie par l'interface SessionAware.
     */
    private Map<String, Object> session;
    
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
    
    private int idMatch;
    private Match match;
    Collection<String> equipes;
    
    
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
    
    public int getIdMatch()
    {
        return this.idMatch;
    }
    
    public void setIdMatch(int id)
    {
        this.idMatch = id;
    }
    
    public Match getMatch()
    {
        return this.match;
    }
    
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

    /**
     * Fixe la Map des variables de session.
     * 
     * Cette méthode est héritée de l'interface
     * SessionAware, et est automatiquement
     * appelée par Struts 2 lors de l'invocation
     * de l'action.
     * @param map la Map des variables de session.
     */
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
    
}
