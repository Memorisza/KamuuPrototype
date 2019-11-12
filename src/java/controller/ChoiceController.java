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
