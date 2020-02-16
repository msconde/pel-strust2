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
import fr.orleans.univ.pel.support.PelActionSupport;
import java.util.Collection;
import modele.Pari;

/**
 * Action d'affichage des paris.
 * 
 * @author DirectX-Box
 */
public class Paris extends PelActionSupport {
    
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
     * Retourne la collection de paris.
     * 
     * Cette méthode est utilisée par
     * Struts 2 pour générer la JSP.
     * @return la collection de paris.
     */
    public Collection<Pari> getMesParis()
    {
        return this.mesParis;
    }
}
