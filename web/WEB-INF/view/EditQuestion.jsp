<%-- 
    Document   : EditQuestion
    Created on : Nov 24, 2019, 11:33:11 PM
    Author     : Win 10
--%>
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
        <form action="EditQuestion" method="post">
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <input hidden="true" name="quesid" value="${ques.getQuestionId()}"/>
                        <label>Question title:<input type="text" name="quName" value="${ques.getQuestionTitle()}"/></label>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <input type="submit" class="btn btn-warning" value="Edit"/>
                </div>
                <div class="col-lg-4">
                </div>
                <div class="col-lg-2">
                    <button type="button" class="btn btn-primary" onclick="location.href = 'AddQuiz'">Back</button>
                </div>    
            </div>
        </form>
    </body>
</html>

