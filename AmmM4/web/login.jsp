<%-- 
    Document   : Login
    Created on : Apr 28, 2016, 11:12:46 AM
    Author     : sym410
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>LOG IN</title>
        <meta charset="UTF-8">
        <meta name="author" content="StefanoCorda">
        <meta name="keywords" content="TCGpoetto, login">
        <meta name="description" content="Pagina per effettuare il log in">
        <link rel="stylesheet" type="text/css" href="DatiStatici/style.css">
    </head>
    
    
    <body>
       
      
     <jsp:include page="Navbar.jsp" />     
      <div id=page>

         <header>
           <img id="Logo" title="Logo" alt="Logo" src="DatiStatici/Images/Logo.png" width="130" height="126">
           <h1> Pagina di autenticazione </h1>
         </header>
                
         <div id=login>  
             <c:if test="${error  == 'wrongdata'  }">
                 <p class="error"> Hai inserito dei dati errati. </p>
             </c:if>
                 
             <form  method="post" action="login.html">
                <label for="username"> Username  </label>
                <input type="text" name="usr" id="username" placeholder="Nome utente o Email"> 

                <label for="password">Password </label>
                <input type="password" name="psw" id="password" placeholder="Password">

                <button type="submit" name="Submit" value="Login"> Accedi </button>
             </form> 
         </div> 
      </div>
    </body>
</html>
