/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB_Interaction.ApplicationDB;
import DB_Interaction.CandidateDB;
import DB_Interaction.ExamDB;
import Models.Application;
import Models.Candidate;
import Models.Exam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {
    CandidateDB candidateDB=null;
    ApplicationDB applicationDB=null;
    ExamDB examDB=null;
    public Search()
    {
        candidateDB=new CandidateDB();
        applicationDB=new ApplicationDB();
        examDB=new ExamDB();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //email
        //deadline
        //exam type
        String returnElements="";
        if(candidateDB.get(request.getParameter("search"))!=null)
        {
            Candidate candidate=candidateDB.get(request.getParameter("search"));
            returnElements+="<h1>users</h1><br>";
            returnElements+=candidate.get_username()+" "+candidate.getPrevilige()+"<br>";
        }
        if(examDB.getExam(request.getParameter("search"))!=null)
        {
            Exam exam=examDB.getExam(request.getParameter("search"));
            returnElements+="<h1>Exams</h1><br>";
            returnElements+=exam.getType()+" "+exam.getRelatedTo()+"<br>";
        }
        
        if(applicationDB.get(request.getParameter("search"))!=null)
        {
            Application application=applicationDB.get(request.getParameter("search"));
            returnElements+="<h1>Applcations</h1><br>";
            returnElements+=application.getJob().getTitle()+" "
                    +application.getCandidate().get_username()+" "
                    +application.getDate()+" "
                    +application.getSequence()+" "+"<br>";
        }
        try (PrintWriter out = response.getWriter()) {
            out.println(returnElements);
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
