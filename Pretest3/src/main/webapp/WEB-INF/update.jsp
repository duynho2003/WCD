<%-- 
    Document   : add
    Created on : Feb 28, 2024, 7:19:03 PM
    Author     : Tommy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Employee</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>UPDATE Employee!</h1>
            <h2><a href="employees">Back to Employees list</a></h2>

            <form action="${pageContext.request.contextPath}/employees/edit" method="post" enctype="multipart/form-data">
                <table class="table">
                    <input type="hidden" name="id" value="${employee.id}"/>
                    <tr>
                        <th>First name</th>
                        <td>
                            <input type="text" name="firstname" value="${employee.firstname}" required/>
                        </td>
                    </tr>
                    <tr>
                        <th>Last name</th>
                        <td>
                            <input type="text" name="lastname" value="${employee.lastname}" required/>
                        </td>
                    </tr>
                    <tr>
                        <th>Date of birth</th>
                        <td>
                            <input type="date" name="dob" value="${employee.dob}" required/>
                        </td>   
                    </tr>
                    <tr>
                        <th>Image</th>
                        <td>
                            <input type="file" name="image" id="img" accept="image/png,image/jpeg"/>
                            <img src="${pageContext.request.contextPath}/${employee.image}" width="200" height="100"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Update" class="btn btn-primary"/>
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
