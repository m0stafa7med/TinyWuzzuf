/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Candidate;
import Models.Exam;
import Models.Message;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.jfr.Timestamp;

/**
 *
 * @author hp
 */
public class MessageDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Vector<Message> get(String email) {
        createMessage();
        Vector<Message> list=null;
        Message message=null;
        try {	
		list = new Vector<Message>();
                String sql="";
                if(new CandidateDB().get(email).getPrevilige().equals("user"))
                {
                     sql = "SELECT * FROM `Message` where `to` ='"+email+"'";
                }
                else
                {
                     sql = "SELECT * FROM `Message` where `to` = 'HR'";
                }
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
                        message = new Message(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5));
                        String word[]=resultSet.getString(1).split(" ");
                        String job="";
                        for(int i=4;i<word.length;i++)
                        {
                            if(word[i].equals("so"))
                            {
                                break;
                            }
                            job+=word[i];
                        }
                        message.setJob(job);
			list.add(message);
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (list);
    }
    public boolean createMessage()
    {
        boolean flag=false;
        try{
        
        connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
                String query="Delete FROM `message` where `from` = 'system' AND seen = '0'";
		statement.executeUpdate(query);
                query="Select * from application where 1";
		resultSet = statement.executeQuery(query);
                Vector<String> queries=new Vector<String>();
		while(resultSet.next()) {
                    String title=resultSet.getString(1);
                    String applier=resultSet.getString(2);
                    Date deadline=resultSet.getDate(3);
                    String exams=resultSet.getString(4);
                    Date date = new Date();
                    if(deadline.compareTo(date)==1)
                    {
                        String sql="insert into `message` VALUES("
                        + "'" + "You have applied for "+ title + " so you have to take those exams "+ exams  + "'" + ","
                        + "'" + UUID.randomUUID().toString() + "'" + ","
                        +"'" + "System" +"'" + ","
                        + "'" + applier  + "'" + ","
                        + "'" + 0 + "'"
                        +")";
                        queries.add(sql);
                    }
                }
                for(int i=0;i<queries.size();i++)
                {
                    statement.executeUpdate(queries.elementAt(i));
                }
                flag=true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public boolean sendMessageToHr(String email,String Relatedexam,String type)
    {
        boolean flag=false;
        Candidate candidate=new CandidateDB().get(email);
        String score=new SolutionDB().getScore(email,Relatedexam,type);
        String msg="candidate with ID "+candidate.get_username()
                +" and telephone number = "+candidate.getTelephone()
                +" who applied for job called "+Relatedexam
                +" has finished exam named "+type
                +" and he got score "+score;
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="insert into `message` VALUES("
                        + "'" + msg + "'" + ","
                        + "'" + UUID.randomUUID().toString() + "'" + ","
                        + "'" + "Inside" + "'" + ","
                        + "'" + "HR"  + "'" + ","
                        + "'" + 0 + "'"
                        +")";
            statement.executeUpdate(query);
            flag=true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public boolean sendDisApproveMessage(String email,String title)
    {
        boolean flag=false;
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String msg="Sorry to tell you that, but your cv is not enough to apply for "+title+" try working more hard and goodluck";
            String query="insert into `message` VALUES("
                        + "'" +msg+ "'" + ","
                        + "'" + UUID.randomUUID().toString() + "'" + ","
                        +"'" + "System" +"'" + ","
                        + "'" + email  + "'" + ","
                        + "'" + 1 + "'"
                        +")";
            statement.executeUpdate(query);
            flag=true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public boolean deleteMessage(String messageID)
    {
        boolean flag=false;
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="Delete from message where MessageID = '"+messageID+"'";
            statement.executeUpdate(query);
            flag=true;
        } 
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
}