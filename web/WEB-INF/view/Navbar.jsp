<%-- 
    Document   : Navbar
    Created on : Nov 7, 2019, 3:19:32 PM
    Author     : Win 10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand col-lg-8" href="Login">Kamuu</a>
    <div class="col-lg-2">Hello ${user.getFullname()}!</div>
    <div class="col-lg-1"><a href="Logout">Logout</a></div>
</nav>