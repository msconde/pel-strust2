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
import facade.exceptions.OperationNonAuthoriseeException;
import fr.orleans.univ.pel.support.PelActionSupport;

/**
 * Action d'annulation des paris.
 * 
 * Cette action gère l'annulation d'un pari
 * depuis la liste des paris.
 * 
 * @author DirectX-Box
 */
public class AnnulerPari extends PelActionSupport {
    
    /**
     * L'ID du pari à annuler.
     * 
     * Cet identifiant est rempli
     * automatiquement par Struts 2
     * à l'aide de la méthode setIdPari().
     */
    private long idPari;
    
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
        try {
            modele.annulerPari(this.pseudonyme, this.idPari);
        } catch (OperationNonAuthoriseeException ex) {
            return "input";
        }
        return "success";
    }
    
    /**
     * Retourne l'ID du pari.
     * 
     * @return l'ID du pari.
     */
    public long getIdPari()
    {
        return this.idPari;
    }
    
    /**
     * Fixe l'ID du pari.
     * 
     * Cette méthode est appelée
     * par Struts 2 lors de
     * l'invocation de l'action.
     * @param id l'ID du pari.
     */
    public void setIdPari(long id)
    {
        this.idPari = id;
    }
}
