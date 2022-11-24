/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import backup.InventarioRevistasFix;
import com.google.gson.Gson;
import controldao.CatalogoRevistasJpaController;
import controldao.InventarioRevistasJpaController;
import dao.CatalogoRevistas;
import dao.InventarioRevistas;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luisg
 */
@WebServlet(name = "InventariarRevista", urlPatterns = {"/InventariarRevista"})
public class InventariarRevista extends HttpServlet {

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
        CatalogoRevistas catalogoRevistas = null;
        InventarioRevistas inventarioRevistas = null;
        InventarioRevistasJpaController controlInventario = new InventarioRevistasJpaController();
        CatalogoRevistasJpaController controlRevistas = new CatalogoRevistasJpaController();
        List<InventarioRevistas> listaInventario = controlInventario.findInventarioRevistasEntities();
        List<CatalogoRevistas> listaRevistas = controlRevistas.findCatalogoRevistasEntities();
        List<InventarioRevistasFix> listaFix = new ArrayList<>();
        
        response.setContentType("application/json");
        String revista = request.getParameter("revista");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        for (CatalogoRevistas cR : listaRevistas) {
            if (revista.equals(cR.getTitulo())) {
                catalogoRevistas = cR;
            }
        }
        
        for (InventarioRevistas iR : listaInventario) {
            if (catalogoRevistas == iR.getIsbnRevista()) {
                inventarioRevistas = iR;
            }
        }
        
        if (inventarioRevistas != null) {
            inventarioRevistas.setCantidad(inventarioRevistas.getCantidad() + cantidad);
            
            try {
                controlInventario.edit(inventarioRevistas);
            } catch (Exception e) {
                Logger.getLogger(InventariarRevista.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            inventarioRevistas = new InventarioRevistas(cantidad, catalogoRevistas);
            
            try {
                controlInventario.create(inventarioRevistas);
            } catch (Exception e) {
                Logger.getLogger(InventariarRevista.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        listaInventario = controlInventario.findInventarioRevistasEntities();
        
        for (InventarioRevistas iR : listaInventario) {
            InventarioRevistasFix iRF = new InventarioRevistasFix(iR.getId(), iR.getCantidad(), iR.getIsbnRevista().getIsbn());
            listaFix.add(iRF);
        }
        
        String inventario = gson.toJson(listaFix);
        try (PrintWriter out = response.getWriter()) {
            out.println(inventario);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
