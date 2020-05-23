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
import java.util.Date;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class QuestionDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Vector<Question> getAllQuestion(String title)
    {
        Vector<Question> list=null;
        Question question=null;	
        Vector<Answer> answerList=null;
        Answer answer=null;
        try{
            list = new Vector<Question>();
            String sql = "SELECT * FROM `question` WHERE type = '"+title+"'";
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                question=new Question(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                list.add(question);
            }
            for(int i=0;i<list.size();i++)
            {
                answerList=new Vector<Answer>();
                sql="SELECT * from answer where QID = '"+list.elementAt(i).getQID()+"'";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next())
                {
                    answer=new Answer(resultSet.getString(1),resultSet.getString(3),resultSet.getBoolean(4));
                    answerList.add(answer);
                }
                list.elementAt(i).setAnswer(answerList);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (list);
    }
    public Vector<Question> getAllQuestion()
    {
        Vector<Question> list=null;
        Question question=null;	
        Vector<Answer> answerList=null;
        Answer answer=null;
        try{
            list = new Vector<Question>();
            String sql = "SELECT * FROM `question` WHERE 1";
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                question=new Question(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                list.add(question);
            }
            for(int i=0;i<list.size();i++)
            {
                answerList=new Vector<Answer>();
                sql="SELECT * from answer where QID = '"+list.elementAt(i).getQID()+"'";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next())
                {
                    answer=new Answer(resultSet.getString(1),resultSet.getString(3),resultSet.getBoolean(4));
                    answerList.add(answer);
                }
                list.elementAt(i).setAnswer(answerList);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (list);
    }
    public boolean add(String text,String type)
    {
        boolean flag=false;
        try{
            String sql="insert into `question` VALUES("
                    + "'" + UUID.randomUUID().toString() + "'" + ","
                    +"'" + type +"'" + ","
                    +"'" + text +"'"
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
    public boolean update(String QID,String newText)
    {
        boolean flag=false;
        try{
            String sql="update `question` set `text` = "
                    + "'" + newText + "'" + " where `QID` = "
                    +"'" + QID +"'"
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
    public boolean delete(String QID)
    {
        boolean flag=false;
        try{
            String sql="delete from `question` where `QID` = "
                    + "'" + QID + "'" 
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
    public Vector<Question> getRandQuestion(String type,String email,String title)
    {
        Vector<Question> list=null;
        Question question=null;	
        Vector<Answer> answerList=null;
        Answer answer=null;
        try{
            list = new Vector<Question>();
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM `candidate_solution` WHERE type = "+"'"+type+"' AND "+
                    "email = '"+email+"' AND title= '"+title+"' AND AID = 'null'";
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()==false)
            {
                sql = "SELECT * FROM `question` WHERE type = "+"'"+type+"'"+
                    " ORDER BY RAND() LIMIT 5";
                resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, resultSet.getString(1));
                question=new Question(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                list.add(question);
            }
            for(int i=0;i<list.size();i++)
            {
                answerList=new Vector<Answer>();
                sql="SELECT * from answer where QID = '"+list.elementAt(i).getQID()+"' AND status = 1 ORDER BY RAND() LIMIT 1";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next())
                {
                    answer=new Answer(resultSet.getString(1),resultSet.getString(3),resultSet.getBoolean(4));
                    answerList.add(answer);
                }
                sql="SELECT * from answer where QID = '"+list.elementAt(i).getQID()+"' AND status = 0 ORDER BY RAND() LIMIT 3";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next())
                {
                    answer=new Answer(resultSet.getString(1),resultSet.getString(3),resultSet.getBoolean(4));
                    answerList.add(answer);
                }
                list.elementAt(i).setAnswer(answerList);
            }
            new SolutionDB().initialSave(type,list, email,title);
            return (list);
        }
        else
        {
            resultSet.beforeFirst();
            while(resultSet.next())
            {
                question=new Question(resultSet.getString(4));
                list.add(question);
            }
            for(int i=0;i<list.size();i++)
            {
                sql = "SELECT * FROM `question` WHERE QID = "+"'"+list.elementAt(i).getQID()+"'";
                resultSet= statement.executeQuery(sql);
                while(resultSet.next()){
                    list.elementAt(i).setType(resultSet.getString(2));
                    list.elementAt(i).setText(resultSet.getString(3));
                }
                answerList=new Vector<Answer>();
                sql="SELECT * from answer where QID = '"+list.elementAt(i).getQID()+"' AND status = 1 ORDER BY RAND() LIMIT 1";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next())
                {
                    answer=new Answer(resultSet.getString(1),resultSet.getString(3),resultSet.getBoolean(4));
                    answerList.add(answer);
                }
                sql="SELECT * from answer where QID = '"+list.elementAt(i).getQID()+"' AND status = 0 ORDER BY RAND() LIMIT 3";
                resultSet=statement.executeQuery(sql);
                while(resultSet.next())
                {
                    answer=new Answer(resultSet.getString(1),resultSet.getString(3),resultSet.getBoolean(4));
                    answerList.add(answer);
                }
                list.elementAt(i).setAnswer(answerList);
            }
        }
            }
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
           return list; 
    }
}
