<%-- 
    Document   : Quiz
    Created on : Nov 12, 2019, 9:35:17 PM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Quiz"/>
    <body>
    <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <jsp:include page="/WEB-INF/view/Jumbotron.jsp?message=${message}"/>
        <div class="row">
            <div class="col-lg-2">
            </div>
            <div class="col-lg-4">
                <h2>Welcome ${user.getFullname()}</h2>
            </div>
        </div>
            <hr>
            <form action="Quiz" method="post">
            <c:forEach items='${quizes}' var='quiz' varStatus="vs">
                <nav aria-label="breadcrumb">
                    <div class="row">
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-8">
                            <h1>${vs.count}. ${quiz.key.getQuestionTitle()}</h1><br>
                        </div>
                    </div>
                </nav>
                <c:forEach items="${quiz.value}" var='choice' varStatus="vs2">
                    <div class="row">
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-8">
                            <!--<h1>${choice.getChoiceId()}</h1>-->
                            <label><input class="form-check-input" name="question_${vs.count}" type="radio" value="${choice.getChoiceId()}"/><h4>${choice.getChoiceAns()}</h4></label><br>
                        </div>
                    </div>
                </c:forEach>
            </c:forEach>
                <div class="row">
                    <div class="col-lg-2">
                    </div>
                    <div class="col-lg-8">
                        <input class="btn btn-primary" type="submit" value="Submit"/>
                    </div>
                </div>
            </form>
    </body>
</html>
