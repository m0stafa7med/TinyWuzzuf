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
public class Message {
    String body;
    String MessageID;
    String from;
    String to;
    boolean seen;
    String job;
    public Message()
    {
        
    }
    public Message(String body,String MessageID,String from,String to,boolean seen)
    {
        this.body=body;
        this.MessageID=MessageID;
        this.from=from;
        this.to=to;
        this.seen=seen;
    }
    public void setJob(String job)
    {
        this.job=job;
    }
    public String getBody()
    {
        return body;
    }
    public String getMessageID()
    {
        return MessageID;
    }
    public String getFrom()
    {
        return from;
    }
    public String getTo()
    {
        return to;
    }
    public String getJob()
    {
        return job;
    }
    public boolean getSeen()
    {
        return seen;
    }
}
