<%-- 
    Document   : KamuuIndex
    Created on : Nov 9, 2019, 3:02:05 PM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Kamuu!"/>
    <body>
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <h1>Kamuu</h1>
        <h2>Welcome ${user.getFullname()}</h2>
    </body>
</html>
