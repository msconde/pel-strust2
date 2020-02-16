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
import modele.Match;
import org.apache.struts2.interceptor.ApplicationAware;

/**
 *
 * @author DirectX-Box
 */
public class Matchs extends ActionSupport implements ApplicationAware {
    
    private Map<String, Object> application;
    private Collection<Match> matchs;
    
    @Override
    public String execute()
    {
        FacadeParis modele = this.getModele();
        this.matchs = modele.getMatchsPasCommences();
        return "success";
    }
    
    public Collection<Match> getMatchs()
    {
        return this.matchs;
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
    
    @Override
    public void setApplication(Map<String, Object> map) {
        this.application = map;
    }
    
}
