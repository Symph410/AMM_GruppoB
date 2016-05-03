<%-- 
    Document   : riepilogoAcquisto
    Created on : Apr 30, 2016, 10:37:53 PM
    Author     : sym410
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css"  href="DatiStatici/style.css"
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Riepilogo acquisto</title>
    </head>
    <body>
        
      <jsp:include page="Navbar.jsp" />     

      <div id="page">
        <h1>Riepilogo acquisto</h1>   
        
        
        
        <p> Controlla la correttezza dei dati prima di procedere all'acquisto</p>
        
            <table>
            <tr>
                <th>Nome </th>
                <th>Immagine </th>
                <th>Descrizione </th>
                <th>Quantità disponibile</th>
                <th>Prezzo</th>
            </tr>
             
            <tr>
                <td>  ${objAcquisto.getName()} </td>
                <td> <img title="${objAcquisto.getName()}" 
                       witdh="150px" height="150px"src="${objAcquisto.getUrl()}" alt="${item.Input.getName()}"}> 
                </td>
                <td class="descrizione"> ${objAcquisto.getDescription()} </td>
                <td> ${objAcquisto.getQuantity()} </td>
                <td> ${objAcquisto.getPrice()}</td>
            </tr>
  
        </table>
        
          <form method="post" action="cliente.html">
            <input type="hidden" name="idOggetto" value="${objAcquisto.getId()}">
            <div id="acquisto">
                <button type="submit" name="acquisto" value="confermato"> Conferma </button>
            </div>
          </form>
              
                     
           
           
         </div>
       
    </body>
</html>
