<%-- 
    Document   : Quiz
    Created on : Nov 12, 2019, 9:35:17 PM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Kamuu!"/>
    <body>
    <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <div class="jumbotron">
            <center><h1 class="display-4">Kamuu</h1></center>
        </div>
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
                            <h1>${quiz.key.getQuestionTitle()}</h1><br>
                        </div>
                    </div>
                <c:forEach items="${quiz.value}" var='choice' varStatus="vs2">
                    <div class="row">
                        <div class="col-lg-2">

                        </div>
                        <div class="col-lg-8 form-check">
                            <label><input class="form-check-input" type="checkbox"/><h2>${choice.getChoiceAns()}</h2><br></label>
                        </div>
                    </div>                
                </c:forEach>
            </c:forEach>
            </form>
    </body>
</html>
