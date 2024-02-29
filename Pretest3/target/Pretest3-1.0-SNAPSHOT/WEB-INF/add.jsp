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
        <title>New Employee</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>NEW Employee!</h1>
            <h2><a href="employees">Back to Employees list</a></h2>

            <form action="${pageContext.request.contextPath}/employees/save" method="post" enctype="multipart/form-data">
                <table class="table">
                    <tr>
                        <th>Employee Id</th>
                        <td>
                            <input type="number" name="id" required/>
                        </td>
                    </tr>
                    <tr>
                        <th>Employee first name</th>
                        <td>
                            <input type="text" name="firstname" required/>
                        </td>
                    </tr>
                    <tr>
                        <th>Employee last name</th>
                        <td>
                            <input type="text" name="lastname" required/>
                        </td>
                    </tr>
                    <tr>
                        <th>Employee dob</th>
                        <td>
                            <input type="date" name="dob" required/>
                        </td>   
                    </tr>
                    <tr>
                        <th>Image:</th>
                        <td>
                            <input type="file" name="image" id="img" accept="image/png,image/jpeg"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Create" class="btn btn-primary"/>
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
