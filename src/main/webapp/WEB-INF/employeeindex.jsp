<%-- 
    Document   : forespørgelseoversigt
    Created on : 21-11-2017, 17:20:21
    Author     : ML
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="./img/foglogo.png"/>
        <title>Quick Byg Ansat</title>
    </head>
    <body>
        <h1>View customers</h1>
        <form name="viewcustomers" action="FrontController" method="POST">
            <input type="hidden" name="lastpage" value="employeeindex">
            <input type="hidden" name="command" value="viewcustomers">
            <input type="submit" value="Submit">
        </form>
        
        <h1>View inquiries</h1>
        <form name="viewinquiries" action="FrontController" method="POST">
            <input type="hidden" name="lastpage" value="employeeindex">
            <input type="hidden" name="command" value="viewinquiries">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
