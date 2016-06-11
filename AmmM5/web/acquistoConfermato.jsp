        <%-- 
    Document   : acquistoConfermato
    Created on : May 1, 2016, 3:17:08 PM
    Author     : sym410
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="DatiStatici/style.css">
        <title>Acquisto confermato</title>
    </head>
    <body>
       <jsp:include page="Navbar.jsp" />
       
       <div id="page">
        <h1>Acquisto confermato</h1>    
        
       
            <p>
                Complimenti!<br/> Acquisto avvenuto con successo.
                Il tuo saldo è di ${Math.floor (Utente.conto.getConto())}€
            </p>
        
        </div>

        
    </body>
</html>
