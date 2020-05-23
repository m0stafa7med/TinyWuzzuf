<%-- 
    Document   : Editing
    Created on : Dec 13, 2019, 6:41:45 AM
    Author     : hp
--%>

<%@page import="Models.Question"%>
<%@page import="Models.Position"%>
<%@page import="Models.Exam"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>  
<%
    if(request.getAttribute("status").toString().equals("update_exam"))
    {
        Vector<Exam> AllExam=new Vector<Exam>();
        if(request.getAttribute("examTypes")!=null)
        {
            AllExam=(Vector<Exam>) request.getAttribute("examTypes");
        }
%>     
<form action="HrController">
    <input type="text" name="newTitle" required/>
    <select name="oldTitle">
<%
         for(int i=0;i<AllExam.size();i++)
        {
%>
            <option value="<%= AllExam.elementAt(i).getType() %> ">
            <%= AllExam.elementAt(i).getType() %>
            </option>
<%
        }
%>       </select>   
    <input type="submit" name="decision" value="updateExam">
</form>
<% } %>

<% if(request.getAttribute("status").toString().equals("update_questions&answers"))
    { %>
        <form action="HrController">
            <input type="submit" name="decision" value="update_Question">
            <input type="submit" name="decision" value="update_Answer">
                </form>
<% } %>
<% if(request.getAttribute("status").toString().equals("update_Question"))
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
<input type="text" name="text" required>
<input type="submit" name="decision" value="updateQuestion">
</form>
<% } %>
<% if(request.getAttribute("status").toString().equals("update_Answer"))
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
<select name="QID">
        <% for(int i=0;i<AllQuestion.size();i++)
        { %>
        <option value="<%= AllQuestion.elementAt(i).getQID() %>">
                <%= AllQuestion.elementAt(i).getText() %>
            </option><%
        } %> 
</select> 
<input type="text" name="text" required>
<select name="status">
    <option value="0">false</option>
    <option value="1">true</option>
</select>
<input type="submit" name="decision" value="updateAnswer">
</form>
<% } %>
</body>
</html>
