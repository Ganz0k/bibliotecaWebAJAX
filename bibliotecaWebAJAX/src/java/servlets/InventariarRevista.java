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
import tables.InventarioRevistas;
import webserviceclients.CatalogoRevistasFacadeRESTClient;
import webserviceclients.InventarioRevistasFacadeRESTClient;

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
        InventarioRevistasFacadeRESTClient inventarioClient = new InventarioRevistasFacadeRESTClient();
        CatalogoRevistasFacadeRESTClient catalogoClient = new CatalogoRevistasFacadeRESTClient();
        InventarioRevistas inventario = null;
        
        response.setContentType("application/json");
        String revista = request.getParameter("revista");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        try {
            String revistaEncontrada = catalogoClient.find_JSON(String.class, revista);
            CatalogoRevistas catalogoRevista = gson.fromJson(revistaEncontrada, CatalogoRevistas.class);
            
            int n = Integer.parseInt(inventarioClient.countREST());
            
            for (int i = 1; i <= n; i++) {
                String iR = inventarioClient.find_JSON(String.class, String.valueOf(i));
                inventario = gson.fromJson(iR, InventarioRevistas.class);
                
                if (inventario.getIsbnRevista().equals(catalogoRevista)) {
                    int cantidadOld = inventario.getCantidad();
                    inventario.setCantidad(cantidad + cantidadOld);
                    inventarioClient.edit_JSON(gson.toJson(inventario), String.valueOf(inventario.getIdInventario()));
                }
                
                break;
            }
            
            if (inventario == null) {
                inventario = new InventarioRevistas(cantidad, catalogoRevista);
                inventarioClient.create_JSON(gson.toJson(inventario));
            }
            
            String listaInventario = inventarioClient.findAll_JSON(String.class);
            
            try (PrintWriter out = response.getWriter()) {
                out.println(listaInventario);
                out.flush();
            }
        } catch (ClientErrorException cee) {
            Logger.getLogger(InventariarRevista.class.getName()).log(Level.SEVERE, null, cee);
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
