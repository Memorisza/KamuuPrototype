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
import java.util.HashMap;
import model.Answer;
import model.KamuuUser;
import model.Quiz;

/**
 *
 * @author Win 10
 */
public class AnswerController {
    private final String RECORD_NEW_ANSWER = "INSERT INTO USER_ANSWER (TRANS_ID,USER_ID,QUIZ_ID,QUESTION_ID,CHOICE_ID,IS_RIGHT)"
            + "VALUES (?,?,?,?,?,?)";
    private final String FIND_BY_USER = "SELECT * FROM USER_ANSWER WHERE USER_ID = ?";
    private final String FIND_LASTTRANSID = "SELECT MAX(TRANS_ID) FROM USER_ANSWER";
    private final String FIND_QUIZBYUSER = "SELECT DISTINCT QUIZ_ID FROM USER_ANSWER WHERE USER_ID = ?"; 
    private final String FIND_BY_USERNQUIZ = "SELECT * FROM USER_ANSWER WHERE USER_ID = ? AND QUIZ_ID = ?";
    private final String SCORE_BY_QUIZID = "SELECT USER_ID,COUNT(IS_RIGHT) AS SCORE FROM USER_ANSWER WHERE QUIZ_ID = ? AND IS_RIGHT = '1' GROUP BY USER_ID";
    
    public HashMap<KamuuUser,Integer> scoreByQuizId(int id){
        HashMap<KamuuUser,Integer> hm = new HashMap<>();
        UserController uc = new UserController();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(SCORE_BY_QUIZID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                hm.put(uc.findById(rs.getInt("USER_ID")), rs.getInt("SCORE"));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return hm;
    }
    
    
     public int findLastTransID(){
        int i = -1;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_LASTTRANSID);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                i = rs.getInt("1");
            }
            else {
                conn.close();
                return 0;
            }
            conn.close();
        }
        catch(SQLException e){
            System.out.println("Error Occured");
        }
        return i;
    }
    
    public boolean ADD_ANSWER(Answer a){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(RECORD_NEW_ANSWER);
            pstm.setInt(1, findLastTransID()+1);
            pstm.setInt(2, a.getUserId());
            pstm.setInt(3, a.getQuizId());
            pstm.setInt(4, a.getQuestionId());
            pstm.setInt(5, a.getChoiceId());
            pstm.setBoolean(6, a.isIsRight());
            int rs = pstm.executeUpdate();
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return true;
    }
    
    public ArrayList<Answer> findByUserNQuiz(KamuuUser ku, int quizId){
        ArrayList<Answer> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_USERNQUIZ);
            pstm.setInt(1, ku.getId());
            pstm.setInt(2, quizId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Answer(rs.getInt("TRANS_ID"),rs.getInt("USER_ID"), rs.getInt("QUIZ_ID"), rs.getInt("QUESTION_ID"), rs.getInt("CHOICE_ID"), rs.getBoolean("IS_RIGHT")));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
    
    public ArrayList<Answer> findByUser(KamuuUser ku){
        ArrayList<Answer> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_USER);
            pstm.setInt(1, ku.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Answer(rs.getInt("TRANS_ID"),rs.getInt("USER_ID"), rs.getInt("QUIZ_ID"), rs.getInt("QUESTION_ID"), rs.getInt("CHOICE_ID"), rs.getBoolean("IS_RIGHT")));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
    
    public ArrayList<Integer> findQuizByUser(KamuuUser ku){
        ArrayList<Integer> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_QUIZBYUSER);
            pstm.setInt(1, ku.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(rs.getInt("QUIZ_ID"));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
        //Fighting Atissssssss!!!!!!!!!
    }
}
