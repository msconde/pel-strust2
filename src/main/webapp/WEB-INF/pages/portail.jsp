<%-- 
    Document   : portail.jsp
    Created on : Feb 16, 2020, 2:27:22 PM
    Author     : DirectX-Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="Portail.portail"/> - <s:text name="SiteWeb.nom"/></title>
    </head>
    <body>
        <h1><s:text name="Portail.portail"/></h1>
        <h2><s:text name="Portail.bienvenue"/> <s:property value="pseudonyme"/></h2>
        <ul>
            <li><a href="<s:url action="paris"/>"><s:text name="Portail.voirParis"/></a></li>
        </ul>
    </body>
</html>
