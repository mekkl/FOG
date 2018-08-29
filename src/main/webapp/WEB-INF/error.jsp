<%-- 
    Document   : error
    Created on : 12-12-2017, 10:19:54
    Author     : Orchi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String error = (String) request.getAttribute("error"); %>
        <p> <%= error %> </p>
    </body>
</html>
