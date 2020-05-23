<%-- 
    Document   : Hr_Home
    Created on : Nov 30, 2019, 9:02:43 PM
    Author     : hp
--%>

<%@page import="Models.Message"%>
<%@page import="java.util.Vector"%>
<%@page import="Models.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="jquery-3.4.1.min.js"></script>
        <script>
                $(document).ready(function(){
               $("#notification").click(function(){
                   $("#message").show();
               }) 
            });
            </script>
            <style>
div.header h1{
    display: table-header-group;
}
div.header a
{
    display: table-header-group;
    background: #ffff00;
    align-content: center;
    align-items: center;
    align-self: center;
    text-align: center;
    
}
div.footer {
    display: block; text-align: center;
}
/*div.header {
  text-align: right;
  display: inline;
}

div.div1 {
  text-align: left;
  display: inline;
}*/
</style>
    <script>
        //Getting value from "ajax.php".
function fill(Value) {
   //Assigning value to "search" div in "search.php" file.
   $('#search').val(Value);
   //Hiding "display" div in "search.php" file.
   $('#display').hide();
}
$(document).ready(function() {
   $("#search").keyup(function() {
       var search = $('#search').val();
       if (search == "") {
           $("#display").html("");
       }
       else {
           $.ajax({
               type: "POST",
               url: "Search",
               data: {
                   search : search
               },
               success: function(html) {
                   $("#display").html(html).show();
               }
           });
       }
   });
});
    </script>
    <style>
            .notification 
            {
            background-color: #555;
            color: white;
            text-decoration: none;
            padding: 15px 26px;
            position: relative;
            display: inline-block;
            border-radius: 2px;
            }

            .notification:hover {
              background: red;
            }

            .notification .badge {
              position: absolute;
              top: -10px;
              right: -10px;
              padding: 5px 10px;
              border-radius: 50%;
              background: red;
              color: white;
            }
            </style>
   <%response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);%>
    </head>
    <body>
        <div class='header'>
            <% if(session.getAttribute("username")!=null)
            { 
                %><h1> Hello <%=session.getAttribute("username")%> </h1><%
            }
            else
            {
                response.sendRedirect("Login.jsp"); 
            }%>
            <a href="Logout" >Logout</a> 
                <input type="text" id="search" placeholder="Search" />
                <div id="display"></div>
        </div>
            
    <div class="body">
       <form type="GET" action="HrController">
        <h1 class="approve">Approve or dis Approve</h1>
        <input class= "approve" type="submit" name="decision" value="approve"/>
        <h1>show Add update remove available exam types</h1>
        <input type="submit" name="decision" value="show_exam"/>
        <input type="submit" name="decision" value="add_exam"/>
        <input type="submit" name="decision" value="update_exam"/>
        <input type="submit" name="decision" value="delete_exam"/>
        <h1>show Add update remove questions and answers for available exam type</h1>
        <input type="submit" name="decision" value="show_questions&answers"/>
        <input type="submit" name="decision" value="add_questions&answers"/>
        <input type="submit" name="decision" value="update_questions&answers"/>
        <input type="submit" name="decision" value="delete_questions&answers"/>
        <h1>See full tests</h1>
        <input type="submit" name="decision" value="checkTest&Solution"/>
        <h1>Show Summarized Report</h1>
        <input type="submit" name="decision" value="Report"/>
        <input type="submit" name="decision" value="GetMessages"/>
        </form>
    </div>
        <div class='footer'>
        </div>
    </body>
</html>
