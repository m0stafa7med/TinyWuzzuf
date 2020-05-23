/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Exam;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class ExamDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Vector<Exam> getAllExam()
    {
        Vector<Exam> list=null;
        Exam exam=null;	
        try{
            list = new Vector<Exam>();
            String sql = "SELECT * FROM `exam` WHERE 1";
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                exam=new Exam(resultSet.getString(1),resultSet.getString(2));
                list.add(exam);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (list);
    }
    public Exam getExam(String type)
    {
        Exam exam=null;	
        try{
            String sql = "SELECT * FROM `exam` WHERE type="+"'"+type+"'";
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                exam=new Exam(resultSet.getString(1),resultSet.getString(2));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (exam);
    }
    public boolean add(String type,String title)
    {
        boolean flag=false;
        try{
            String sql="insert into `exam` VALUES("
                    + "'" + type + "'" + ","
                    +"'" + title +"'"
                    +")"; 
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            flag=true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (flag);
    }
    public boolean update(String type,String newType)
    {
        boolean flag=false;
        try{
            String sql="update `exam` set `type` = "
                    + "'" + newType + "'" + " where `type` = "
                    +"'" + type +"'"
                    ; 
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            flag=true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (flag);
    }
    public boolean delete(String type)
    {
        boolean flag=false;
        try{
            String sql="delete from `exam` where `type` = "
                    + "'" + type + "'" 
                    ; 
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            flag=true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (flag);
    }
}
