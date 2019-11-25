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
import model.Choice;

/**
 *
 * @author Win 10
 */
public class ChoiceController {
    private final String FIND_BY_ID = "SELECT * FROM QUESTION_CHOICES WHERE CHOICE_ID = ?";
    private final String IS_RIGHT_CHOICE = "SELECT * FROM QUESTION_CHOICES WHERE QUESTION_ID = ? AND IS_RIGHT_CHOICE = '1'";
    private final String FIND_BY_QUESTIONID = "SELECT * FROM QUESTION_CHOICES WHERE QUESTION_ID = ?";
    private final String FIND_LASTCHOICE_ID = "SELECT MAX(CHOICE_ID) AS MAX_CHOICE_ID FROM QUESTION_CHOICES";
    private final String ADD_CHOICE = "INSERT INTO QUESTION_CHOICES (CHOICE_ID, CHOICE_ANS, IS_RIGHT_CHOICE, QUESTION_ID) VALUES (?,?,?,?)";
    private final String UPDATE_CHOICE = "UPDATE QUESTION_CHOICES SET CHOICE_ANS = ?, IS_RIGHT_CHOICE = ? WHERE CHOICE_ID = ?";
    private final String REMOVE_CHOICE = "DELETE FROM QUESTION_CHOICES WHERE WHERE CHOICE_ID = ?";
    
    public boolean removeChoice(int id){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(REMOVE_CHOICE);
            pstm.setInt(1, id);
            int rs = pstm.executeUpdate();
            conn.close();
            return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean updateChoice(Choice c){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(UPDATE_CHOICE);
            pstm.setString(1, c.getChoiceAns());
            pstm.setBoolean(2, c.isIsRightChoice());
            pstm.setInt(3, c.getChoiceId());
            int rs = pstm.executeUpdate();
            conn.close();
            return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public int findLastChoice(){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_LASTCHOICE_ID);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("MAX_CHOICE_ID");
            }
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
    
    public boolean insertChoiceToDB(Choice c){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(ADD_CHOICE);
            pstm.setInt(1, findLastChoice()+1);
            pstm.setString(2, c.getChoiceAns());
            pstm.setBoolean(3, c.isIsRightChoice());
            pstm.setInt(4, c.getQuestionId());
            pstm.executeUpdate();
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    
    public Choice findById(int id){
        Choice c = null;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_ID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                c = new Choice(rs.getInt("CHOICE_ID"), rs.getString("CHOICE_ANS"), rs.getBoolean("IS_RIGHT_CHOICE"), rs.getInt("QUESTION_ID"));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return c;
    }
    
    public ArrayList<Choice> isRightChoice(int questionId){
        ArrayList<Choice> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(IS_RIGHT_CHOICE);
            pstm.setInt(1, questionId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Choice(rs.getInt("CHOICE_ID"), rs.getString("CHOICE_ANS"), rs.getBoolean("IS_RIGHT_CHOICE"), rs.getInt("QUESTION_ID")));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
    
    public ArrayList<Choice> findByQuestionId(int questionId){
        ArrayList<Choice> ary = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_QUESTIONID);
            pstm.setInt(1, questionId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new Choice(rs.getInt("CHOICE_ID"), rs.getString("CHOICE_ANS"), rs.getBoolean("IS_RIGHT_CHOICE"), rs.getInt("QUESTION_ID")));
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return ary;
    }
}
