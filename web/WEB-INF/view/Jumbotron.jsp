<%-- 
    Document   : Jumbotron
    Created on : Nov 15, 2019, 2:22:10 AM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="jumbotron">
    <center><h1 class="display-4">
            <c:if test="${param.message.isEmpty()}">Kamuu!</c:if><c:if test="${message == null}">${message}</c:if>${param.message}
    </h1></center>
</div>
