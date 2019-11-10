<%-- 
    Document   : Register
    Created on : Nov 9, 2019, 6:34:55 PM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Register"/>
    <body>
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <h1>Register</h1>
        <form action="Register" method="post">
            <label>Name:<input class="form-control" type="text" name="fname"/></label><br>
            <label>Surname:<input class="form-control" type="text" name="lname"/></label><br>
            <label>Username:<input class="form-control" type="text" name="user"/></label> <button onclick="">Check Availability</button><br>
            <label>Password:<input class="form-control" type="password" name="pass"/></label><br>
            <input class="btn btn-success" type="submit" value="Register"/>
        </form>
    </body>
</html>
