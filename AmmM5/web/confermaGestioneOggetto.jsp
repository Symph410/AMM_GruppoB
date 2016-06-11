<%-- 
    Document   : confermaGestioneOggetto
    Created on : May 23, 2016, 7:15:59 PM
    Author     : sym410
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica oggetto</title>    
        <meta charset="UTF-8">
        <meta name="author" content="StefanoCorda">
        <meta name="description" content="Pagina di conferma">
        <link rel="stylesheet" type="text/css" href="DatiStatici/style.css">

    </head>

    <body>
        <jsp:include page="Navbar.jsp" />     
        
        <div id="page">

            <c:if test="${Modifica == 'avvenuta'}">
                <p class="error"> Aggiornamento dello stato dell'oggetto avvenuto con successo.</p>
            </c:if> 
        </div>
    </body>
</html>
