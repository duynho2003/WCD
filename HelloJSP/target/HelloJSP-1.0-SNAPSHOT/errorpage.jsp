<%-- 
    Document   : errorpage
    Created on : Jan 26, 2024, 2:43:38 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>An error!</h1>
        <%= exception %>
    </body>
</html>
