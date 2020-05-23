/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Answer;
import Models.Exam;
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
public class SolutionDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public boolean initialSave(String type,Vector<Question> question,String email,String title)
    {	
        boolean flag=false;
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            for(int i=0;i<question.size();i++)
            {
                String sql="insert into `candidate_solution` VALUES("
                       + "'" + email + "'" + ","
                       +"'" + type +"'" + ","
                       + "'" + title + "'" + ","
                       +"'" + question.elementAt(i).getQID() +"'" + ","
                       +"'" + "null" +"'" 
                       +")"; 
               statement.executeUpdate(sql);
            }
            flag=true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (flag);
    }
    public boolean saveAnswer(String email,String type,String QID,String AID,String title)
    {
        boolean flag=false;
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="UPDATE `candidate_solution` SET"
                    + " `AID` = "+AID
                    +" WHERE email = "+email+" AND "
                    +"title = "+title+" AND "
                    +"type = "+type+" AND "
                    +"QID = "+QID;
            statement.executeUpdate(query);
            flag=true;
        }catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public String getScore(String email,String Relatedexam,String type)
    {
        int score=0;
        Vector<String> AID=new Vector<String>();
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="select `AID` from"
                    + " `candidate_solution` "
                    +" WHERE email = '"+email+"' AND "
                    +"type = '"+type+"' AND "
                    +"title = '"+Relatedexam+"'";
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                AID.add(resultSet.getString(1));
            }
            for(int i=0;i<AID.size();i++)
            {
                query="select status from `answer` where AID = '"+AID.elementAt(i)+"'";
                resultSet=statement.executeQuery(query);
                if(resultSet.next())
                {
                    if(resultSet.getString(1).equals("1"))
                    {
                        score++;
                    }
                }
            }
        }catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(score+"/"+AID.size()) ;
    }
    public String getAllScore(String email)
    {
        int score=0;
        Vector<String> AID=new Vector<String>();
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="select `AID` from"
                    + " `candidate_solution` "
                    +" WHERE email = '"+email+"'";
            resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                AID.add(resultSet.getString(1));
            }
            for(int i=0;i<AID.size();i++)
            {
                query="select status from `answer` where AID = '"+AID.elementAt(i)+"'";
                resultSet=statement.executeQuery(query);
                if(resultSet.next())
                {
                    if(resultSet.getString(1).equals("1"))
                    {
                        score++;
                    }
                }
            }
        }catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(score+"/"+AID.size()) ;
    }
    public Vector<String> getAllCandidate()
    {
        Vector<String> list=new Vector<String>();
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="SELECT DISTINCT email FROM `candidate_solution` WHERE 1";
            ResultSet rs=statement.executeQuery(query);
            while(rs.next())
            {
                list.add(rs.getString(1));
            }
        }catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Vector<String> getAllTakenTest(String email)
    {
        Vector<String> list=new Vector<String>();
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="SELECT DISTINCT type FROM `candidate_solution` WHERE email= '"+email+"'";
            ResultSet rs=statement.executeQuery(query);
            while(rs.next())
            {
                list.add(rs.getString(1));
            }
        }catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Vector<String> getTestAndSolution(String type,String email)
    {
        Vector<String> list=new Vector<String>();
        Vector<String> qList=new Vector<String>();
        Vector<String> aList=new Vector<String>();
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="SELECT * FROM `candidate_solution` WHERE email= '"+email+"' and type='"+type+"'";
            ResultSet rs=statement.executeQuery(query);
            while(rs.next())
            {
                qList.add(rs.getString(4));
                aList.add(rs.getString(5));
            }
            for(int i=0;i<qList.size();i++)
            {
                query="SELECT text from question where QID = '"+qList.elementAt(i)+"'";
                rs=statement.executeQuery(query);
                if(rs.next())
                {
                    list.add(rs.getString(1));
                }
            }
            for(int i=0;i<aList.size();i++)
            {
                query="SELECT text from answer where AID = '"+aList.elementAt(i)+"'";
                rs=statement.executeQuery(query);
                if(rs.next())
                {
                    list.add(rs.getString(1));
                }
                else
                {
                    list.add("skipped");
                }
            }
        }catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
