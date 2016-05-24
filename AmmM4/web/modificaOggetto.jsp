<%-- 
    Document   : modificaOggetto
    Created on : May 23, 2016, 2:33:12 PM
    Author     : sym410
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica oggetto</title>    
        <meta charset="UTF-8">
        <meta name="author" content="StefanoCorda">
        <meta name="description" content="Pagina usata per modificare un oggetto precedentemente messo in vendita">
        <link rel="stylesheet" type="text/css" href="DatiStatici/style.css">

    </head>
    <body>
        <jsp:include page="Navbar.jsp" />     

        <div id="page">
            <header>
                <img id="Logo" title="Logo" alt="Logo" src="DatiStatici/Images/Logo.png" width="130" height="126">
                <h1> Modifica un oggetto</h1>
            </header>

            


            <div id="vendita">    
                <form method="post" action="venditore.html"> 
                    <label for="NomeOggetto">Inserire nome dell'oggetto</label>
                    <input type="text" id="NomeOggetto" name="ItemNameMod" value="${objMod.getName()}"> 

                    <label for="UrlImg"> Inserire link a un'immagine</label>
                    <input type="text" id="UrlImg" value="${objMod.getUrl()}" name="UrlNameMod">  <!-- Ho messo type text perché se metto percorsi 
                                                                                                    a immagini nel disco, url me li segna coma sbagliati -->
                    <label for="BreveDescrizione">Inserire una breve descrizione dell'oggetto</label>
                    <textarea id="BreveDescrizione" name="textAreaMod"cols="40" rows="7" > ${objMod.getDescription()}</textarea>

                    <label for="Prezzo"> Inserire prezzo dell'oggetto in euro </label>
                    <input type="number"  name="PrezzoMod" id="Prezzo" value="${objMod.getPrice()}">

                    <label for="Quantità">Inserire quantità che si intende vendere. </label>
                    <input type="number" id="Quantità" name="QuantityMod" min="1" value="${objMod.getQuantity()}">
                    
                    
                    <input type="hidden" id="idItemMod" name="idItemMod" value="${objMod.getId()}">
                    <button type="submit" name="SubmitObjMod"> Modifica</button>
                    <button type="submit" name="DeleteObjMod"> Cancella oggetto </button>

                </form> 
            </div>
        </div>
    </body>
</html>
