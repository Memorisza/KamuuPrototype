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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KamuuUser;
import model.Quiz;

/**
 *
 * @author Win 10
 */
public class QuizController {
    private final String FIND_BY_ID = "SELECT * FROM QUIZ WHERE QUIZ_ID = ?";
    private final String FIND_BY_QNAME = "SELECT * FROM QUIZ WHERE QUIZ_NAME = ?";
    private final String FIND_ACTIVEQUIZ = "SELECT * FROM QUIZ WHERE IS_ACTIVE = '1'";
    private final String FIND_INACTIVEQUIZ = "SELECT * FROM QUIZ WHERE IS_ACTIVE = '0'";
    private final String FIND_BY_TID = "SELECT * FROM QUIZ WHERE TEACHER_ID = ?";
    private final String FIND_REMAINQUIZ = "SELECT DISTINCT QUIZ_ID,QUIZ_NAME,IS_ACTIVE,TEACHER_ID FROM QUIZ WHERE IS_ACTIVE = '1' AND QUIZ_ID NOT IN (SELECT DISTINCT q2.QUIZ_ID FROM QUIZ q2, USER_ANSWER u WHERE q2.QUIZ_ID = u.QUIZ_ID AND u.USER_ID = ?)";
    private final String ADD_NEWQUIZ = "INSERT INTO QUIZ (QUIZ_ID,QUIZ_NAME,IS_ACTIVE,TEACHER_ID) VALUES(?,?,?,?)";
    private final String FIND_LASTQUIZID = "SELECT MAX(QUIZ_ID) AS MAX_QUIZ_ID FROM QUIZ";
    private final String REMOVE_ANS = "DELETE FROM USER_ANSWER WHERE QUIZ_ID = ?";
    private final String REMOVE_CHOICE = "DELETE FROM QUESTION_CHOICES WHERE QUESTION_ID IN (SELECT QUESTION_ID FROM QUIZ_QUESTIONS WHERE QUIZ_ID = ?)";
    private final String REMOVE_QUES = "DELETE FROM QUIZ_QUESTIONS WHERE QUIZ_ID = ?";
    private final String REMOVE_QUIZ = "DELETE FROM QUIZ WHERE QUIZ_ID = ?";
    private final String UPDATE_QUIZ = "UPDATE QUIZ SET QUIZ_NAME = ?, IS_ACTIVE = ? WHERE QUIZ_ID = ?";
    
    public boolean updateQuiz(Quiz q){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(UPDATE_QUIZ);
            pstm.setString(1, q.getQuizName());
            pstm.setBoolean(2, q.isIsActive());
            pstm.setInt(3, q.getQuizId());
            int rs = pstm.executeUpdate();
            conn.close();
            return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean removeQuizById(int id){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(REMOVE_ANS);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            pstm = conn.prepareStatement(REMOVE_CHOICE);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            pstm = conn.prepareStatement(REMOVE_QUES);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            pstm = conn.prepareStatement(REMOVE_QUIZ);
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
    
    public boolean addNewQuiz(Quiz q){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(ADD_NEWQUIZ);
            pstm.setInt(1, findLastQuizId()+1);
            pstm.setString(2, q.getQuizName());
            pstm.setBoolean(3, q.isIsActive());
            pstm.setInt(4, q.getTeacherId());
            int rs = pstm.executeUpdate();
            return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public int findLastQuizId(){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_LASTQUIZID);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                return rs.getInt("MAX_QUIZ_ID");
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return -1;
    }
    
    public Quiz findById(int id){
        Quiz q = null;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_ID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                q = new Quiz(rs.getInt("QUIZ_ID"), rs.getString("QUIZ_NAME"), rs.getBoolean("IS_ACTIVE"), rs.getInt("TEACHER_ID"));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return q;
    }
    
    public Quiz findByQname(String qname){
        Quiz q = null;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_QNAME);
            pstm.setString(1, qname);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                q = new Quiz(rs.getInt("QUIZ_ID"), rs.getString("QUIZ_NAME"), rs.getBoolean("IS_ACTIVE"), rs.getInt("TEACHER_ID"));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return q;
    }
    
    public ArrayList<Quiz> findByTeacherId(int id){
        ArrayList<Quiz> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_TID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Quiz(rs.getInt("QUIZ_ID"), rs.getString("QUIZ_NAME"), rs.getBoolean("IS_ACTIVE"), rs.getInt("TEACHER_ID")));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
    
    
    public ArrayList<Quiz> findActiveQuiz(){
        ArrayList<Quiz> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_ACTIVEQUIZ);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Quiz(rs.getInt("QUIZ_ID"), rs.getString("QUIZ_NAME"), rs.getBoolean("IS_ACTIVE"), rs.getInt("TEACHER_ID")));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
    
    public ArrayList<Quiz> findInactiveQuiz(){
        ArrayList<Quiz> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_INACTIVEQUIZ);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Quiz(rs.getInt("QUIZ_ID"), rs.getString("QUIZ_NAME"), rs.getBoolean("IS_ACTIVE"), rs.getInt("TEACHER_ID")));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
    
    public ArrayList<Quiz> findRemainQuizByUser(KamuuUser ku){
        ArrayList<Quiz> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_REMAINQUIZ);
            pstm.setInt(1, ku.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Quiz(rs.getInt("QUIZ_ID"), rs.getString("QUIZ_NAME"), rs.getBoolean("IS_ACTIVE"), rs.getInt("TEACHER_ID")));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
}
