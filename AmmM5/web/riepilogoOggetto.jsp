<%-- 
    Document   : RiepilogoOggetto
    Created on : Apr 30, 2016, 1:45:39 PM
    Author     : sym410
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link rel="stylesheet" type="text/css" href="DatiStatici/style.css">
        <title> Riepilogo Inserimento Oggetto</title>
    </head>
    
    <body>   
     <jsp:include page="Navbar.jsp" />     
      <div id="page">            
        <h1>Riepilogo</h1>
        
        <p> Inserimento dell'oggetto avvenuto con successo. <br/>
            Questi sono i dati del'oggetto messo in vendita.
        </p>
        
        <table>
            <tr>
                <th>Nome </th>
                <th>Immagine </th>
                <th>Descrizione </th>
                <th>Quantità disponibile</th>
                <th>Prezzo</th>
            </tr>
             
            <tr>
                <td>  ${itemInput.getName()} </td>
                <td> <img title="${itemInput.getName()}" 
                       witdh="150px" height="150px"src="${itemInput.getUrl()}" alt="${item.Input.getName()}"}> 
                </td>
                <td class="descrizione"> ${itemInput.getDescription()} </td>
                <td> ${itemInput.getQuantity()} </td>
                <td> ${itemInput.getPrice()}€</td>
            </tr>
  
        </table>
       </div>
    </body>
</html>
