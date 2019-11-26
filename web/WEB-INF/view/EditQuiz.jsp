<%-- 
    Document   : EditQuiz
    Created on : Nov 24, 2019, 6:47:11 PM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Edit Quiz"/>
    <body>
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <jsp:include page="/WEB-INF/view/Jumbotron.jsp?message=${message}"/>
        <form action="EditQuiz" method="post">
        <c:forEach items='${quizes}' var='quiz' varStatus="vs">
                <nav aria-label="breadcrumb">
                    <div class="row">
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-8">
                            <input name="question_${vs.count}" value="${quiz.key.getQuestionTitle()}"/>
                            <h1>${vs.count}</h1><br>
                        </div>
                    </div>
                </nav>
                <c:forEach items="${quiz.value}" var='choice' varStatus="vs2">
                    <div class="row">
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-8">
                            <!--<h1>${choice.getChoiceId()}</h1>-->
                            <label><input class="form-check-input" name="question_${vs.count}_${vs2.count}" type="text" value="${choice.getChoiceAns()}"/></label><br>
                        </div>
                    </div>
                </c:forEach>
            </c:forEach>
        </form>
    </body>
</html>
