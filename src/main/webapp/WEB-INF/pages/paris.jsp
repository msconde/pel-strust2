<%-- 
    Document   : paris
    Created on : Feb 16, 2020, 3:48:03 PM
    Author     : DirectX-Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="Pari.paris"/> - <s:text name="SiteWeb.nom"/></title>
    </head>
    <body>
        <h1><s:text name="Pari.paris"/></h1>

        <ul>        
        <s:iterator status="status" value="mesParis">
            <li><p>Match : <s:property value="match.sport"/> entre <s:property value="match.equipe1"/> et <s:property value="match.equipe2"/></p>
                <p><s:property value="montant"/> € sur <s:property value="vainqueur"/></p>
                <p><a href="<s:url action="annulerPari">
                                <s:param name="idPari">
                                    <s:property value="match.idMatch"/>
                                </s:param>
                            </s:url>"><s:text name="Pari.annuler"/></a></p>
            </li>
        </s:iterator>
        </ul>
    </body>
</html>
