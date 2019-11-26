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
    private final String FIND_BY_QUIZID = "SELECT * FROM QUIZ_QUESTIONS WHERE QUIZ_ID = ? ORDER BY QUESTION_ID ASC";
    private final String ADD_QUESTION = "INSERT INTO QUIZ_QUESTIONS (QUESTION_ID, QUESTION_TITLE, QUIZ_ID) VALUES (?,?,?)";
    private final String FIND_LASTQUESTION_ID = "SELECT MAX(QUESTION_ID) AS MAX_QUESTION_ID FROM QUIZ_QUESTIONS";
    private final String UPDATE_QUESTION = "UPDATE QUIZ_QUESTIONS SET QUESTION_TITLE = ? WHERE QUESTION_ID = ?";
    private final String REMOVE_CHOICE = "DELETE FROM QUESTION_CHOICES WHERE QUESTION_ID = ?";
    private final String REMOVE_QUESTION = "DELETE FROM QUIZ_QUESTIONS WHERE QUESTION_ID = ?";
    
    public boolean removeQuestion(int id){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(REMOVE_CHOICE);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            pstm = conn.prepareStatement(REMOVE_QUESTION);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            conn.close();
            return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean updateQuestion(Question q){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(UPDATE_QUESTION);
            pstm.setString(1, q.getQuestionTitle());
            pstm.setInt(2, q.getQuestionId());
            int rs = pstm.executeUpdate();
            conn.close();
            return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public int findLastQuestionId(){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_LASTQUESTION_ID);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("MAX_QUESTION_ID");
            }
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    
    public boolean insertQuestionToDB(Question q){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(ADD_QUESTION);
            pstm.setInt(1, findLastQuestionId()+1);
            pstm.setString(2, q.getQuestionTitle());
            pstm.setInt(3, q.getQuizId());
            int rs = pstm.executeUpdate();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    
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
            return ary;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
