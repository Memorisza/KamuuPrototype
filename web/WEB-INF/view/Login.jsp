<%-- 
    Document   : login
    Created on : Nov 7, 2019, 3:04:33 PM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Login"/>
    <body>
        <%--<jsp:include page="/WEB-INF/view/Navbar.jsp"/>--%>
        <h1>Login</h1>
        <h2>${message}</h2>
        <hr>
        <form action="Login" method="post">
            <label>Username:<input class="form-control" type="text" name="user"/></label><br>
            <label>Password:<input class="form-control" type="password" name="pass"/></label><br>
            <input class="btn btn-success" type="submit" value="Login"/>
        </form>
        <button type="button" class="btn btn-primary" onclick="location.href = 'Register';">Register</button>
    </body>
</html>
