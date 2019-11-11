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
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <h1>Register</h1>
        <h2>${message}</h2>
        <form action="Register" method="post">
            <label>Name:<input class="form-control" type="text" name="fname" required="yes"/></label><br>
            <label>Surname:<input class="form-control" type="text" name="lname" required="yes"/></label><br>
            <label>Username:<input class="form-control" type="text" id="userid" name="user" required="yes"/></label><br>
            <label>Password:<input class="form-control" type="password" name="pass" required="yes"/></label><br>
            <label>
              <input class="form-control" type="radio" name="role" value="student"> Student<br>
              <input class="form-control" type="radio" name="role" value="teacher"> Teacher<br>
            </label>
            <input class="btn btn-success" type="submit" value="Register"/>
        </form>
    </body>
</html>
