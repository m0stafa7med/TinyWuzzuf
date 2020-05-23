<%-- 
    Document   : add
    Created on : Dec 14, 2019, 1:23:24 AM
    Author     : hp
--%>

<%@page import="Models.Question"%>
<%@page import="Models.Position"%>
<%@page import="Models.Exam"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<% if(request.getAttribute("status").toString().equals("add_exam"))
    {
        Vector<Position> AllPosition=new Vector<Position>();
        if(request.getAttribute("AllPosition")!=null)
        {
            AllPosition=(Vector<Position>) request.getAttribute("AllPosition");
        }
%>     
<form action="HrController">
    <input type="text" name="type" required/>
    <select name="title">
<%for(int i=0;i<AllPosition.size();i++)
        {
%>
            <option value="<%= AllPosition.elementAt(i).getTitle()%> ">
            <%= AllPosition.elementAt(i).getTitle() %>
            </option>
<%
        }
%>  </select> 
    <input type="submit" name="decision" value="addExam">
</form>
<% } %>
<% if(request.getAttribute("status").toString().equals("add_questions&answers"))
    {
%>     
<form action="HrController">
    <input type="submit" name="decision" value="add_Question">
    <input type="submit" name="decision" value="add_Answer">
</form>
<% } %>
<% if(request.getAttribute("status").toString().equals("add_Question"))
    {
        Vector<Exam> AllExam=new Vector<Exam>();
        if(request.getAttribute("examTypes")!=null)
        {
            AllExam=(Vector<Exam>) request.getAttribute("examTypes");
        }
%>
<form action="HrController">
<input type="text" name="text" required>
<select name="type">
<%      for(int i=0;i<AllExam.size();i++)
        { %>
            <option value="<%= AllExam.elementAt(i).getType() %>">
                <%= AllExam.elementAt(i).getType() %>
            </option>  
     <% } %> 
<input type="submit" name="decision" value="addQuestion">
</select>
</form>
<% } %>
<% if(request.getAttribute("status").toString().equals("add_Answer"))
    {
        Vector<Question> AllQuestion=new Vector<Question>();
        if(request.getAttribute("Q&A")!=null)
        {
            AllQuestion=(Vector<Question>) request.getAttribute("Q&A");
        }
%>
<form action="HrController">
<input type="text" name="text" required>
<select name="status">
    <option value="0">false</option>
    <option value="1">true</option>
</select>
<select name="QID">
<%      for(int i=0;i<AllQuestion.size();i++)
        { %>
            <option value="<%= AllQuestion.elementAt(i).getQID() %>">
                <%= AllQuestion.elementAt(i).getText() %>
            </option>  
     <% } %>
</select>
<input type="submit" name="decision" value="addAnswer">
</form>
<% } %>
    </body>
</html>
