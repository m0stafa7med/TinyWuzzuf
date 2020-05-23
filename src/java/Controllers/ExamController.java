/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB_Interaction.ApplicationDB;
import DB_Interaction.CandidateDB;
import DB_Interaction.MessageDB;
import DB_Interaction.SolutionDB;
import Models.Candidate;
import Models.Exam;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Map;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
@WebServlet(name = "ExamController", urlPatterns = {"/ExamController"})
public class ExamController extends HttpServlet {
    ApplicationDB applicationDB=null;
    SolutionDB solutionDB=null;
    MessageDB messageDB=null;
    CandidateDB candidateDB=null;
    String messageID="";
    public ExamController() {
            applicationDB = new ApplicationDB();
            solutionDB = new SolutionDB();
            messageDB = new MessageDB();
            candidateDB = new CandidateDB();
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
        HttpSession session=request.getSession(true);
        if(request.getParameter("decision").equals("startExam"))
        {
            
            request.setAttribute("messageID",request.getParameter("messageID"));
            request.setAttribute("exam",applicationDB.get(
                    session.getAttribute("username").toString(),
                    request.getParameter("job")
            ));
            RequestDispatcher dispatcher = request.getRequestDispatcher("exam.jsp");
            dispatcher.forward(request, response);
        }
        if(request.getParameter("decision").equals("submitExam"))
        {
//            
            messageDB.sendMessageToHr(
                    session.getAttribute("username").toString(),
                    request.getParameter("jobRelateTo"),
                    request.getParameter("jobType")
            );
            if(applicationDB.isThereNext(session.getAttribute("username").toString(),request.getParameter("jobRelateTo")))
            {  
                request.setAttribute("messageID",request.getParameter("messageID"));
                request.setAttribute("exam",applicationDB.get(
                    session.getAttribute("username").toString(),
                    request.getParameter("jobRelateTo")
                    )
                );
                RequestDispatcher dispatcher = request.getRequestDispatcher("exam.jsp");
            dispatcher.forward(request, response);
            }
            else
            {
                messageDB.deleteMessage(request.getParameter("messageID"));
                 Candidate candidate=candidateDB.get(session.getAttribute("username").toString());
                request.setAttribute("candidate",candidate);
                request.setAttribute("message", messageDB.get(session.getAttribute("username").toString()));
                RequestDispatcher dispatcher = request.getRequestDispatcher("PositionController");
                dispatcher.forward(request, response);
            }
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
