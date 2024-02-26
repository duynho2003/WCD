<%-- 
    Document   : welcome
    Created on : Feb 23, 2024, 4:51:53 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String name = request.getParameter("name");
            String guess = request.getParameter("guess");
            session.setAttribute("user", name);
            session.setAttribute("guess", guess);
        %>
        <h1>EL Welcome!</h1>
        <h1>Hello ${param.name}</h1>
        <h1>Hello ${sessionScope.user}</h1>
        <br>
        <a href=${sessionScope.user == "" ? "login.jsp" : "home.jsp"}>Login/Home</a>
    </body>
</html>
