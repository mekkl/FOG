<%-- 
    Document   : customers
    Created on : Nov 29, 2017, 10:59:42 AM
    Author     : Mellem
--%>

<%@page import="FunctionLayer.Inquiry"%>
<%@page import="FunctionLayer.Customer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        
        <link href="${pageContext.request.contextPath}/Style/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        
        <% String idTable = (String)request.getAttribute("tableTagId"); %>
        <% List<Inquiry> inq = (List<Inquiry>)request.getAttribute("inquiries"); %>
        <% String email = (String)request.getAttribute("email"); %>
        <% String tableInquiry = (String)request.getAttribute("inquirytable"); %>
        
        <div class="topbar">
            <div style="margin: 10px;">
                <form name="viewcustomers" action="FrontController" method="POST">
                    <input type="hidden" name="lastpage" value="inquiries">
                    <input type="hidden" name="command" value="viewcustomers">
                    <input type="submit" value="view customers" />
                </form>
            </div>
        </div>
        
        <h1>Inquiries</h1>
        
        <div class="container">
            <div class="toolbar">
                <input type="text" class="searchbar" placeholder="id.." onkeyup="rowSorting('<%= idTable %>')">
                <input type="text" class="searchbar" placeholder="status.." onkeyup="rowSorting('<%= idTable %>')">
                <% if (email.equals("")) { %>
                <input type="text" class="searchbar" placeholder="email.." onkeyup="rowSorting('<%= idTable %>')">
                <% } else { %>
                <input type="text" class="searchbar" placeholder="email.." value="<%= email %>" onkeyup="rowSorting('<%= idTable %>')">
                <% } %>
                <input type="text" class="searchbar" placeholder="employee.." onkeyup="rowSorting('<%= idTable %>')">
            </div>
        </div>
            
        <div class="container">
            <div class="col-lg-12">
                <%= tableInquiry %>
            </div>
        </div>
                
        <script src="${pageContext.request.contextPath}/Scripts/script.js" type="text/javascript"></script>
    </body>
</html>
