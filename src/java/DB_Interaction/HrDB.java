/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Candidate;
import Models.Exam;
import Models.Position;
import Models.Question;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class HrDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Vector<String> getAppliers() {
        Vector<String> list=null;
        Position position=null;
        try {	
		list = new Vector<String>();
		String sql = "SELECT DISTINCT email FROM `apply` WHERE 1";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
                         list.add(resultSet.getString(1));
                }
            }
        catch(Exception ex)
        {
            
        }
	return (list);
    }
    public Candidate getApplierDetails(String email)
    {
        return new CandidateDB().getDetailedCandidate(email);
    }
    public Vector<Position> getAllPosition()
    {
        return new PositionDB().getAll();
    }
    public Vector<Exam> getAllExam()
    {
        return new ExamDB().getAllExam();
    }
    public boolean addExam(String type,String title)
    {
        return new ExamDB().add(type,title);
    }
    public boolean updateExam(String type,String newType)
    {
        return new ExamDB().update(type,newType);
    }
    public boolean deleteExam(String type)
    {
        return new ExamDB().delete(type);
    }
    public boolean disApprove(String email,String title)
    {
        boolean flag=false;
        try {	
		String sql = "DELETE FROM `apply` WHERE email="
                        +" '"+email+"' "+"And title= '"+title+"'";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		flag=true;
            }
        catch(Exception ex)
        {
            
        }
	return (flag);
    }

    public void approve(String email, Date deadline,String exam,String jobTitle) {
        try {	
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		String sql="insert into `application` VALUES("
                    + "'" + jobTitle + "'" + ","
                    + "'" + email + "'" + ","
                    +"'" + deadline +"'" + ","+ "'" + exam  + "'"
                    +")";
                statement.executeUpdate(sql);
                sql="DELETE FROM `apply` WHERE email="+ " '"+email+"' "+"And title= '"+jobTitle+"'";
                statement.executeUpdate(sql);
        }
        catch(Exception ex)
        {
            
        }
    }

    public Vector<Question> getAllQuestionsAndAnswers(String title) {
        return new QuestionDB().getAllQuestion(title);
    }
    public Vector<Question> getAllQuestionsAndAnswers() {
        return new QuestionDB().getAllQuestion();
    }
    public boolean addQuestion(String text, String type) {
        return new QuestionDB().add(text, type);
    }
    public boolean updateQuestion(String QID, String text) {
        return new QuestionDB().update(QID, text);
    }
    public boolean deleteQuestion(String QID) {
        return new QuestionDB().delete(QID);
    }
    public boolean addAnswer(String text,int status,String QID) {
        return new AnswerDB().add(text,status,QID);
    }
    public boolean updateAnswer(String AID,String QID,String text,int status) {
        return new AnswerDB().update(AID,QID,text,status);
    }
    public boolean deleteAnswer(String AID) {
        return new AnswerDB().delete(AID);
    }
    public Vector<String> getAllCandidate() {
        return new SolutionDB().getAllCandidate();
    }
    public Vector<String> getAllTakenTest(String email) {
        return new SolutionDB().getAllTakenTest(email);
    }
    public Vector<String> getTestAndSolution(String type,String email) {
        return new SolutionDB().getTestAndSolution(type,email) ;
    }
    public String getAllScore(String email) {
        return new SolutionDB().getAllScore(email) ;
    }
}
