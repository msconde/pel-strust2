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
import java.util.Collection;
import java.util.Map;
import modele.Pari;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action d'affichage des paris.
 * 
 * @author DirectX-Box
 */
public class Paris extends ActionSupport implements
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
     * Les paris de l'utilisateur.
     * 
     * Cette collection contient les paris
     * de l'utilisateur. La méthode
     * getMesParis() permet à la JSP
     * de récupérer automatiquement
     * les paris.
     */
    private Collection<Pari> mesParis;
    
    /**
     * Le pseudonyme de l'utilisateur.
     * 
     * Elle nécessite une méthode getPseudonyme()
     * pour permettre à la JSP de le récupérer.
     */
    private String pseudonyme;
    
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
        if(this.pseudonyme == null) return "input";
        this.mesParis = modele.getMesParis(this.pseudonyme);
        return "success";
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
     * Retourne la collection des paris de l'utilisateur.
     * 
     * Cette méthode est utilisée automatiquement par
     * Struts 2 lors de la génération de la JSP.
     * @return la collection des paris.
     */
    public Collection<Pari> getMesParis()
    {
        return this.mesParis;
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