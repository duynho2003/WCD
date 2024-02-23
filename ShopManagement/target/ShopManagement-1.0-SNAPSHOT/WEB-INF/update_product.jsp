<%-- 
    Document   : index
    Created on : Jan 31, 2024, 2:22:27 PM
    Author     : trungtruong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop management</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>UPDATE PRODUCT!</h1>
            <h2><a href="products">Back to Customers list</a></h2>

            <form action="${pageContext.request.contextPath}/products/save" method="post" enctype="multipart/form-data">
                <table class="table">
                    <tr>
                        <th></th>
                        <td><input type="hidden" name="id" value="${product.id}"/></td>
                    </tr>
                    <tr>
                        <th>Product name:</th>
                        <td>
                            <input type="text" name="name" value="${product.name}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Price:</th>
                        <td>
                            <input type="number" name="price" value="${product.price}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Image</th>
                        <td>
                            <input type="file" name="image" id="img" accept="image/png,image/jpeg">
                            <img src="${pageContext.request.contextPath}/${product.image}" width="200" height="300" alt="product image"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="UPDATE"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

    </body>
</html>
