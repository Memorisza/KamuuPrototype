<%-- 
    Document   : AddQuestion
    Created on : Nov 24, 2019, 9:50:35 PM
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
        <form action="AddQuestion" method="post">
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <input hidden="true" name="quizid" value="${quizid}"/>
                    <c:if test="${ques == null}">
                        <label>Question title:<input type="text" name="quName"/></label>
                    </c:if>
                    <c:if test="${ques != null}">
                        <label>Question title:<input type="text" name="quName" value="${ques.getQuestionTitle()}"/></label>
                    </c:if>
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
