/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AnswerController;
import controller.ChoiceController;
import controller.QuestionController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Answer;
import model.Choice;
import model.KamuuUser;
import model.Question;

/**
 *
 * @author Win 10
 */
public class StudentResultServlet extends HttpServlet {

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
        int quizId = Integer.parseInt(request.getParameter("quizid"));
        int uscore = 0;
        HttpSession session = request.getSession();
        KamuuUser ku = (KamuuUser)session.getAttribute("user");
        AnswerController ac = new AnswerController();
        QuestionController qc = new QuestionController();
        ChoiceController cc = new ChoiceController();
        ArrayList<Answer> aary = ac.findByUserNQuiz(ku, quizId);
        ArrayList<Question> qary = qc.findByQuizId(quizId);
        ArrayList<Choice> cary = new ArrayList<>();
        HashMap<Question,ArrayList<Choice>> hm = new HashMap();
        for(Answer a : aary){
            cary.add(cc.findById(a.getChoiceId()));
            if(a.isIsRight()){
                uscore++;
            }
        }
        for(Question q : qary){
            hm.put(q, cc.isRightChoice(q.getQuestionId()));
        }
        request.setAttribute("quiz", hm);
        request.setAttribute("score", uscore);
        request.setAttribute("uans", cary);
        getServletContext().getRequestDispatcher("/WEB-INF/view/StudentResult.jsp").forward(request, response);
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
