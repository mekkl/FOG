<%-- 
    Document   : index
    Created on : 16-11-2017, 14:00:17
    Author     : Orchi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="icon" href="http://shop.johannesfog.dk/gfx/foglogok.png"/>-->
        <link rel="icon" href="./img/foglogo.png"/>
        <title>Quick Byg</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <div class="row">
            <img class="col-sm-3" style="margin-right: 0px; padding-right: 0px;" src="./img/johannesfog.jpg" alt="johannesfog" >
            <h1 class="col-sm-9 well bg-primary text-white" style=" background:#124989; margin-top: 1px; margin-right: 0px; margin-bottom: 0px; padding-top: 30px; padding-bottom: 35px">Quick Byg</h1>
        </div>
        <div class="row">
            <div class="col-sm-12 text-center">
                <a href="FrontController?command=QuickBuild">
                    <img src="https://www.johannesfog.dk/globalassets/inriver/resources/33529" ></a>
            </div>
        </div>

        <div class="row" style="background-color: #124989; position: fixed; width: 100%; bottom: 0;">
            <div class="col-sm-12">
                <form id="passtoemplogin" name="passtoemplogin" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="passtoemplogin">
                    <input type="hidden" name="lastpage" value="error">
                    <input class="form-control" type="submit" value="employee login" style="margin:7px; width: 200px; height: 50px;">
                </form>
            </div>
        </div>
    </div>

    <script src="../Scripts/QuickBuildJS.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
