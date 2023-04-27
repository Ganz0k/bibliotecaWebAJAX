/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import backup.CatalogoRevistasFix;
import com.google.gson.Gson;
import dao.CatalogoRevistas;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        List<CatalogoRevistas> lista;
        List<CatalogoRevistasFix> listaFix = new ArrayList<>();
        CatalogoRevistasFacadeRESTClient control = new CatalogoRevistasFacadeRESTClient();
        
        response.setContentType("application/json");
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String editorial = request.getParameter("editorial");
        String clasificacion = request.getParameter("clasificacion");
        String periodicidad = request.getParameter("periodicidad");
        String sFecha = request.getParameter("fecha");
        Date fecha = null;
        
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            fecha = formatter.parse(sFecha);
        } catch (ParseException pe) {
            Logger.getLogger(AgregaRevista.class.getName()).log(Level.SEVERE, null, pe);
        }
        
        CatalogoRevistas revista = new CatalogoRevistas(isbn, titulo, editorial, clasificacion);
        revista.setPeriodicidad(periodicidad);
        revista.setFecha(fecha);
        
        control.create_JSON(revista);
        
        lista = control.findAll_JSON(List.class);
        
        for (CatalogoRevistas cR : lista) {
            CatalogoRevistasFix cRF = new CatalogoRevistasFix(cR.getIsbn(), cR.getTitulo(), cR.getEditorial(), cR.getClasificacion(), cR.getPeriodicidad(), cR.getFecha().toString());
            listaFix.add(cRF);
        }
        
        String revistas = gson.toJson(listaFix);
        
        try (PrintWriter out = response.getWriter()) {
            out.println(revistas);
            out.flush();
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
