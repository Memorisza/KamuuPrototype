<%-- 
    Document   : QuizResult
    Created on : Nov 24, 2019, 1:38:23 PM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Quiz Result"/>
    <body>
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <jsp:include page="/WEB-INF/view/Jumbotron.jsp?message= Quiz Result"/>
    <c:if test="${flag == true}">
        <div class="row">
            <div class="col-lg-2">
                
            </div>
            <div class="col-lg-4">
                 <h2>No Result yet.</h2>
            </div>
        </div>
        <hr>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <button type="button" class="btn btn-primary" onclick="location.href = 'Login';">Back</button>
                </div>
            </div>
    </c:if>
    <c:if test="${scores == false}">
        <div class="row">
            <div class="col-lg-2">
                
            </div>
            <div class="col-lg-1">
                <h2>ID</h2>
            </div>
            <div class="col-lg-5">
                <h2>Student Name</h2>
            </div>
            <div class="col-lg-2">
                <h2>Score</h2>
            </div>
            <div class="col-lg-2">
                
            </div>
        </div>
        <hr>
    <c:forEach items="${scores}" var="score" varStatus="vs">
        <div class="row">
            <div class="col-lg-2">
                
            </div>
            <div class="col-lg-1">
                <h5>${score.key.getId()}</h5>
            </div>
            <div class="col-lg-5">
                <h5>${score.key.getName()} ${score.key.getSurname()}</h5>
            </div>
            <div class="col-lg-2">
                <h5>${score.value}/${fullscore}</h5>
            </div>
            <div class="col-lg-2">
                
            </div>
        </div>
    </c:forEach>
    <hr>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <h4>Minimum Score: ${min}</h4> 
                    <h4>Maximum Score: ${max}</h4> 
                    <h4>Average Score: ${mean}</h4> 
                    <h4>Standard Deviation: ${sd}</h4>
                </div>
                
            </div>
                <br>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <button type="button" class="btn btn-primary" onclick="location.href = 'Login';">Back</button>
                </div>
            </div>
    </c:if>
    </body>
</html>
