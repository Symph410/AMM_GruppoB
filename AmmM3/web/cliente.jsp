 <%-- 
    Document   : cliente
    Created on : Apr 28, 2016, 11:26:14 AM
    Author     : sym410
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Articoli in vendita</title>
        <meta charset="UTF-8">
        <meta name="keywords" content="TCGpoetto, acquisti, oggetti">
        <meta name="author" content="Stefano Corda">
        <meta name="description" content="Pagina dove è possibile fare acquisti">
        <link rel="stylesheet" type="text/css" href="DatiStatici/style.css">
    </head>
    
    
    <body>
        
        
     <jsp:include page="Navbar.jsp" />     


     <div id="page">
        
        <header>
          <img id="Logo" title="Logo" alt="Logo" src="DatiStatici/Images/Logo.png" width="130" height="126">
          <h1> Oggetti in vendita </h1>
        </header>
        
         <c:if test="${Disponibilità== 'insufficiente'}">
             <p class="error"> L'oggetto è momentaneamente esaurito. </p>
         </c:if>
             
             <c:if test="${Denaro == 'notEnough'}">
                 <p class="error"> Operazione non riuscita: fondo insufficiente </p>
             </c:if>
                 
                 <p> Il tuo conto è di ${Math.floor(Utente.conto.getConto())}E<p>
         <table>
             <tr>
                 <th>Nome </th>
                 <th>Immagine </th>
                 <th> Descrizione</th>
                 <th>Quantità disponibile</th>
                 <th>Prezzo</th>
             </tr>
             <c:forEach var="obj" items="${obj}">
                 <tr>
                     <td> ${obj.getName()} </td>
                     <td> <image src="${obj.getUrl()}" name="${obj.getName()}" alt="${obj.getName()}"> </td>
                     <td> ${obj.getDescription()}</td>
                     <td> ${obj.getQuantity()}</td>
                     <td> ${obj.getPrice()}</td>
                     <td> <a href="cliente.html?objId=${obj.getId()}"> acquista </a> </td>
                 </tr>
             </c:forEach>
        </table>
     </div>
    </body>


</html>
