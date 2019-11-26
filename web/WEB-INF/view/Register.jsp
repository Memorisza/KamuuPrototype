<%-- 
    Document   : Register
    Created on : Nov 9, 2019, 6:34:55 PM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Register"/>
    <body>
        <h1>Register</h1>
        <h2>${message}</h2>
        <script>
            function validate(){
                if(document.getElementById("pass").value !== document.getElementById("conpass").value){
                    alert('Password and Confirm password field not match');
                    document.getElementById("pass").value = "";
                    document.getElementById("conpass").value = "";
                    document.getElementById("pass").focus();
                    return false;
                }
            }
        </script>
        <form action="Register" method="post" onsubmit="return validate()">
            <label>Name:<input class="form-control" type="text" name="fname" required="yes"/></label><br>
            <label>Surname:<input class="form-control" type="text" name="lname" required="yes"/></label><br>
            <label>Username:<input class="form-control" type="text" id="userid" name="user" required="yes"/></label><br>
            <label>Password:<input class="form-control" type="password" id="pass" name="pass" required="yes"/></label><br>
            <label>Confirm Password:<input class="form-control" type="password" id="conpass" required="yes"/></label><br>
            <label>
              <input class="form-control" type="radio" name="role" value="Student"> Student<br>
              <input class="form-control" type="radio" name="role" value="Teacher"> Teacher<br>
            </label>
            <br>
            <input class="btn btn-success" type="submit" value="Register"/>
        </form>
    </body>
</html>
