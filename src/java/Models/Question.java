/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Vector;

/**
 *
 * @author hp
 */
public class Question {
    String QID;
    String type;
    String text;
    Vector<Answer> answer;
    public Question(){}
    public Question(String QID,String type,String text)
    {
        this.QID=QID;
        this.type=type;
        this.text=text;
        this.answer=answer;
    }
    public void setAnswer(Vector<Answer> answer)
    {
        this.answer=answer;
    }
    public String getQID()
    {
        return QID;
    }
    public String getType()
    {
        return type;
    }
    public String getText()
    {
        return text;
    }
    public Vector<Answer> getAnswer()
    {
        return answer;
    }
    public Question(String QID)
    {
        this.QID=QID;
    }
    public void setText(String text)
    {
        this.text=text;
    }
    public void setType(String type) {
        this.type=type;
    }
}
