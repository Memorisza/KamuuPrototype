/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.KamuuUser;

/**
 *
 * @author Win 10
 */
public class LoginServlet extends HttpServlet {

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
        String user = request.getParameter("user");
        String password = request.getParameter("pass");
        String msg;
        if(user.isEmpty() || password.isEmpty()){
            msg = "Incorrect format";
            request.setAttribute("message", msg);
            getServletContext().getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
        }
        UserController uc = new UserController();
        KamuuUser ku = uc.findByUsername(user);
        if(ku == null){
            msg = "User not found";
            request.setAttribute("message", msg);
            getServletContext().getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
        }
        System.out.println(ku.getPassword());
        System.out.println(password);
        if(!ku.getPassword().equals(password)){
            msg = "Wrong Password";
            request.setAttribute("message", msg);
            getServletContext().getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", ku);
        getServletContext().getRequestDispatcher("/WEB-INF/view/KamuuIndex.jsp").forward(request, response);
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
        HttpSession session = request.getSession(false);
        if(session == null){
            getServletContext().getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
        }
        KamuuUser ku = (KamuuUser)session.getAttribute("user");
        if(ku == null){
            getServletContext().getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/KamuuIndex.jsp").forward(request, response);
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
