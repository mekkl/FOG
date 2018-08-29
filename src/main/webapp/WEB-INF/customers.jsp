<%-- 
    Document   : inquiries
    Created on : Nov 26, 2017, 5:53:58 PM
    Author     : Mellem
--%>

<%@page import="FunctionLayer.Customer"%>
<%@page import="FunctionLayer.Inquiry"%>
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
        <% List<Customer> c = (List<Customer>)request.getAttribute("customers"); %>
        <% String tableCustomer = (String)request.getAttribute("customertable"); %>

        
        <div class="topbar">
            <div style="margin: 10px;">
                <form name="viewinquiries" action="FrontController" method="POST">
                    <input type="hidden" name="lastpage" value="customers">
                    <input type="hidden" name="command" value="viewinquiries">
                    <input type="submit" value="view inquiries" />
                </form>
            </div>
        </div>
        
        <h1>Customers</h1>
        
        <div class="container">
            <div class="toolbar">
                <input type="text" class="searchbar" placeholder="email.." onkeyup="rowSorting('customertable')">
                <input type="text" class="searchbar" placeholder="name.." onkeyup="rowSorting('customertable')">
                <input type="text" class="searchbar" placeholder="surname.." onkeyup="rowSorting('customertable')">
                <input type="text" class="searchbar" placeholder="phonenumber.." onkeyup="rowSorting('customertable')">
                <input type="text" class="searchbar" placeholder="address.." onkeyup="rowSorting('customertable')">
                <input type="text" class="searchbar" placeholder="zipcode.." onkeyup="rowSorting('customertable')">
            </div>
        </div>

        <div class="container">
            <div class="col-lg-12">
                <%= tableCustomer %>
            </div>
        </div>
                
        <script src="${pageContext.request.contextPath}/Scripts/script.js" type="text/javascript"></script>
    </body>
</html>
