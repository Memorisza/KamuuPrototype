<%-- 
    Document   : StudentResult
    Created on : Nov 15, 2019, 2:08:07 AM
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
                <h2>Quiz Result</h2>
            </div>
        </div>
        <hr>
        <table>
            <c:forEach items="${quiz}" var="quiz" varStatus="vs">
                <div class="row">
                    <div class="col-lg-2">
                    </div>
                    <div class="col-lg-4">
                        <h2>${vs.count}. ${quiz.key.getQuestionTitle()}</h2>
                    </div>
                    <div class="col-lg-1">
                        
                    </div>
                    <c:forEach items="${uans}" var="ans">
                        <c:if test="${ans.getQuestionId() == quiz.key.getQuestionId()}">
                            <div class="col-lg-2">
                                <h2>${ans.getChoiceAns()}</h2>
                            </div>
                        </c:if>
                    </c:forEach>
                        <c:forEach items="${quiz.value}" var="choice">
                            <c:if test="${choice.isIsRightChoice()}">
                                <div class="col-lg-2">
                                    <h2>${choice.getChoiceAns()}</h2>
                                </div>
                            </c:if>
                        </c:forEach>
                </div>
        </c:forEach>
            <hr>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-4">
                    <h1>Total Score: ${score}</h1>
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
        </table>
    </body>
</html>
