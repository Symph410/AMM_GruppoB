<%-- 
    Document   : accessDenied.jsp
    Created on : Apr 29, 2016, 7:55:31 PM
    Author     : sym410
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link rel="stylesheet" type="text/css" href="DatiStatici/style.css">
       <meta name="author" content="Stefano Corda">
        <title>ACCESS DENIED</title>
    </head>
    <body>
       <jsp:include page="Navbar.jsp" />     

        <div id="page">
             <h1>Access Denied!</h1>
            <p> Non hai i permessi necessari per accedere a questa pagina.<br/>
                I venditori non hanno il permesso di accedere alla sezione clienti,
                e i clienti non possono accedere alla sezione venditori.
            </p>
            
            <p> Se credi di stare ricevendo questo messaggio erroneamente,
                dopo esserti assicurato della correttezza delle tue credenziali
                contatta gli amministratori di sistema.</p>
        </div>
    </body>
</html>
