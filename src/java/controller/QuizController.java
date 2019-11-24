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
    private final String FIND_BY_QNAME = "SELECT * FROM QUIZ WHERE QUIZ_NAME LIKE '?%'";
    private final String FIND_ACTIVEQUIZ = "SELECT * FROM QUIZ WHERE IS_ACTIVE = '1'";
    private final String FIND_INACTIVEQUIZ = "SELECT * FROM QUIZ WHERE IS_ACTIVE = '0'";
    private final String FIND_BY_TID = "SELECT * FROM QUIZ WHERE TEACHER_ID = ?";
    private final String FIND_REMAINQUIZ = "SELECT DISTINCT QUIZ_ID,QUIZ_NAME,IS_ACTIVE,TEACHER_ID FROM QUIZ WHERE IS_ACTIVE = '1' AND QUIZ_ID NOT IN (SELECT DISTINCT q2.QUIZ_ID FROM QUIZ q2, USER_ANSWER u WHERE q2.QUIZ_ID = u.QUIZ_ID AND u.USER_ID = ?)";
    
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
    
    public ArrayList<Quiz> findByQname(String qname){
        ArrayList<Quiz> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_QNAME);
            pstm.setString(1, qname);
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
