<%-- 
    Document   : delete
    Created on : Dec 14, 2019, 2:34:43 AM
    Author     : hp
--%>

<%@page import="Models.Exam"%>
<%@page import="Models.Question"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<% if(request.getAttribute("status").toString().equals("delete_exam"))
    {
        Vector<Exam> AllExam=new Vector<Exam>();
        if(request.getAttribute("examTypes")!=null)
        {
            AllExam=(Vector<Exam>) request.getAttribute("examTypes");
        }
%>     
<form action="HrController">
    <select name="title">
<% for(int i=0;i<AllExam.size();i++)
        {
%>
            <option value="<%= AllExam.elementAt(i).getType() %> ">
            <%= AllExam.elementAt(i).getType() %>
            </option>
            <%
        }
%>       </select> 
    <input type="submit" name="decision" value="deleteExam">
</form>
<% } %>
<% if(request.getAttribute("status").toString().equals("delete_questions&answers"))
    { %>
        <form action="HrController">
            <input type="submit" name="decision" value="delete_Question">
            <input type="submit" name="decision" value="delete_Answer">
        </form>
<% } %>
<% if(request.getAttribute("status").toString().equals("delete_Question"))
    {
        Vector<Question> AllQuestion=new Vector<Question>();
        if(request.getAttribute("Q&A")!=null)
        {
            AllQuestion=(Vector<Question>) request.getAttribute("Q&A");
        }
%>
<form action="HrController">
<select name="QID">
        <% for(int i=0;i<AllQuestion.size();i++)
        { %>
            <option value="<%= AllQuestion.elementAt(i).getQID() %>">
                <%= AllQuestion.elementAt(i).getText() %>
            </option>
       <% } %> 
      </select> 
<input type="submit" name="decision" value="deleteQuestion">
</form>
<% } %>

<% if(request.getAttribute("status").toString().equals("delete_Answer"))
    {
        Vector<Question> AllQuestion=new Vector<Question>();
        if(request.getAttribute("Q&A")!=null)
        {
            AllQuestion=(Vector<Question>) request.getAttribute("Q&A");
        }
%>
<form action="HrController">
<select name="AID">
        <% for(int i=0;i<AllQuestion.size();i++)
        { %>
        <% for(int j=0;j<AllQuestion.elementAt(i).getAnswer().size();j++)
        {
        %><option value="<%= AllQuestion.elementAt(i).getAnswer().elementAt(j).getAID() %>">
                <%= AllQuestion.elementAt(i).getAnswer().elementAt(j).getText() %>
            </option><%
        }
         } %> 
      </select> 
<input type="submit" name="decision" value="deleteAnswer">
</form>
<% } %>
    </body>
</html>
