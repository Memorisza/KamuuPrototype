/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbconnect.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Question;

/**
 *
 * @author Win 10
 */
public class QuestionController {
    private final String FIND_BY_ID = "SELECT * FROM QUIZ_QUESTIONS WHERE QUESTION_ID = ?";
    private final String FIND_BY_TITLE = "SELECT * FROM QUIZ_QUESTIONS WHERE QUESTION_TITLE LIKE '?%'";
    private final String FIND_BY_QUIZID = "SELECT * FROM QUIZ_QUESTIONS WHERE QUIZ_ID = ?";
    
    public Question findById(int id){
        Question q = null;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_ID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                q = new Question(rs.getInt("QUESTION_ID"), rs.getString("QUESTION_TITLE"), rs.getInt("QUIZ_ID"));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return q;
    }
    
    public ArrayList<Question> findByTitle(String title){
        ArrayList<Question> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_TITLE);
            pstm.setString(1, title);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Question(rs.getInt("QUESTION_ID"), rs.getString("QUESTION_TITLE"), rs.getInt("QUIZ_ID")));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
    
    public ArrayList<Question> findByQuizId(int id){
        ArrayList<Question> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_QUIZID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Question(rs.getInt("QUESTION_ID"), rs.getString("QUESTION_TITLE"), rs.getInt("QUIZ_ID")));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
}
