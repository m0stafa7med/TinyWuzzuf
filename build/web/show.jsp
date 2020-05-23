<%-- 
    Document   : show
    Created on : Dec 14, 2019, 1:19:33 AM
    Author     : hp
--%>

<%@page import="Models.Message"%>
<%@page import="Models.Question"%>
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
<%  if(request.getAttribute("status").toString().equals("show_exam"))
    {
        Vector<Exam> examTypes=new Vector<Exam>();
        if(request.getAttribute("examTypes")!=null)
        {
            examTypes=(Vector<Exam>) request.getAttribute("examTypes");
        }
%><%
    for(int i=0;i<examTypes.size();i++)
        {
%>          <h1>
            <%= examTypes.elementAt(i).getType() %>
            </h1><%
        }
    }
%>
<% if(request.getAttribute("status").toString().equals("show_questions&answers"))
    {
        Vector<Exam> AllExam=new Vector<Exam>();
        if(request.getAttribute("examTypes")!=null)
        {
            AllExam=(Vector<Exam>) request.getAttribute("examTypes");
        }
%>     
<form action="HrController">
    <select name="type" required>
<%
         for(int i=0;i<AllExam.size();i++)
        {
%>
            <option value="<%= AllExam.elementAt(i).getType() %> ">
            <%= AllExam.elementAt(i).getType() %>
            </option>
<%      } %>
    </select>
    <input type="submit" name="decision" value="showQuestions&Answers">
</form>
<% } %>
<% if(request.getAttribute("status").toString().equals("showQuestions&Answers"))
    {
        Vector<Question> AllQuestion=new Vector<Question>();
        if(request.getAttribute("Q&A")!=null)
        {
            AllQuestion=(Vector<Question>) request.getAttribute("Q&A");
        }
%><%
         for(int i=0;i<AllQuestion.size();i++)
        { %>
            <h2><%= AllQuestion.elementAt(i).getText() %></h2>
            <ul>
                <% for(int j=0;j<AllQuestion.elementAt(i).getAnswer().size();j++)
                {
                %><li><%= AllQuestion.elementAt(i).getAnswer().elementAt(j).getText() %></li><%
                }
%>
            </ul>
    <% }
    }
%>
<%  if(request.getAttribute("status").equals("checkTest&Solution"))
        {
                Vector<String> candidates=new Vector<String>();
                if(request.getAttribute("candidates")!=null)
                {
                    candidates=(Vector<String>) request.getAttribute("candidates");
                }
                %>
                <form action="HrController">
                <select name="email" required>
<%
                for(int i=0;i<candidates.size();i++)
                {
%>
                <option value="<%= candidates.elementAt(i)%> ">
                <%= candidates.elementAt(i) %>
                </option>
<%              } %>
                </select>
                <input type="submit" name="decision" value="checkTest&SolutionStep1">
                </form>
            <%}
            else if(request.getAttribute("status").equals("checkTest&SolutionStep1"))
            {
                Vector<String> types=new Vector<String>();
                if(request.getAttribute("types")!=null)
                {
                    types=(Vector<String>) request.getAttribute("types");
                }
                %>
                <form action="HrController">
                <input type="text" name="email" value="<%=request.getParameter("email")%>" hidden/>
                <select name="type" required>
<%
                for(int i=0;i<types.size();i++)
                {
%>
                <option value="<%= types.elementAt(i)%> ">
                <%= types.elementAt(i) %>
                </option>
<%              } %>
                </select>
                <input type="submit" name="decision" value="checkTest&SolutionStep2">
                </form>
            <%
            }
else if(request.getAttribute("status").equals("checkTest&SolutionStep2"))
            {
                Vector<String> AllQuestion=new Vector<String>();
        if(request.getAttribute("Q&A")!=null)
        {
            AllQuestion=(Vector<String>) request.getAttribute("Q&A");
        }
%><%
         for(int i=0;i<AllQuestion.size()/2;i++)
        { %>
            <h2><%= AllQuestion.elementAt(i) %></h2>
            <ul>
                <li><%= AllQuestion.elementAt(i+AllQuestion.size()/2)%></li>
            </ul>
    <% }
            }
        else if(request.getAttribute("status").equals("Report_1"))
            {
          Vector<String> candidates=new Vector<String>();
                if(request.getAttribute("candidates")!=null)
                {
                    candidates=(Vector<String>) request.getAttribute("candidates");
                }
                %>
                <form action="HrController">
                <select name="email" required>
<%
                for(int i=0;i<candidates.size();i++)
                {
%>
                <option value="<%= candidates.elementAt(i)%> ">
                <%= candidates.elementAt(i) %>
                </option>
<%              } %>
                </select>
                <input type="submit" name="decision" value="Report_2">
                </form><%
            }
            else if(request.getAttribute("status").equals("Report_2"))
            {
                String totalScore="";
                if(request.getAttribute("totalScore")!=null)
                {
                    totalScore= request.getAttribute("totalScore").toString();
                }
                %>
                <h1><%=totalScore%></h1>
<%
            }
        %>
        <%  if(request.getAttribute("status").toString().equals("GetMessages"))
    {
        Vector<Message> message=new Vector<Message>();
        if(request.getAttribute("message")!=null)
        {
            message=(Vector<Message>) request.getAttribute("message");
        }
%><%
    for(int i=0;i<message.size();i++)
        {
%>          <h1>
            <%= message.elementAt(i).getBody() %>
            </h1><%
        }
    }
%>
    </body>
</html>
