<%-- 
    Document   : data
    Created on : Jan 26, 2024, 2:46:51 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" errorPage="errorpage.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Date Page</title>
    </head>
    <body>
        <%
            Date curDate = new Date();
            out.println(curDate);
            
            Date newDate = null;
        %>
        <br>
        <%-- Test null exception --%>
        Current Date is: <%= newDate.toString() %>
        <h1>Hello World!</h1>
    </body>
</html>
