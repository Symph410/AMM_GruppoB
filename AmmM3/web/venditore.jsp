<%-- 
    Document   : venditore
    Created on : Apr 28, 2016, 11:35:01 AM
    Author     : sym410
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

    <html>

        <head>
            <title> Form per la messa in vendita di un oggetto</title>
            <meta charset="UTF-8">
            <meta name="author" content="StefanoCorda">
            <meta name="keywords" content="TCGpoetto, vendita, metti in vendita">
            <meta name="description" content="Pagina usata per mettere in vendita i propri oggetti">
            <link rel="stylesheet" type="text/css" href="DatiStatici/style.css">
        </head>


      <body>   
         <jsp:include page="Navbar.jsp" />     

       <div id="page">
          <header>
             <img id="Logo" title="Logo" alt="Logo" src="DatiStatici/Images/Logo.png" width="130" height="126">
            <h1> Metti in vendita un oggetto</h1>
          </header>

           

            <div id="vendita">
                
             <c:if test="${Warning == 'wrongValues'}">
                 <p class="error"> Perfavore completa tutti i campi e inserisci valori numerici validi </p>
             </c:if> 
              <form  method="post" action="venditore.html"> 
                  <label for="NomeOggetto">Inserire nome dell'oggetto</label>
                  <input type="text" id="NomeOggetto" name="ItemName"> 

                 <label for="UrlImg"> Inserire link a un'immagine</label>
                 <input type="url" id="UrlImg" placeholder="www.example.com/imm.jpg" name="UrlName"> 

                 <label for="BreveDescrizione">Inserire una breve descrizione dell'oggetto</label>
                 <textarea id="BreveDescrizione" name="textArea"cols="40" rows="7" placeholder="Descrizione..." ></textarea>

                 <label for="Prezzo"> Inserire prezzo dell'oggetto in euro </label>
                 <input type="number" name="Prezzo" id="Prezzo">

                 <label for="Quantità">Inserire quantità che si intende vendere </label>
                 <input type="number" id="Quantità" name="Quantity">

                <button type="submit" name="SubmitObj"> Vendi</button>
                <button type="reset"> Azzera i campi </button>

              </form>  
            </div>

         </div>   
        </body>
    </html>
