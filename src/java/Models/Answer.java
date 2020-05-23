/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author hp
 */
public class Answer {
    String AID;
    String text;
    boolean status;
    public Answer(){}
    public Answer(String AID,String text,boolean status)
    {
        this.AID=AID;
        this.text=text;
        this.status=status;
    }
    public String getAID()
    {
        return AID;
    }
    public String getText()
    {
        return text;
    }
    public boolean getStatus()
    {
        return status;
    }
}
