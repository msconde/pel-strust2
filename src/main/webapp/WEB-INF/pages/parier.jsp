<%-- 
    Document   : parier
    Created on : Feb 16, 2020, 5:22:12 PM
    Author     : DirectX-Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="Pari.parier"/> - <s:text name="SiteWeb.nom"/></title>
    </head>
    <body>
        <h1><s:text name="Pari.parier"/></h1>
        
        <p><s:property value="match.sport"/> entre <s:property value="match.equipe1"/> et <s:property value="match.equipe2"/></p>
        
        <s:form action="confirmerPari">
            <s:hidden name="idMatch" />
            <s:select name="vainqueur" list="equipes" key="Pari.vainqueur"/>
            <s:textfield name="montant" key="Pari.montant"/>
            <s:submit/>
        </s:form>
    </body>
</html>
