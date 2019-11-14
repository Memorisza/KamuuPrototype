<%-- 
    Document   : Jumbotron
    Created on : Nov 15, 2019, 2:22:10 AM
    Author     : Win 10
--%>

<div class="jumbotron">
    <center><h1 class="display-4">
        <c:if test="${param.message == null}">Kamuu!</c:if>${param.message}
    </h1></center>
</div>
