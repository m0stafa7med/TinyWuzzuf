/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Position;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class PositionDB {
   Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Vector<Position> get(String email) {
        Vector<Position> list=null;
        Position position=null;
        try {	
		list = new Vector<Position>();
		String sql = "SELECT title FROM position\n" +
                    "WHERE title NOT IN\n" +
                    "(\n" +
                    "    SELECT title from apply WHERE email ="+ email + "\n" +
                    ") "+
                        "AND title NOT IN\n"+
                    "(\n" +
                    "    SELECT title from application WHERE email ="+ email + "\n" +
                    ") ";    
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
                         list.add(new Position(resultSet.getString(1)));
                }
            }
        catch(Exception ex)
        {
            
        }
	return (list);
    }
    
    public Vector<Position> getAll() {
        Vector<Position> list=null;
        Position position=null;
        try {	
		list = new Vector<Position>();
		String sql = "SELECT title FROM position WHERE 1";    
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
                         list.add(new Position(resultSet.getString(1)));
                }
            }
        catch(Exception ex)
        {
            
        }
	return (list);
    }
    
//    public Candidate get(String email) {
//        Candidate candidate = null;
//		try {
//			candidate = new Candidate();
//			String sql = "select * from user where email = "+email;
//			connection = DatabaseConnection.openConnection();
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(sql);
//			if(resultSet.next()) {
//                                candidate.setPrevilige(resultSet.getString(1));
//				candidate.setUsername(resultSet.getString(2));
//                            }
//	}catch(Exception e) {
//		e.printStackTrace();
//	}
//	return candidate;
//    }

    
    public boolean save(Position position) {
        boolean flag = false;
	try {
                connection = DatabaseConnection.openConnection();
		String query = "insert into `position` VALUES("
                    + "'" + position.getTitle() + "'"
                    +")";
		statement.executeUpdate(query);
		flag = true;
	}
        catch(Exception ex) {
		ex.printStackTrace();
        }
		return flag;
    }

    
    public boolean delete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
