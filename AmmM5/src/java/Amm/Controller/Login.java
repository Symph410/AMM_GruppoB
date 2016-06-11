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
import Amm.model.Classes.UtenteFactory;
import Amm.model.Classes.Utente;
import java.util.ArrayList;
import Amm.model.Classes.UtenteAcquirente;
import Amm.model.Classes.UtenteVenditore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sym410
 */
@WebServlet(name = "Login", urlPatterns = {"/login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {

    //Codice prof utile per il database
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";

    @Override
    public void init() {

        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_CLEAN_PATH;
        try {

            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException ex) {

            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

        }

        ObjectSaleFactory.singleton().setConnectionString(dbConnection);
        UtenteFactory.singleton().setConnectionString(dbConnection);

        ObjectSaleFactory.singleton().inizializzaDati();
        UtenteFactory.singleton().inizializzaDati();

    }

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

        HttpSession session = request.getSession(false);

        /*qua controllo se un utente è già loggato, e a seconda del fatto che sia
         *un cliente o un venditore lo redirecto alla giusta pagina*/
        if (session != null) {
            if (session.getAttribute("loggedAs") == "buyer") {
                request.getRequestDispatcher("cliente.html").forward(request, response);
            } else if (session.getAttribute("loggedAs") == "seller") {
                request.getRequestDispatcher("venditore.html").forward(request, response);
            }
        }

        if (request.getParameter("Submit") != null) {
            String user = request.getParameter("usr");
            String psw = request.getParameter("psw");

            ArrayList<Utente> myList = UtenteFactory.singleton().getUtenteList();

            for (Utente u : myList) {
                if (u.getUser().equals(user) && u.getPsw().equals(psw)) {
                    session = request.getSession(true);

                    if (u instanceof UtenteAcquirente) {
                        session.setAttribute("Utente", u);
                        session.setAttribute("loggedAs", "buyer");
                        request.getRequestDispatcher("cliente.html").forward(request, response);
                    } else if (u instanceof UtenteVenditore) {
                        ObjectSaleFactory.singleton().setLoggedSellerId(u.getId());
                        session.setAttribute("Utente", u);
                        session.setAttribute("loggedAs", "seller");
                        request.getRequestDispatcher("venditore.html").forward(request, response);
                    }
                }
            }
        }

        //Stampo un messaggio di errore se l'utente inserisce valori nulli o sbagliati
        if (request.getParameter("usr") != null && request.getParameter("psw") != null) {
            request.setAttribute("error", "wrongdata");
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);

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
