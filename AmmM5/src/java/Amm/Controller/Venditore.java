/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amm.Controller;

import Amm.model.Classes.ObjectSale;
import Amm.model.Classes.ObjectSaleFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sym410
 */
@WebServlet(name = "Venditore", urlPatterns = {"/venditore.html"})
public class Venditore extends HttpServlet {

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

        HttpSession session = request.getSession(true);
        if (session.getAttribute("loggedAs") != "seller") {
            request.getRequestDispatcher("accessDenied.jsp").forward(request, response);
        }

        if (request.getParameter("SubmitObj") != null) {

            ObjectSale itemInput = new ObjectSale();
            itemInput.setName(request.getParameter("ItemName"));
            itemInput.setUrl(request.getParameter("UrlName"));
            itemInput.setDescription(request.getParameter("textArea"));

            //Prima di settare i valori di prezzo e quantità, che non sono stringhe
            //effettuo una serie di controlli sull'input effettuato dall'utente.
            //In particolare controllo se ha inserito una stringa nulla o vuota.
            //A prescindere da cosa inserirà l'utente, al peggio itemPrice e itemQuantity
            //varranno zero, e in questo caso non permetterò la messa in vendita dell'oggetto,
            //oltre che a stampare un messaggio di errore.
            Double itemPrice;
            if (request.getParameter("Prezzo") != null && !(request.getParameter("Prezzo").isEmpty())) {
                itemPrice = Double.parseDouble(request.getParameter("Prezzo"));
            } else {
                itemPrice = 0.0;
            }

            Integer itemQuantity;
            if (request.getParameter("Quantity") != null && !(request.getParameter("Quantity").isEmpty())) {
                itemQuantity = Integer.parseInt(request.getParameter("Quantity"));
            } else {
                itemQuantity = 0;
            }

            itemInput.setQuantity(itemQuantity);
            itemInput.setPrice(itemPrice);

            request.setAttribute("itemInput", itemInput);
            ObjectSaleFactory.singleton().addItem(itemInput);

            if (itemQuantity <= 0 || itemPrice <= 0.0) {
                request.setAttribute("Warning", "wrongValues");
                request.getRequestDispatcher("venditore.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("riepilogoOggetto.jsp").forward(request, response);
            }
        }

        ArrayList<ObjectSale> objSeller = new ArrayList<>();
        objSeller = ObjectSaleFactory.singleton().getSellerItems();
        request.setAttribute("objSeller", objSeller);

        if (request.getParameter("objModId") != null) {
            Integer idModObj = Integer.parseInt(request.getParameter("objModId"));
            //faccio in modo che non possano essere modificati oggetti
            //che non sono messi in vendita dal venditore loggato
            for (ObjectSale ob : objSeller) {
                if (ob.getId().equals(idModObj)) {
                    request.setAttribute("objMod", ob);
                    request.getRequestDispatcher("modificaOggetto.jsp").forward(request, response);
                }
            }
        }
        //modifico i valori nella mia lista e nel db
        if (request.getParameter("SubmitObjMod") != null) {

            Integer idOb = Integer.parseInt(request.getParameter("idItemMod"));
           
            //i controlli su null mi consentono di non modificare lo stato dell'oggetto
            //se viene lasciato vuoto il form
            if (request.getParameter("ItemNameMod") != null) {
                ObjectSaleFactory.singleton().
                        getObjectSaleById(idOb).
                        setName(request.getParameter("ItemNameMod"));
            }
            if (request.getParameter("UrlNameMod") != null) {
                ObjectSaleFactory.singleton().
                        getObjectSaleById(idOb).
                        setUrl(request.getParameter("UrlNameMod"));
            }
            if (request.getParameter("textAreaMod") != null) {
                ObjectSaleFactory.singleton().
                        getObjectSaleById(idOb).
                        setDescription(request.getParameter("textAreaMod"));
            }

            if (request.getParameter("PrezzoMod") != null) {
                ObjectSaleFactory.singleton().
                        getObjectSaleById(idOb).
                        setPrice(Double.parseDouble(request.getParameter("PrezzoMod")));
            }

            if (request.getParameter("QuantityMod") != null) {
                ObjectSaleFactory.singleton().
                        getObjectSaleById(idOb).
                        setQuantity(Integer.parseInt(request.getParameter("QuantityMod")));
            }

            ObjectSaleFactory.singleton().updateItem(ObjectSaleFactory.singleton().getObjectSaleById(idOb));
            request.setAttribute("Modifica", "avvenuta");
            request.getRequestDispatcher("confermaGestioneOggetto.jsp").forward(request, response);
        }

        if (request.getParameter("DeleteObjMod") != null) {
            if (request.getParameter("idItemMod") != null) {
                objSeller.remove(ObjectSaleFactory.singleton().
                        getObjectSaleById(Integer.parseInt(request.getParameter("idItemMod"))));
                ObjectSaleFactory.singleton().
                        deleteItem(Integer.parseInt(request.getParameter("idItemMod")));
                request.setAttribute("Modifica", "avvenuta");
                request.getRequestDispatcher("confermaGestioneOggetto.jsp").forward(request, response);
            }
        }
        //posiziono qua quest'istruzione in modo che il venditore, quando si torva nel riepilogo per messa in vendita
        //dell'oggetto possa tornare alla pagina per metterne in vendita un altro tornando alla pagina venditore
        request.getRequestDispatcher("venditore.jsp").forward(request, response);

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
