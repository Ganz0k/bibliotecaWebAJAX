/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import tables.Users;
import webserviceclients.UsersFacadeRESTClient;

/**
 *
 * @author luisg
 */
@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

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
        UsersFacadeRESTClient client = new UsersFacadeRESTClient();
        
        response.setContentType("application/json");
        String userJWT = request.getParameter("user");
        
        String[] jwtParts = userJWT.split("\\.");
        String encodedUser = jwtParts[1];
        String decodedUser = new String(Base64.getUrlDecoder().decode(encodedUser));
        
        Users user = gson.fromJson(decodedUser, Users.class);
        
        try {
            String existingUserJSON = client.find_JSON(String.class, user.getUsername());
            
            if (existingUserJSON != null) {
                Users existingUser = gson.fromJson(existingUserJSON, Users.class);
                
                if (!existingUser.getPassword().equals(user.getPassword())) {
                    existingUserJSON = "No such user";
                }
            } else {
                existingUserJSON = "No such user";
            }
            
            try (PrintWriter out = response.getWriter()) {
                out.println(existingUserJSON);
                out.flush();
            }
        } catch (ClientErrorException cee) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, cee);
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
