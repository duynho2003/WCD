<%-- 
    Document   : books
    Created on : Feb 29, 2024, 4:36:53 PM
    Author     : Tommy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Books Management Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>Books MANAGEMENT!</h1>
            <h2><a href="books/create">New Book</a></h2>
            <nav class="navbar navbar-light bg-light">
                <form class="form-inline d-flex" action="${pageContext.request.contextPath}/books/search" method="get">
                    <input class="form-control mx-2" type="search" name="searchText"
                           placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
                <form class="form-inline d-flex" action="${pageContext.request.contextPath}/books/sort" method="get">
                    <input class="form-control mx-2" type="number" name="min" required min="0"
                           placeholder="Min" aria-label="Search">
                    <input class="form-control mx-2" type="number" name="max" required
                           placeholder="Max" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </nav>
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Book name</th>
                    <th>Author name</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="book" items="${listBooks}">
                    <tr>
                        <td><c:out value="${book.id}"></c:out></td>
                        <td><c:out value="${book.name}"></c:out></td>
                        <td><c:out value="${book.author}"></c:out></td>
                        <td><c:out value="${book.price}"></c:out></td>
                        <td>
                            <img src="${pageContext.request.contextPath}/${book.image}" width="200" height="100"/>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/books/edit?id=<c:out value='${book.id}'/>">Edit</a> 
                            <a href="${pageContext.request.contextPath}/books/delete?id=<c:out value='${book.id}'/>">Delete</a>
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
