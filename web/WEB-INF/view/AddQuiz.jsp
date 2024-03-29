<%-- 
    Document   : AddQuiz
    Created on : Nov 24, 2019, 6:21:12 PM
    Author     : Win 10
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Add Quiz"/>
    <body>
        <script>
            function actQuiz(){
                if(document.getElementById("actConfirm").checked === true){
                    return window.confirm('Are you sure to delete the quiz?');
                }
                return true;
            }
        </script>
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <jsp:include page="/WEB-INF/view/Jumbotron.jsp?message=${message}"/>
        <c:if test="${rAdd == true}">
              <form action="AddQuiz" method="post">
        </c:if>
        <c:if test="${rAdd == false}">
              <form action="EditQuiz" method="post">
        </c:if>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-10">
                    <input hidden="true" name="quizid" value="${newquiz.getQuizId()}"/>
                    <label>Quiz Name:<input type="text" class="form-control" name="quizN" value="${newquiz.getQuizName()}"/></label><br>                
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8">
                    <c:if test="${rAdd == false}">
                        <label>Set Active:<input type="checkbox" name="quizAct" <c:if test="${newquiz.isIsActive()}">checked</c:if>/></label><br>
                    </c:if>                    
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8">
                        <input class="btn btn-primary" type="submit" value="Save"/>   
                </div>
            </div>
        </form>
                <hr>
        <c:if test="${rAdd == false}">
            <c:forEach items='${quizes}' var='quiz' varStatus="vs">
                <nav aria-label="breadcrumb">
                    <div class="row">
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-4">
                            ${vs.count}. <input value="${quiz.key.getQuestionTitle()}" readonly=true/> 
                            <button type="button" class="btn btn-warning" onclick="location.href = 'EditQuestion?quesid=${quiz.key.getQuestionId()}';">Edit Question</button>
                            <button type="button" class="btn btn-danger" onclick="location.href = 'DeleteQuestion?quesid=${quiz.key.getQuestionId()}';">Delete Question</button>
                        </div>
                        <div class="col-lg-4">
                                
                        </div>
                    </div>
                </nav>
                        <br>
                <c:forEach items="${quiz.value}" var='choice' varStatus="vs2">
                    <div class="row">
                        <div class="col-lg-2">
                        </div>
                        <div class="col-lg-4">
                            <input readonly="true" type="text" value="${choice.getChoiceAns()}"/>
                            <button type="button" class="btn btn-link" onclick="location.href = 'EditChoice?choiceid=${choice.getChoiceId()}';">Edit Choice</button>
                            <button type="button" class="btn btn-delete" onclick="location.href = 'DeleteChoice?choiceid=${choice.getChoiceId()}';">Delete Choice</button>
                        </div>
                        <div class="col-lg-4">
                            
                        </div>
                    </div>
                </c:forEach>
                    <div class="row">
                    <div class="col-lg-2">
                    </div>
                    <div class="col-lg-4">
                        <h1><button type="button" class="btn btn-success" onclick="location.href = 'AddChoice?quesid=${quiz.key.getQuestionId()}'">Add Choice</button></h1>
                    </div>
                </div>
                    <div class="row">
                        <div class="col-lg-2">
                            
                        </div>
                        <div class="col-lg-8">
                            <hr>
                        </div>
                        <div class="col-lg-2">
                            
                        </div>
                    </div>
            </c:forEach>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8">
                    <h1><button type="button" class="btn btn-info" onclick="location.href = 'AddQuestion?quizid=${newquiz.getQuizId()}';">Add New Question</button></h1>
                    <br>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8">
                    <button type="button" id="actConfirm" class="btn btn-primary" onclick="if(actQuiz()) location.href = 'Login';">Back</button>
                </div>
            </div>
        </c:if>
    </body>
</html>
