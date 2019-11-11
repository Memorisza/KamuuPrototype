<%-- 
    Document   : KamuuIndex
    Created on : Nov 9, 2019, 3:02:05 PM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/view/Header.jsp?title=Kamuu!"/>
    <body>
        <jsp:include page="/WEB-INF/view/Navbar.jsp"/>
        <h1>Kamuu</h1>
        <h2>Welcome ${user.getFullname()}</h2>
        <input type="search">&nbsp;Search<br>
        <table>
            <tr>
                <th>Subject A</th>
                <th></th>
            </tr>
            <tr>
                <td>Quiz 1</td>
                <td><input type="submit" value="Click to do the Quiz"></td>
            </tr>
            <tr>
                <td>Quiz 2</td>
                <td><input type="submit" value="Click to do the Quiz"></td>
            </tr>
            <tr>
                <td>Quiz 3</td>
                <td><input type="submit" value="Click to do the Quiz"></td>
            </tr>
        </table>
    </body>
</html>
