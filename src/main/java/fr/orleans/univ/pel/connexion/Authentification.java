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
 *
 * @author DirectX-Box
 */
public class Authentification extends ActionSupport implements ApplicationAware {
    
    private Map<String, Object> application;
    
    private String pseudonyme;
    private String motDePasse;
    
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
    
    public String getPseudonyme()
    {
        return this.pseudonyme;
    }
    
    public void setPseudonyme(String p)
    {
        this.pseudonyme = p;
    }
    
    public void setMotDePasse(String m)
    {
        this.motDePasse = m;
    }
    
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

    @Override
    public void setApplication(Map<String, Object> map) {
        this.application = map;
    }
}
