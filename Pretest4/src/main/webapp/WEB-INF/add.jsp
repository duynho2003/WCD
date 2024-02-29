<%-- 
    Document   : add
    Created on : Mar 1, 2024, 12:42:35 AM
    Author     : Tommy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Book</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h1>NEW BOOK!</h1>
            <h2><a href="books">Back to Books list</a></h2>

            <form action="${pageContext.request.contextPath}/books/save" method="post" enctype="multipart/form-data">
                <table class="table">
                    <tr>
                        <th>Book Id</th>
                        <td>
                            <input type="number" name="id" required/>
                        </td>
                    </tr>
                    <tr>
                        <th>Book name</th>
                        <td>
                            <input type="text" name="name" required/>
                        </td>
                    </tr>
                    <tr>
                        <th>Book author</th>
                        <td>
                            <input type="text" name="author" required/>
                        </td>
                    </tr>
                    <tr>
                        <th>Book price</th>
                        <td>
                            <input type="number" name="price" min="0" max="10000" required/>
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
