<%-- 
    Document   : newjsp
    Created on : Feb 19, 2024, 4:11:08 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customers</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>PRODUCTS MANAGEMENT!</h1>
            <h2><a href="customers/new">New Product</a></h2>
            <nav class="navbar navbar-light bg-light">
                <form class="form-inline d-flex" action="${pageContext.request.contextPath}/products/search" method="get">
                    <input class="form-control mx-2" type="search" name="searchText"
                           placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </nav>
            <table class="table">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="product" items="${listProducts}">
                    <tr>
                        <td><c:out value="${product.id}"></c:out></td>
                        <td><c:out value="${product.name}"></c:out></td>
                        <td><c:out value="${product.price}"></c:out></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/products/edit?id=<c:out value='${product.id}'/>">Edit</a> 
                            <a href="${pageContext.request.contextPath}/products/delete?id=<c:out value='${product.id}'/>">Delete</a>
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

