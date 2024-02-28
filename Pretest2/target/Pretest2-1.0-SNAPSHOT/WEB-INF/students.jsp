<%-- 
    Document   : students
    Created on : Feb 28, 2024, 5:55:13 PM
    Author     : Tommy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
/* Thao tác thẻ với SQL  */
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Management Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>Student MANAGEMENT!</h1>
            <h2><a href="students/new">New Student</a></h2>

            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Class</th>
                    <th>Date of Birth</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="student" items="${listStudents}">
                    <tr>
                    <td><c:out value="${student.id}"></c:out></td>
                    <td><c:out value="${student.code}"></c:out></td>
                    <td><c:out value="${student.name}"></c:out></td>
                    <td><c:out value="${student.classString}"></c:out></td>
                    <td><c:out value="${student.dob}"></c:out></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/students/edit?id=<c:out value='${student.id}'/>">Edit</a> 
                        <a href="${pageContext.request.contextPath}/students/delete?id=<c:out value='${student.id}'/>">Delete</a>
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
