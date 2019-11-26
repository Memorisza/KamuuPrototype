<%-- 
    Document   : StudentResult
    Created on : Nov 15, 2019, 2:08:07 AM
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
        <div class="row">
            <div class="col-lg-2">
                
            </div>
            <div class="col-lg-4">
                <h2>Question</h2>
            </div>
            <div class="col-lg-1">
                
            </div>
            <div class="col-lg-2">
                <h2>Your Answer</h2>
            </div>
            <div class="col-lg-2">
                <h2>Correct Answer</h2>
            </div>
        </div>
        <hr>
            
            <c:forEach items="${quiz}" var="quiz" varStatus="vs">
                <div class="row">
                    <div class="col-lg-2">
                    </div>
                    <div class="col-lg-4">
                        <h5>${vs.count}. ${quiz.key.getQuestionTitle()}</h5>
                    </div>
                    <div class="col-lg-1">
                        
                    </div>
                    <c:forEach items="${uans}" var="ans">
                        <c:if test="${ans.getQuestionId() == quiz.key.getQuestionId()}">
                            <div class="col-lg-2">
                                <h5>${ans.getChoiceAns()}</h5>
                            </div>
                        </c:if>
                    </c:forEach>
                        <div class="col-lg-2"><h5>
                        <c:forEach items="${quiz.value}" var="choice" varStatus="ccount">
                            <c:if test="${choice.isIsRightChoice()}">
                                ${choice.getChoiceAns()}
                            </c:if>
                        </c:forEach>
                        </h5>
                        </div>
                </div>
        </c:forEach>
            <hr>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <h1>Total Score: ${score}/${quiz.size()}</h1>
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
    </body>
</html>
