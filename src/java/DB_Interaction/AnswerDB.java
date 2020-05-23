/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Answer;
import Models.Question;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class AnswerDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public boolean add(String text,int status,String QID)
    {
        boolean flag=false;
        try{
            String sql="insert into `answer` VALUES("
                    + "'" + UUID.randomUUID().toString() + "'" + ","
                    +"'" + QID +"'" + ","
                    +"'" + text +"'" + ","
                    +"'" + status +"'"
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
    public boolean update(String AID,String QID,String text,int status)
    {
        boolean flag=false;
        try{
            String sql="update `answer` set "
                    + "`text` = " + "'" + text + "'" + ","
                    + "`QID` = " + "'" + QID + "'" + ","
                    + "`status` = " + "'" + status + "'" 
                    +" where `AID` = "
                    +"'" + AID +"'"
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
    public boolean delete(String AID)
    {
        boolean flag=false;
        try{
            String sql="delete from `answer` where `AID` = "
                    + "'" + AID + "'" 
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
