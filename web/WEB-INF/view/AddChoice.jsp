<%-- 
    Document   : AddChoice
    Created on : Nov 24, 2019, 11:41:59 PM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Add Question"/>
    <body>
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <jsp:include page="/WEB-INF/view/Jumbotron.jsp?message=Add Question"/>
        <form action="AddChoice" method="post">
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <input hidden="true" name="quesid" value="${choice.getQuestionId()}"/>
                    <label>Choice Answer:<input type="text" name="cAns"/></label>
                </div>
            </div>
                    <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <label>Correct Choice?:<input type="checkbox" name="isCorrect"/></label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <input type="submit" class="btn btn-primary" value="Add"/>
                </div>
                <div class="col-lg-4">
                </div>
                <div class="col-lg-2">
                    <button type="button" class="btn btn-primary" onclick="location.href = 'EditQuiz?quizid=${quizid}'">Back</button>
                </div>    
            </div>
        </form>
    </body>
</html>
