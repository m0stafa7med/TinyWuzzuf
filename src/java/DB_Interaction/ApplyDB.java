/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Candidate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class ApplyDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public boolean apply(String email,String title) {
        boolean flag=false;
        try {
		String sql = "Insert into apply values"
                    +"("
                    +"'"+email+"'"
                    +","
                    +"'"+title+"'"
                    +")";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		flag=true;
            }
        catch(Exception ex)
        {
            
        }
	return flag;
    }
}
