/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.ChoiceController;
import controller.QuestionController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Choice;

/**
 *
 * @author Win 10
 */
public class AddChoiceServlet extends HttpServlet {

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
        int quesId = Integer.parseInt(request.getParameter("quesid"));
        String Answer = request.getParameter("cAns");
        boolean isC = request.getParameter("isCorrect") != null;
        ChoiceController cc = new ChoiceController();
        QuestionController qc = new QuestionController();
        Choice c = new Choice(cc.findLastChoice()+1, Answer, isC, quesId);
        int quizid = qc.findById(quesId).getQuizId();
        cc.insertChoiceToDB(c);
        request.setAttribute("message", "Add New Choice");
        response.sendRedirect("/KamuuPrototype/EditQuiz");
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
        int quesid = Integer.parseInt(request.getParameter("quesid"));
        ChoiceController cc = new ChoiceController();
        Choice c = new Choice(cc.findLastChoice()+1, "", false, quesid);
        request.setAttribute("choice", c);
        getServletContext().getRequestDispatcher("/WEB-INF/view/AddChoice.jsp").forward(request, response);
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
