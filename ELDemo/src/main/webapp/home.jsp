<%-- 
    Document   : home
    Created on : Feb 23, 2024, 4:45:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="/WEB-INF/tdls/number.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home Page!</h1>
        <h2>Hello ${sessionScope.user}</h2>
        <h2>Average of 5 and 8 is ${fn:getAverage([5.0, 8.0])}</h2>
        ${2 + 2}
    </body>
</html>
