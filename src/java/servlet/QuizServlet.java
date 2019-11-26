/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AnswerController;
import controller.ChoiceController;
import controller.QuestionController;
import controller.QuizController;
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
import model.Quiz;

/**
 *
 * @author Win 10
 */
public class QuizServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        KamuuUser ku = (KamuuUser)session.getAttribute("user");
        HashMap<Question,ArrayList<Choice>> hm = (HashMap<Question,ArrayList<Choice>>)session.getAttribute("quizes");
        ArrayList<Choice> cary = new ArrayList<>();
        ArrayList<Integer> iary = new ArrayList<>();
        for(int i = 1;i<=hm.size();i++){
            String sc = "question_"+i;
            System.out.println(sc);
            if(request.getParameter(sc) == null){
                request.setAttribute("message", "Answer are not completed.");
                getServletContext().getRequestDispatcher("/WEB-INF/view/Quiz.jsp").forward(request, response);
            }
            int ic = Integer.parseInt(request.getParameter(sc));
            iary.add(ic);
        }
        for(ArrayList<Choice> nary : hm.values()){
            for(Choice c : nary){
                for(int i : iary){
                    if(c.getChoiceId() == i){
                        QuestionController qc = new QuestionController();
                        AnswerController ac = new AnswerController();
                        Answer a = new Answer(0,ku.getId(), qc.findById(c.getQuestionId()).getQuizId(), c.getQuestionId() , c.getChoiceId(), c.isIsRightChoice());
                        ac.ADD_ANSWER(a);
                    }
                }
            }
        }
        request.setAttribute("message", "Quiz submitted.");
        response.sendRedirect("/KamuuPrototype/Login");
     //Fighting Atis!!   
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
        int quizId = Integer.parseInt(request.getParameter("quizid"));
        QuestionController qc = new QuestionController();
        ChoiceController cc = new ChoiceController();
        ArrayList<Question> ary = qc.findByQuizId(quizId);
        HashMap<Question,ArrayList<Choice>> hm = new HashMap();
        for(Question q : ary){
            hm.put(q, cc.findByQuestionId(q.getQuestionId()));
        }        
        System.out.println(hm.size());
        HttpSession session = request.getSession(false);
        session.setAttribute("quizes", hm);
        getServletContext().getRequestDispatcher("/WEB-INF/view/Quiz.jsp").forward(request, response);
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
