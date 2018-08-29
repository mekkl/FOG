<%-- 
    Document   : emplogin
    Created on : Dec 12, 2017, 10:26:58 AM
    Author     : Mellem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Employee</title>
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <link href="${pageContext.request.contextPath}/Style/emplogin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="inner">
            <div class="formbackground">
                <% String error = (String)request.getAttribute("error"); %>

                <div class="errorholder">
                    <% if(error != null) { %>
                    <p><%= error %></p>
                    <% } %>
                </div>
                
                <form id="loginemployee" name="loginemployee" action="FrontController" method="POST">
                    <div class="formcontent" >
                        <input type="hidden" name="command" value="loginemployee">
                        <input type="hidden" name="lastpage" value="emplogin">
                        <input class="form-control" type="number" name="id" placeholder="id" style="margin-bottom: 30px;">
                        <input class="form-control" type="password" name="pwd" placeholder="password"style="margin-bottom: 60px;">
                        <input id="buttondown" class="form-control" type="submit" value="login">
                    </div>
                </form>
                
                <div class="picholder">
                    <img src="./img/foglogo2.png" alt="Fog">
                </div>
            </div>
            </div>
        </div>
    </body>
</html>
