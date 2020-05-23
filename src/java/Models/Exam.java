/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Timestamp;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class Exam {
    String type;
    Vector<Question> question;
    String relatedTo;
    public Exam(){}
    public Exam(String type,String relatedTo)
    {
        this.relatedTo=relatedTo;
        this.type=type;
    }
    public void setQuestion(Vector<Question> question)
    {
        this.question=question;
    }
    public String getType()
    {
        return type;
    }
    public String getRelatedTo()
    {
        return relatedTo;
    }
    public Vector<Question> getQuestion()
    {
        return question;
    }
}
