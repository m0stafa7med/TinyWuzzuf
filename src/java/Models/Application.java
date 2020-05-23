/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hp
 */
public class Application {
    Position Job;
    Candidate candidate;
    Date deadline;
    String sequence;
    public Application(Position Job,Candidate candidate,String deadline,String sequence) throws ParseException
    {
        this.Job=Job;
        this.candidate=candidate;
        this.deadline= new SimpleDateFormat("yyyy-MM-dd").parse(deadline);
        this.sequence=sequence;
    }
    public Position getJob()
    {
        return Job;
    }
    public Candidate getCandidate()
    {
        return candidate;
    }
    public Date getDate()
    {
        return deadline;
    }
    public String getSequence()
    {
        return sequence;
    }
}
