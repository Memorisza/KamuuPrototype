<%-- 
    Document   : KamuuIndex
    Created on : Nov 9, 2019, 3:02:05 PM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Kamuu!"/>
    <body>
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <jsp:include page="/WEB-INF/view/Jumbotron.jsp?message=${message}"/>
        <div class="row">
            <div class="col-lg-2">

            </div>
            <div class="col-lg-4">
                <h2>Completed Quizzes</h2>
            </div>
        </div>
        <br>
        <c:forEach items='${donequizes}' var='uquiz' varStatus="vs">
            <nav aria-label="breadcrumb">
                <div class="row">
                    <div class="col-lg-2">
                        
                    </div>
                    <div class="col-lg-1">
                        ${vs.count}
                    </div>
                    <div class="col-lg-4">
                        ${uquiz.getQuizName()}
                    </div>
                    <div class="col-lg-2">
                        
                    </div>
                    <div class="col-lg-2">
                        <button type="button" class="btn btn-link" onclick="location.href = 'StudentResult?quizid=${uquiz.getQuizId()}'">Check Results</button>
                    </div>
                </div>
            </nav>
        </c:forEach>
        <hr>
        <div class="row">
            <div class="col-lg-2">

            </div>
            <div class="col-lg-4">
                <h2>Available Quizzes</h2>
            </div>
        </div>
        <br>
        <c:forEach items='${quizes}' var='quiz' varStatus="vs">
            <nav aria-label="breadcrumb">
                <div class="row">
                    <div class="col-lg-2">
                        
                    </div>
                    <div class="col-lg-1">
                        ${vs.count}
                    </div>
                    <div class="col-lg-4">
                        ${quiz.getQuizName()}
                    </div>
                    <div class="col-lg-2">
                        
                    </div>
                    <div class="col-lg-2">
                        <button type="button" class="btn btn-link" onclick="location.href = 'Quiz?quizid=${quiz.getQuizId()}'">Do the Quiz!</button>
                    </div>
                </div>
            </nav>
        </c:forEach>
        </table>
    </body>
</html>
