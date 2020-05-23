<%-- 
    Document   : Login
    Created on : Nov 29, 2019, 6:51:05 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="LoginController" method="GET">
            Email <input  type="text" name="username" required/>
            <input type="submit" name="decision" value="login"/>
            <br>
            don't have an email <a href="register.jsp"/>register now</a>
        </form>
    </body>
</html>
