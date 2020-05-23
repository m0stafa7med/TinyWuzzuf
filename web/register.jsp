<%-- 
    Document   : register
    Created on : Nov 30, 2019, 9:07:32 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
        function checkvalidation()
        {
            var xmlhttp;
            var username=document.getElementById("username").value;
            var urls="checkExistance?username="+username;
            if (window.XMLHttpRequest)
              {
              xmlhttp=new XMLHttpRequest();
              }
            else
              {
              xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
              }
            xmlhttp.onreadystatechange=function()
              {
              if (xmlhttp.readyState==4)
                {
                    document.getElementById("isE").innerHTML=xmlhttp.responseText;
                }
              }
            xmlhttp.open("GET",urls,true);
            xmlhttp.send();
        }
        function valid()
        {
            var telephone=document.getElementById("telephone").value;
            message=document.getElementById("isE").value;
            var cv=document.getElementById("cv");
            if(cv.value==""||cv.value.toString().endsWith(".pdf")!=true)
            {
                document.getElementById("isE2").innerHTML="Please Upload appropriate file";
                return false;
//                event.preventDefault();
            }
            else
            {
                document.getElementById("isE2").innerHTML="";
            }
            document.getElementById("x").innerHTML=document.getElementById("isE").innerHTML;
            var x=document.getElementById("isE").innerText;
            if(x.toString()!="Available")
            {
                return false;
            }
        }
        </script>
    </head>
    <body>
        <form action="RegisterController" method="GET" onsubmit="return valid()">
            Please Enter Your info <br>
            Enter your username:<input id="username" type="text" name="username" onkeyup="checkvalidation()" required/>
            <span id="isE"></span><br><br>
            CV <input id="cv" type="file" name="cv"/>
            <span id="isE2" style="color: red"></span><br><br>
            telephone Number <input id="telephone" type="text" name="telephone" maxlength="14"/>
            <input type="submit" name="decision" value="register"/>
            <span id="x" style="color: red"></span><br><br>
        </form>
    </body>
</html>
