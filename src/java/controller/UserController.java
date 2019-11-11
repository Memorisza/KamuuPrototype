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

/**
 *
 * @author Win 10
 */
public class UserController {
    private final String FIND_BY_USERNAME = "SELECT * FROM KAMUUUSER WHERE USERNAME = ?";
    private final String FIND_BY_ID = "SELECT * FROM KAMUUUSER WHERE USER_ID = ?";
    private final String FIND_BY_NAME = "SELECT * FROM KAMUUUSER WHERE NAME LIKE '?%'";
    private final String FIND_BY_SURNAME = "SELECT * FROM KAMUUUSER WHERE SURNAME LIKE '?%'";
    private final String ADD_NEWUSER = "INSERT INTO KAMUUUSER (USER_ID,NAME,SURNAME,USERNAME,PASSWORD,ROLE)"
            + "VALUES (?,?,?,?,?,?)";
    private final String FIND_LASTUSERID = "SELECT MAX(USER_ID) FROM KAMUUUSER";
    
    public int findLastIndexUser(){
        int i = -1;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_LASTUSERID);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                i = rs.getInt("1");
            }
            conn.close();
        }
        catch(SQLException e){
            System.out.println("Error Occured");
        }
        return i;
    }
    
    public KamuuUser findById(int id){
        KamuuUser u = null;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_ID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                u = new KamuuUser(rs.getInt("USER_ID"),rs.getString("NAME"),rs.getString("SURNAME"),rs.getString("USERNAME"),rs.getString("PASSWORD"),rs.getString("ROLE"));
            }
            conn.close();
        }
        catch(SQLException e){
            System.out.println("Error Occured");
        }
        return u;
    }
    
    public KamuuUser findByUsername(String username){
        KamuuUser u = null;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_USERNAME);
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                u = new KamuuUser(rs.getInt("USER_ID"),rs.getString("NAME"),rs.getString("SURNAME"),rs.getString("USERNAME"),rs.getString("PASSWORD"),rs.getString("ROLE"));
            }
            conn.close();
        }
        catch(SQLException e){
            System.out.println("Error");
        }
        return u;
    }
    
    public ArrayList<KamuuUser> findByName(String name){
        ArrayList<KamuuUser> ary = new ArrayList();
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_NAME);
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new KamuuUser(rs.getInt("USER_ID"),rs.getString("NAME"),rs.getString("SURNAME"),rs.getString("USERNAME"),rs.getString("PASSWORD"),rs.getString("ROLE")));
            }
            conn.close();
        }
        catch(SQLException e){
            System.out.println("Error Occured");
        }
        return ary;
    }
    
    public ArrayList<KamuuUser> findBySurname(String surname){
        ArrayList<KamuuUser> ary = null;
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(FIND_BY_SURNAME);
            pstm.setString(1, surname);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ary.add(new KamuuUser(rs.getInt("USER_ID"),rs.getString("NAME"),rs.getString("SURNAME"),rs.getString("USERNAME"),rs.getString("PASSWORD"),rs.getString("ROLE")));
            }
            conn.close();
        }
        catch(SQLException e){
            System.out.println("Error Occured");
        }
        return ary;
    }
    
    public boolean addNewUser(KamuuUser ku){
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement pstm = conn.prepareStatement(ADD_NEWUSER);
            int last = findLastIndexUser();
            System.out.println(last);
            pstm.setInt(1, last+1);
            pstm.setString(2, ku.getName());
            pstm.setString(3, ku.getSurname());
            pstm.setString(4, ku.getUsername());
            pstm.setString(5, ku.getPassword());
            pstm.setString(6, ku.getRole());
            int rs = pstm.executeUpdate();
            conn.close();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
