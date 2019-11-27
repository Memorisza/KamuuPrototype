/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.ChoiceController;
import controller.QuestionController;
import controller.QuizController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Choice;
import model.Question;
import model.Quiz;

/**
 *
 * @author Win 10
 */
public class EditQuizServlet extends HttpServlet {

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
        String qName = request.getParameter("quizN");
        boolean isAct = request.getParameter("quizAct") != null;
        int id = Integer.parseInt(request.getParameter("quizid"));
        boolean flag = true;
        QuizController qc = new QuizController();
        QuestionController quc = new QuestionController();
        ChoiceController cc = new ChoiceController();
        if (isAct) {
            ArrayList<Question> qary = quc.findByQuizId(id);
            System.out.println(quc.findByQuizId(id));
            if (qary.isEmpty()) {
                flag = false;
                request.setAttribute("message", "The Quiz is incompleted.");
            }
            for (Question q : qary) {
                if (cc.isRightChoice(q.getQuestionId()).isEmpty()) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag == true) {
            Quiz q = qc.findById(id);
            q.setQuizName(qName);
            q.setIsActive(isAct);
            qc.updateQuiz(q);
            request.setAttribute("message", "Quiz Saved.");
        }
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
        HttpSession session = request.getSession(false);
        int id = -1;
        if (session.getAttribute("quizid") == null) {
            id = Integer.parseInt(request.getParameter("quizid"));
        } else {
            id = (Integer) session.getAttribute("quizid");
        }
        QuizController qc = new QuizController();
        QuestionController quc = new QuestionController();
        ChoiceController cc = new ChoiceController();
        HashMap<Question, ArrayList<Choice>> hm = new HashMap();
        ArrayList<Question> ary = quc.findByQuizId(id);
        
        for (Question q : ary) {
            System.out.println(q);
            hm.put(q, cc.findByQuestionId(q.getQuestionId()));
        }
        TreeMap<Question, ArrayList<Choice>> sorted = new TreeMap<>(hm);
        session.setAttribute("quizid", id);
        request.setAttribute("newquiz", qc.findById(id));
        request.setAttribute("quizes", sorted);
        request.setAttribute("rAdd", false);
        request.setAttribute("message", "Quiz Management");
        getServletContext().getRequestDispatcher("/WEB-INF/view/AddQuiz.jsp").forward(request, response);
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
