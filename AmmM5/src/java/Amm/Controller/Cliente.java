    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amm.Controller;

import Amm.model.Classes.ObjectSaleFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Amm.model.Classes.ObjectSale;
import Amm.model.Classes.Utente;
import java.util.List;

/**
 *
 * @author sym410
 */
@WebServlet(name = "Cliente", urlPatterns = {"/cliente.html"})
public class Cliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session=request.getSession(true);
        if(session.getAttribute("loggedAs")!="buyer"){
            request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
        }
                
        
        
        ObjectSaleFactory factory= ObjectSaleFactory.singleton();
        List<ObjectSale> items = factory.getSellingObjectList();
        
        /*Ho settato il parametro objId tramite il metodo getId() nella pagina 
         *cliente.jsp, usando un url rewriting. Qua banalmente ottengo il link
         *clickato dall'utente e lo reindirizzo alla pagina di conferma acquisto*/
        Integer objectId;
        if (request.getParameter("objId")!=null && !(request.getParameter("objId").isEmpty()))
            objectId=Integer.parseInt(request.getParameter("objId"));
        else
            objectId=-1;
            
            
        /*non accetto che l'Id sia un valore negativo, nella generazione dell'Id
         *ho fatto in modo che non potesse venire generato un numero negativo.
         *Non concedo di proseguire anche se la quantità dell'oggetto che si desidera
         *acquistare è pari a zero e stampo un messaggio di errore */
         if(objectId> -1){
            if (factory.getObjectSaleById(objectId).getQuantity()>0 ){
                request.setAttribute("objAcquisto", factory.getObjectSaleById(objectId));
                request.getRequestDispatcher("riepilogoAcquisto.jsp").forward(request, response);
            }else
                request.setAttribute("Disponibilità", "insufficiente");
                
        }
        
        /*Questo blocco di codice viene eseguito se il cliente ha clickato sul bottone
         *conferma acquisto della pagine riepilogoAcquisto.jsp.
         *Ottengo l'id dell'oggetto che si vuole acquistare e controllo se l'acquirente
         *ha abbastanza denaro per poterlo comprare, in caso contrario stampo un errore.*/
        
          if(request.getParameter("acquisto") != null && request.getParameter("acquisto").equals("confermato")){
           if((Utente)session.getAttribute("Utente")!=null){
                Utente u= (Utente)session.getAttribute("Utente");               
                Integer idAcquisto;
                if (request.getParameter("idOggetto")!=null && ! (request.getParameter("idOggetto").isEmpty()))
                     idAcquisto=Integer.parseInt(request.getParameter("idOggetto"));
                else
                    idAcquisto=-1;
                
                if(idAcquisto!=-1){
                    if(! factory.transazione(u,factory.getObjectSaleById(idAcquisto) ) )
                //if(u.conto.subMoney(factory.getObjectSaleById(idAcquisto).getPrice())==false)
                        request.setAttribute("Denaro", "notEnough");
                    else{ //se il denaro è sufficiente a comprare l'oggetto, diminuisco la disponibilità di uno
                        request.getRequestDispatcher("acquistoConfermato.jsp").forward(request, response);
                    } 
                }
            }
        }
        //passo la lista con tutti gli oggetti a cliente.jsp,
        //dove la stamperò.
        request.setAttribute("obj", items);
        request.getRequestDispatcher("cliente.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
