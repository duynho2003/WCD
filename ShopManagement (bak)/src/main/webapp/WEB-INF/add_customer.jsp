<%-- 
    Document   : add_customer
    Created on : Jan 31, 2024, 4:42:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New customer</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>NEW CUSTOMER!</h1>
            <h2><a href="customers">Back to Customers List</a></h2>

            <form action="${pageContext.request.contextPath}/customers/save" method="POST">
                <table class="table">
                    <tr>
                        <th>Customer name:</th>
                        <td>
                            <input type="text" name="name"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Customer email:</th>
                        <td>
                            <input type="text" name="email"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Customer country:</th>
                        <td>
                            <select name="country">
                                <c:forEach var="country" items="${listCountries}">
                                    <option value="${country.name}"><c:out value="${country.name}"></c:out></option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Create"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
