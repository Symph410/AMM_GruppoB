<%-- 
    Document   : Descrizione
    Created on : Apr 28, 2016, 11:22:21 AM
    Author     : sym410
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 
<html>
    <head>
        <title> TCGpoetto summary </title>
        <meta name="author" content="StefanoCorda" >
        <meta name="description" content="Breve descrizione del sito e link al log in">
        <meta name="keywords" content="compravendita, carte, modellismo, collezionismo, gadget">
        <meta charset="utf-8" >
        <link rel="stylesheet" type="text/css" href="DatiStatici/style.css"> 
    </head>
   
   
    <body>

     <jsp:include page="Navbar.jsp" />     

         
          <nav id="faq">
                <h2> FAQ </h2>
            <ul>
                <li> <a href="#FAQ1">Come nasce e cos'è TCGpoetto? </a> </li>
                <li> <a href="#FAQ2"> Cosa è possibile mettere in vendita? </a> </li>
                <li> <a href="#FAQ3"> Come posso fare acquisti? </a> </li>
                <li> <a href="#FAQ4"> Come posso vendere? </a> </li>
            </ul>
         </nav>

      <div id="page">

        <header>
          <img id="Logo" title="Logo" alt="Logo" src="DatiStatici/Images/Logo.png" width="130" height="126">
         <h1 id="titolo"> Welcome to TCGpoetto </h1>
        </header>

            <div>
                <h3 id="FAQ1">  Come nasce e cos'è TCGpoetto?  </h3> 
                    <p>TCGpoetto è prevalentemente un sito di compravendita di oggetti di collezionismo,
                    	nasce nel 2004 come forum di TCG (trading card game). <br>
                        Con l'allargarsi della community ha cominciato a offrire servizi di compravendita. <br/>
                        Attualmente oltre che di carte da gioco tratta anche oggetti di modellismo e gadget. <br/>
                    </p>
                   
                   
                   
                <h3 id="FAQ2">  Cosa è possibile mettere in vendita? </h3> 
                    <p>TCGpoetto consente di vendere tutti i tipi di carte da gioco, oggetti di modellismo,
                       e i relativi gadget.  <br/>
                       Oggetti non conformi a questi parametri verranno rimossi dai moderatori.
                    </p>
                   
                   
                <h3 id="FAQ3">  Come posso fare acquisti? </h3> 
                     <p>TCGpoetto supporta bonifico bancario e pagamento tramite carta di credito.<br/>
                         Il bonifico bancario deve essere effettuato all'indirizzo XXXXXXXXXXXXXXXXXXXX.<br/>
                         Accettiamo come carte di credito Visa, Mastercard e Paypal.
                      </p>
                   
                   
                <h3 id="FAQ4"> Come posso vendere? </h3> 
                      <p>TCGpoetto ha molta cura dei suoi clienti. Prima di poter vendere bisogna creare un account da venditore.<br/>
                       Tale account deve essere approvato agli amministratori del sito, che provvederanno a verificare le credenziali
                       fornite dal venditore.
                      </p>
            </div>

      </div>                
    </body>
</html>