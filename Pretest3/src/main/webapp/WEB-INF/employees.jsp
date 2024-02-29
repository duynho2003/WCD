<%-- 
    Document   : employees
    Created on : Feb 29, 2024, 4:36:53 PM
    Author     : Tommy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Management Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>Employee MANAGEMENT!</h1>
            <h2><a href="employees/create">New Employee</a></h2>

            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Date of Birth</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="employee" items="${listEmployees}">
                    <tr>
                        <td><c:out value="${employee.id}"></c:out></td>
                        <td><c:out value="${employee.firstname}"></c:out></td>
                        <td><c:out value="${employee.lastname}"></c:out></td>
                        <td><c:out value="${employee.dob}"></c:out></td>
                        <td>
                            <img src="${pageContext.request.contextPath}/${employee.image}" width="200" height="100"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/employees/edit?id=<c:out value='${employee.id}'/>">Edit</a> 
                            <a href="${pageContext.request.contextPath}/employees/delete?id=<c:out value='${employee.id}'/>">Delete</a>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
    </body>
</html>
