<%-- 
    Document   : index
    Created on : Feb 26, 2024, 2:32:08 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="m" uri="/WEB-INF/tlds/mytaglib.tld" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Custom Tag Demo</title>
    </head>
    <body>
        <h1>JSTL Test page!</h1>
        <h1>
            <m:welcome></m:welcome>
        </h1>
            <m:repeat numOfIteration="30">-</m:repeat>
        <form action="" method="get" name="forEach">
            Select your programming language<br>
            C++<input type="checkbox" id="C++" name="langChoice" value="cpp">
            C#<input type="checkbox" id="C#" name="langChoice" value="csharp">
            PhP<input type="checkbox" id="PhP" name="langChoice" value="php">
            JavaScript<input type="checkbox" id="JavaScript" name="langChoice" value="javascript">
            Java<input type="checkbox" id="Java" name="langChoice" value="java">
            Dart<input type="checkbox" id="Dart" name="langChoice" value="dart">
            <br>
            <input type="submit" value="Submit">
        </form>
        <br>
            <h2>Your selected languages</h2>
            <c:forEach var="lang" items="${paramValues.langChoice}">
                <c:out value="${lang}"></c:out>
            </c:forEach>
    </body>
</html>
