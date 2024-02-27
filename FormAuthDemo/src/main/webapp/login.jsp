<%-- 
    Document   : login
    Created on : Feb 26, 2024, 4:36:26 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>LOGIN!</h1>
        <form action="j_security_check" method="post">
            USER NAME: <input type="text" id="username" name="j_username"><br>
            PASSWORD: <input type="password" id="password" name="j_password" minlength="8"><br>
            <input type="submit" value="LOGIN">
        </form>
    </body>
</html>
