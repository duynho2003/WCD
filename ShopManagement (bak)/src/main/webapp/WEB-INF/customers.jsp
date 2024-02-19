<%-- 
    Document   : customers
    Created on : Jan 31, 2024, 2:31:29 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>USER MANAGEMENT!</h1>
            <h2><a href="${pageContext.request.contextPath}/customers/new">New Customer</a></h2>
            <nav class="navbar navbar-light bg-light">
                <form class="form-inline d-flex" action="${pageContext.request.contextPath}/customers/search" method="get">
                    <input class="form-control mx-2" type="search" name="searchText"
                           placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </nav>
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="customer" items="${listCustomers}">
                    <tr>
                        <td><c:out value="${customer.id}"></c:out></td>
                        <td><c:out value="${customer.name}"></c:out></td>
                        <td><c:out value="${customer.email}"></c:out></td>
                        <td><c:out value="${customer.country}"></c:out></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/customers/edit?id=<c:out value='${customer.id}'/>">Edit</a>
                            <a href="${pageContext.request.contextPath}/customers/delete?id=<c:out value='${customer.id}'/>">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
