<%-- 
    Document   : calc
    Created on : Jan 26, 2024, 2:56:59 PM
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
        <%!
            // tinh giai thua
            int fact(int n) {
                if (n == 0)
                return 1;
                return n * fact(n - 1);
            }
        %>
        Result is : <%= + fact(5) %>
        <h1>Hello World!</h1>
    </body>
</html>
