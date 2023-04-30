/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import tables.CatalogoRevistas;
import webserviceclients.CatalogoRevistasFacadeRESTClient;

/**
 *
 * @author luisg
 */
@WebServlet(name = "AgregaRevista", urlPatterns = {"/AgregaRevista"})
public class AgregaRevista extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        CatalogoRevistasFacadeRESTClient client = new CatalogoRevistasFacadeRESTClient();
        
        response.setContentType("application/json");
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String editorial = request.getParameter("editorial");
        String clasificacion = request.getParameter("clasificacion");
        String periodicidad = request.getParameter("periodicidad");
        String fecha = request.getParameter("fecha");
        
        if (periodicidad.isBlank()) {
            periodicidad = null;
        }
        
        if (fecha.isBlank()) {
            fecha = null;
        }
        
        CatalogoRevistas revista = new CatalogoRevistas(isbn, titulo, editorial, clasificacion, periodicidad, fecha);
        String revistaJson = gson.toJson(revista);
        
        try {
            client.create_JSON(revistaJson);
            
            String listaRevistas = client.findAll_JSON(String.class);
            try (PrintWriter out = response.getWriter()) {
                out.println(listaRevistas);
                out.flush();
            }
        } catch (ClientErrorException cee) {
            Logger.getLogger(AgregaRevista.class.getName()).log(Level.SEVERE, null, cee);
        }
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
