<%-- 
    Document   : exam
    Created on : Dec 14, 2019, 5:56:31 AM
    Author     : hp
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="Models.Exam"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="jquery-3.4.1.min.js"></script>
        <script>
            $(document).ready(function(){
                $( "input" ).on( "click", function() {
                    var AID=$(this).val().toString();
                    var QID=$(this).attr('name').toString();
                    var testName=document.getElementById("testName").innerHTML.toString();
                    var testJob=document.getElementById("testJob").innerHTML.toString();
                    
                    $.ajax({
                       type: "POST",    
                       contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
                       url: "submitAnswer",
                       data: {
                           testName : JSON.stringify(testName),
                           AID : JSON.stringify(AID),
                           QID : JSON.stringify(QID),
                           testJob : JSON.stringify(testJob),
                        },
                        success: function(response) {
                        console.log("success");
                        },
                        error: function(response) {
                            console.log(response);
                        }
                    });
                });
            });
            </script>
            <script type="text/javascript">
            var SESSION_TIMEOUT_MILLIS = <%=request.getSession().getMaxInactiveInterval()%>;
            timer = setInterval(function () {
                var element = document.getElementById("log");
                element.innerHTML = "<h2>You have <b>"+SESSION_TIMEOUT_MILLIS+"</b> seconds to answer the questions</h2>";
                if(SESSION_TIMEOUT_MILLIS < 1){
                    clearInterval(timer);
                    document.getElementById('examForm').submit();
                }
                SESSION_TIMEOUT_MILLIS--;
            }, 1000)
            </script>
              <%response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);%>
    </head>
    <body>
        <h1> Hello <%= session.getAttribute("username") %> </h1>
      <div id="log"></div>
<%
        Exam exam=null;
        if (request.getAttribute("exam") != null) {
            exam = (Exam) request.getAttribute("exam");
        }
        for(int i=0;i<exam.getQuestion().size();i++)
        {
                Collections.shuffle(exam.getQuestion().elementAt(i).getAnswer());
        }
%>
<form id="examForm" action="ExamController">
        <h1 id="testName"><%= exam.getType() %></h1>
        <h1 id="testJob"><%= exam.getRelatedTo() %></h1>
        <input type="text" name="jobRelateTo" value="<%= exam.getRelatedTo() %>" hidden/>
        <input type="text" name="jobType" value="<%= exam.getType() %>" hidden/>
        <input type="text" name="messageID" value="<%= request.getParameter("messageID") %>" hidden/>
        <input type="hidden" name="decision" value="submitExam" />

<%= exam.getQuestion().size() %>
<%= exam.getQuestion().elementAt(0).getQID() %><%          
    for(int i=0;i<exam.getQuestion().size();i++)
    {
%>      <h2>
        
        <%=exam.getQuestion().elementAt(i).getText() %>
        </h2>
<%
        for(int j=0;j<exam.getQuestion().elementAt(i).getAnswer().size();j++)
        {
%>          <input type="radio" name="<%= exam.getQuestion().elementAt(i).getQID() %> " 
       value="<%= exam.getQuestion().elementAt(i).getAnswer().elementAt(j).getAID()%>" required>
            <%= exam.getQuestion().elementAt(i).getAnswer().elementAt(j).getText()%><%
        }
        %>
            <input type="radio" name="<%= exam.getQuestion().elementAt(i).getQID()%> " 
            value="">Skip
            <%
    }
            %><br><br>
    <input name="decision" type="submit"  value="submitExam" />    
</form>
    </body>
</html>
