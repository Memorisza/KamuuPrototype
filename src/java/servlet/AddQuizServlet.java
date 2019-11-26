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
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Choice;
import model.KamuuUser;
import model.Question;
import model.Quiz;

/**
 *
 * @author Win 10
 */
public class AddQuizServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        if(qName.isEmpty()){
            request.setAttribute("message", "Incorrect format");
            getServletContext().getRequestDispatcher("/WEB-INF/view/AddQuiz.jsp").forward(request, response);
        }
        KamuuUser ku = (KamuuUser)session.getAttribute("user");
        QuizController qc = new QuizController();
        if(qc.findByQname(qName) == null) {
            Quiz q = new Quiz(qc.findLastQuizId()+1, qName, isAct, ku.getId());
            if(qc.addNewQuiz(q)){
                session.setAttribute("newquiz", q);
                session.setAttribute("rAdd", false);
                request.setAttribute("message", "Quiz Saved.");
                ChoiceController cc = new ChoiceController();
                QuestionController quc = new QuestionController();
                ArrayList<Question> qary = quc.findByQuizId(q.getQuizId());
                if(qary.isEmpty()){
                    getServletContext().getRequestDispatcher("/WEB-INF/view/AddQuiz.jsp").forward(request, response);
                }
                HashMap<Question,ArrayList<Choice>> hm = new HashMap<>();
                qary.forEach((n) -> hm.put(n, cc.findByQuestionId(n.getQuestionId())));
        //        Iterator<Question> i = qary.iterator();
        //        while(i.hasNext()){
        //            Question qn = i.next();
        //            hm.put(qn, cc.findByQuestionId(qn.getQuestionId()));
        //        }
                request.setAttribute("message", "Add New Quiz");
                request.setAttribute("quizes", hm);
                getServletContext().getRequestDispatcher("/WEB-INF/view/AddQuiz.jsp").forward(request, response);
            }
        }
        request.setAttribute("message", "Quiz Name has already used.");
        getServletContext().getRequestDispatcher("/WEB-INF/view/AddQuiz.jsp").forward(request, response);
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
        KamuuUser ku = (KamuuUser) session.getAttribute("user");
        Quiz q = (Quiz) session.getAttribute("newquiz");
        if(q == null){
            Quiz qq = new Quiz(0,"",false,ku.getId());
            session.setAttribute("newquiz", qq);
            session.setAttribute("rAdd", true);
            getServletContext().getRequestDispatcher("/WEB-INF/view/AddQuiz.jsp").forward(request, response);
        }
        QuestionController qc = new QuestionController();
        ChoiceController cc = new ChoiceController();
        ArrayList<Question> qary = qc.findByQuizId(q.getQuizId());
        if(qary.isEmpty()){
            getServletContext().getRequestDispatcher("/WEB-INF/view/AddQuiz.jsp").forward(request, response);
        }
        HashMap<Question,ArrayList<Choice>> hm = new HashMap<>();
        qary.forEach((n) -> hm.put(n, cc.findByQuestionId(n.getQuestionId())));
//        Iterator<Question> i = qary.iterator();
//        while(i.hasNext()){
//            Question qn = i.next();
//            hm.put(qn, cc.findByQuestionId(qn.getQuestionId()));
//        }
        request.setAttribute("quizes", hm);
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
