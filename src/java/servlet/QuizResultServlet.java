/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AnswerController;
import controller.QuestionController;
import controller.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Answer;
import model.KamuuUser;

/**
 *
 * @author Win 10
 */
public class QuizResultServlet extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("quizid"));
        AnswerController ac = new AnswerController();
        QuestionController qc = new QuestionController();
        HashMap<KamuuUser,Integer> hm = ac.scoreByQuizId(id);
        if(hm.isEmpty()){
            request.setAttribute("message", "No response yet.");
            request.setAttribute("noScore", true);
            getServletContext().getRequestDispatcher("/WEB-INF/view/QuizResult.jsp").forward(request, response);
        }
        else{
            int totalScore = 0;
        int totalStudent = hm.values().size();
        for(int i : hm.values()){
            totalScore+=i;
        }
        double avg = totalScore/totalStudent;
        ArrayList<Double> dary = new ArrayList<>();
        for(int i : hm.values()){
            dary.add(i-avg);
        }
        double variance = 0;
        for(double d : dary){
            variance += Math.pow(d, 2);
        }
        int max = Collections.max(hm.values(), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int min = Collections.min(hm.values(), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        variance/=totalStudent;
        double sd1 = Math.sqrt(variance);
        String sd = String.format("%.1f", sd1);
        request.setAttribute("noScore", false);
        request.setAttribute("mean", avg);
        request.setAttribute("sd", sd);
        request.setAttribute("max", max);
        request.setAttribute("min", min);
        request.setAttribute("scores", hm);
        request.setAttribute("fullscore", qc.findByQuizId(id).size());
        getServletContext().getRequestDispatcher("/WEB-INF/view/QuizResult.jsp").forward(request, response);
        }
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
