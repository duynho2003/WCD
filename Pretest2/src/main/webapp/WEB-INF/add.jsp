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
        <title>New Student</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>NEW STUDENT!</h1>
            <h2><a href="students">Back to Students list</a></h2>

            <form action="${pageContext.request.contextPath}/students/save" method="post">
                <table class="table">
                    
                    <tr>
                        <th>Student code</th>
                        <td>
                            <input type="text" name="code"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Student name</th>
                        <td>
                            <input type="text" name="name"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Student class</th>
                        <td>
                            <input type="text" name="class"/>
                        </td>
                    </tr>
                    <tr>
                        <th>Student dob</th>
                        <td>
                            <input type="date" name="dob"/>
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

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

    </body>
</html>
