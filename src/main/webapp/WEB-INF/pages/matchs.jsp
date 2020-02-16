<%-- 
    Document   : matchs
    Created on : Feb 16, 2020, 4:54:46 PM
    Author     : DirectX-Box
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:text name="Matchs.matchs"/> - <s:text name="SiteWeb.nom"/></title>
    </head>
    <body>
        <h1><s:text name="Matchs.matchs"/></h1>
        
        <ul>
            <s:iterator status="status" value="matchs">
                <li><s:property value="sport"/> entre <s:property value="equipe1"/> et <s:property value="equipe2" />
                    <a href="<s:url action="fairePari">
                        <s:param name="idMatch"><s:property value="idMatch"/></s:param>
                       </s:url>"><s:text name="Pari.parier"/></a>
                </li>
            </s:iterator>
        </ul>
    </body>
</html>
