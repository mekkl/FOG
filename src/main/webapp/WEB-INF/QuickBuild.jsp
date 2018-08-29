<%@page import="FunctionLayer.Inquiry"%>
<%@page import="FunctionLayer.BillOfMaterials"%>
<%-- 
    Document   : QuickBuild
    Created on : 14-11-2017, 12:40:08
    Author     : Orchi
--%>

<%@page import="FunctionLayer.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="./Style/quickbuild.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" href="./img/foglogo.png"/>
        <title>Quick Byg</title>
    </head>

    <body>
        <div class="wrapper container-fluid">
            <% if (request.getAttribute("error") != null) {
                    String error = (String) request.getAttribute("error");%>
            <script>alert("<%=error%>")</script>

            <%}%>
            <div class="row">
                <img class="col-sm-3" style="margin-right: 0px; margin-left: 0px; padding-left: 0px; padding-right: 0px;" src="./img/johannesfog.jpg" alt="johannesfog" >
                <h1 class="col-sm-6 well text-center bg-primary text-white" style=" background:#124989; margin-top: 1px; margin-right: 0px; padding-top: 30px; padding-bottom: 35px">Quick Byg</h1>
                <% Customer customer = null;
                    if ((customer = (Customer) request.getSession().getAttribute("customer")) != null) {%>
                <h3 class="col-sm-3 well text-center bg-primary text-white" style=" background:#124989; margin-top: 1px; padding-top: 25px; padding-bottom: 27px;">Logget ind som :<br><%= customer.getName()%></h3>
                    <% } else {%>
                <div class="col-sm-3 well" style="background:#124989; margin-left: 0px; padding-top: 18px; padding-bottom: 8px;">
                    <p class="text-right" style="margin-right: 42px"><a class="btn btn-sm btn-default"  data-toggle="modal" data-target="#login" >  Login  </a></p>
                    <p class="text-right"><a class="btn btn-sm btn-default" data-toggle="modal" data-target="#registerUser">Register here</a></p>
                </div>
                <% } %>
            </div>

            <%---------- Setup inquiry ------------%>
            <% Inquiry inquiry = null;
                if (request.getSession().getAttribute("inquiry") != null) {
                    inquiry = (Inquiry) request.getSession().getAttribute("inquiry");
                }%>

            <!------------------ MESUREMENTS FORM ------------------>
            <div id="measurements" >

                <form id="orderForm" name="order" action="FrontController" method="POST">
                    <!---------- First row col 1 ------------->
                    <div class="row">
                        <input type="hidden" name="command" value="calculate">

                        <div class="col-sm-1">
                            Vælg længde<br>
                            <select class="form-control" name="length" id="length" >
                                <% int length = 0;
                                    if (inquiry != null) {
                                        length = inquiry.getCarportLength();
                                    }%>
                                <option value=240 <%if (length == 240) { %> selected <%} %>>240</option>
                                <option value=270 <%if (length == 270) { %> selected <%} %>>270</option>
                                <option value=300 <%if (length == 300) { %> selected <%} %>>300</option>
                                <option value=330 <%if (length == 330) { %> selected <%} %>>330</option>
                                <option value=360 <%if (length == 360) { %> selected <%} %>>360</option>
                                <option value=390 <%if (length == 390) { %> selected <%} %>>390</option>
                                <option value=420 <%if (length == 420) { %> selected <%} %>>420</option>
                                <option value=450 <%if (length == 450) { %> selected <%} %>>450</option>
                                <option value=480 <%if (length == 480) { %> selected <%} %>>480</option>
                                <option value=510 <%if (length == 510) { %> selected <%} %>>510</option>
                                <option value=540 <%if (length == 540) { %> selected <%} %>>540</option>
                                <option value=570 <%if (length == 570) { %> selected <%} %>>570</option>
                                <option value=600 <%if (length == 600) { %> selected <%} %>>600</option>
                                <option value=630 <%if (length == 630) { %> selected <%} %>>630</option>
                                <option value=660 <%if (length == 660) { %> selected <%} %>>660</option>
                                <option value=690 <%if (length == 690) { %> selected <%} %>>690</option>
                                <option value=720 <%if (length == 720) { %> selected <%} %>>720</option>
                                <option value=750 <%if (length == 750) { %> selected <%} %>>750</option>
                            </select>
                            <br>
                            Vælg bredde<br>
                            <select class="form-control" id="width" name="width">
                                <% int width = 0;
                                    if (inquiry != null) {
                                        width = inquiry.getCarportWidth();
                                    }%>
                                <option value=240 <%if (width == 240) { %> selected <%} %>>240</option>
                                <option value=270 <%if (width == 270) { %> selected <%} %>>270</option>
                                <option value=300 <%if (width == 300) { %> selected <%} %>>300</option>
                                <option value=330 <%if (width == 330) { %> selected <%} %>>330</option>
                                <option value=360 <%if (width == 360) { %> selected <%} %>>360</option>
                                <option value=390 <%if (width == 390) { %> selected <%} %>>390</option>
                                <option value=420 <%if (width == 420) { %> selected <%} %>>420</option>
                                <option value=450 <%if (width == 450) { %> selected <%} %>>450</option>
                                <option value=480 <%if (width == 480) { %> selected <%} %>>480</option>
                                <option value=510 <%if (width == 510) { %> selected <%} %>>510</option>
                                <option value=540 <%if (width == 540) { %> selected <%} %>>540</option>
                                <option value=570 <%if (width == 570) { %> selected <%} %>>570</option>
                                <option value=600 <%if (width == 600) { %> selected <%} %>>600</option>
                                <option value=630 <%if (width == 630) { %> selected <%} %>>630</option>
                                <option value=660 <%if (width == 660) { %> selected <%} %>>660</option>
                                <option value=690 <%if (width == 690) { %> selected <%} %>>690</option>
                                <option value=720 <%if (width == 720) { %> selected <%} %>>720</option>
                                <option value=750 <%if (width == 750) { %> selected <%} %>>750</option>
                                <option value=780 <%if (width == 780) { %> selected <%} %>>780</option>
                            </select>
                            <br>

                            Vælg højde<br>
                            <select class="form-control" name="height">
                                <% int height = 0;
                                    if (inquiry != null) {
                                        height = inquiry.getCarportHeight();
                                    }%>
                                <option value=210 <%if (height == 210) { %> selected <%} %>>210</option>
                                <option value=240 <%if (height == 240) { %> selected <%} %>>240</option>
                                <option value=270 <%if (height == 270) { %> selected <%} %>>270</option>
                                <option value=300 <%if (height == 300) { %> selected <%} %>>300</option>
                            </select>
                            <br>

                            Vælg tagtype<br>
                            <input id="roofTypeCheck" type="hidden" value="<%if(inquiry != null){%><%=inquiry.getRoofType()%><%}%>">
                            <select class="form-control" name="roofType">
                                <% String roofType = "";
                                    if (inquiry != null) {
                                        roofType = inquiry.getRoofType();
                                    }
                                %>
                                <option value="fladt" <%if (roofType.equals("fladt")) { %> selected <%} %>>fladt</option>
                                <option value="rejsning" <%if (roofType.equals("rejsning")) { %> selected <%}%>>rejsning</option>
                            </select>
                            <br>

                            Tilvælg redskabsrum<br>
                            <input id="shackCheckbox" name="shackCheckbox" type="checkbox"/>
                            <input id="shackCheckboxCheck" name="shackCheckboxCheck" type="hidden" <% if ((String) request.getSession().getAttribute("shackCheckbox") != null) {%>value="on"<%}%>>
                            <br>
                        </div> <!--- CLOSES FIRST COLUMN OF MESUREMENTS-->

                        <!------------------------- SECOND COLUMN ------------------->
                        <div class="col-sm-2">
                            Tag materiale<br>
                            <div id="roofPitched">
                                <!--LIST VIEW-->
                                <select name="roofMaterialPitchedProduct" class="form-control">
                                    <%= request.getSession().getAttribute("roofMaterialPitched")%>
                                </select>
                                <br>
                            </div>

                            <div id="roofFlat">
                                <!--LIST VIEW-->
                                <select  name="roofMaterialFlatProduct" class="form-control" >
                                    <%= request.getSession().getAttribute("roofMaterialFlat")%>
                                </select>
                                <br>
                            </div>

                            <div id="shackLength">
                                Redskabsrum bredde<br>
                                <input id="shackLengthInput" type="number" min="100"  class="form-control" name="shackLength" value="<%if(inquiry != null && inquiry.getShackWidth() != 0){%><%=inquiry.getShackWidth()%><%}%>">
                                <br>
                            </div>
                            <div id="shackWidth">
                                Redskabsrum længde<br>
                                <input id="shackWidthInput" type="number" min="100"  class="form-control" name="shackWidth" value="<% if (inquiry != null && inquiry.getShackLength() != 0) {%><%= inquiry.getShackLength()%><%}%>">
                                <br>
                            </div>

                            <div id="angle">
                                Vælg hældning<br>
                                <select class="form-control" name="angle">
                                    <% int angle = -1;
                                        if (inquiry != null && inquiry.getAngle() != null) {
                                            angle = Integer.parseInt(inquiry.getAngle());
                                        }
                                    %>
                                    <option value=15 <%if (angle == 15) { %> selected <%} %>>15°</option>
                                    <option value=20 <%if (angle == 20) { %> selected <%} %>>20°</option>
                                    <option value=25 <%if (angle == 25) { %> selected <%} %>>25°</option>
                                    <option value=30 <%if (angle == 30) { %> selected <%} %>>30°</option>
                                    <option value=35 <%if (angle == 35) { %> selected <%} %>>35°</option>
                                    <option value=40 <%if (angle == 40) { %> selected <%} %>>40°</option>
                                    <option value=45 <%if (angle == 45) { %> selected <%} %>>45°</option>
                                </select>
                            </div>
                        </div> <!-- END 2 COLUMN --->

                        <!------------------------------ THIRD COLUMN (SVG) ---------------------------->
                        <div id="svg" class="col-sm-6 text-center"> 
                            <% if (request.getSession().getAttribute("svgTop") != null && request.getSession().getAttribute("svgSide") != null) {
                                    String svgTop = (String) request.getSession().getAttribute("svgTop");
                                    String svgSide = (String) request.getSession().getAttribute("svgSide");
                            %>
                            <div class="svgTop">
                                <%= svgTop%>
                            </div>
                            <div class="svgSide" style="display: none;">
                                <%= svgSide%>
                            </div>
                            <% } %>
                        </div> <!----- END 3. COLUMN ------------>

                        <!--------------------------------- FOURTH COLUMN ----------------------------->
                        <div class="col-sm-3"> 
                            <!--ORDER DATA SETUP-->
                            <%if (request.getSession().getAttribute("customer") != null) {%>
                            <div class="well" style="margin-top: 0px; margin-bottom: 0px; background:#124989; color: white">

                                <!--LIST VIEW-->
                                Gemte forespørgsler
                                <ul id="previousInquiries" class="list-group">
                                    <%= request.getSession().getAttribute("inquiries")%>
                                </ul>
                            </div>
                            <%}%>

                            <div class="well" style="background:#124989; color: white">
                                <% if (inquiry != null) {%>
                                TOTAL PRIS: <br>
                                <%= inquiry.getBom().getTotalPrice()%>,-
                                <Br>
                                <%}%>
                                <input form="orderForm" class="btn btn-default"  type="submit" value="Beregn"/>&emsp;&emsp;&emsp;&emsp;

                                <form name="newInquiry" action="FrontController" method="POST">
                                    <input type="hidden" name="lastpage" value="QuickBuild">
                                    <input type="hidden" name="command" value="newInquiry">
                                    <input class="btn btn-warning" type="submit" value="Ny forespørgsel"/>
                                </form>

                                <!--if user is logged in and has made a calculation-->
                                <%if (customer != null && inquiry != null) {%>
                                <!--Save order-->
                                <div id="sendSaveInquiry">
                                    Gem til senere behandling?
                                    <form form="orderForm" name="saveInquiry" action="FrontController" method="POST">
                                        <input type="hidden" name="lastpage" value="QuickBuild">
                                        <input type="hidden" name="command" value="saveInquiry">
                                        <input class="btn btn-default" onclick="saved()" type="submit" value="Gem forespørgsel"/>
                                    </form>

                                    <!-- Send order -->
                                    Send forespørgsel til Fog?
                                    <form form="sendInquiry" name="sendInquiry" action="FrontController" method="POST">
                                        <input type="hidden" name="lastpage" value="QuickBuild">
                                        <input type="hidden" name="command" value="sendInquiry">
                                        <input class="btn btn-default" onclick="confirmFunction()" type="submit" value="Send forespørgsel"/>
                                    </form>
                                    <% }%>
                                </div>
                            </div>

                            <!--If user is logged in-->
                            <%if (customer != null) {%>  
                            <div class="form-group">
                                <label>Kommentar til ordre</label>
                                <textarea class="form-control" rows="3" name="comment" id="comment" maxlength="2000" placeholder="Skriv her hvis du har nogen særlige ønsker, eller spørgsmål. (Bemærk kun 2000 karakterer tilladt)"><%if (request.getSession().getAttribute("comment") != null) {%><%= (String) request.getSession().getAttribute("comment")%><%}%></textarea>
                            </div>

                            <div class="form-group">
                                <label>Ønsket leveringstid</label> <br>
                                <input name="wishedDelivery" id="wishedDelivery" type="date" value="<%= request.getSession().getAttribute("wishedDelivery")%>">
                                <span class="help-block"> Bemærk der er minimum 1 uges behandlingstid</span>                    
                            </div>
                            <%}%>

                        </div> <!------ END LAST COLUMN FIRST ROW ----->
                </form>
            </div> <!--------- FIRST ROW ------->
        </div> <!--- END MESUREMENTS FORM ------>
    </div> <!-- WRAPPER -->
    <%----------------------------------------------------------------------------------------------%>
    <%-- MODAL FOR LOGIN AND REGISTER --%>
    <%----------------------------------------------------------------------------------------------%>
    <div class="container">
        <div class="row">

            <!-- Modal Register -->
            <form action="FrontController" method="post" id="registration" name="registration" >
                <input type="hidden" name="lastpage" value="QuickBuild">
                <div class="modal fade" id="registerUser" tabindex="-1" role="dialog" aria-labelledby="registerModal" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Luk</span></button>
                                <h4 class="modal-title" id="myModalLabel">Registrer</h4>
                            </div>

                            <div class="modal-body form-inline">
                                <div class="form-group has-feedback">
                                    <label class="control-label" for="emaillabel">* Email adresse</label>
                                    <input name="email" type="email" class="form-control"  id="emailinput" aria-describedby="emailinput" required="">
                                    <span class="glyphicon glyphicon-envelope form-control-feedback" aria-hidden="true"></span>
                                </div>

                                <div class="form-group has-feedback">
                                    <label class="control-label" for="passwordlabel">* Password</label>
                                    <input name="password1" type="password" class="form-control" id="password1" aria-describedby="passwordinput" required="">
                                    <span class="glyphicon glyphicon-asterisk form-control-feedback" aria-hidden="true"></span>
                                </div>

                                <div class="form-group has-feedback">
                                    <label class="control-label" for="re-enterpasswordlabel">* Gentag password</label>
                                    <input name="password2" type="password" class="form-control" id="password2" aria-describedby="re-enterpasswordinput" required="">
                                    <span class="glyphicon glyphicon-asterisk form-control-feedback" aria-hidden="true"></span>
                                </div>

                                <div class="form-group has-feedback">
                                    <label class="control-label" for="postcodelabel">* Postnummer</label>
                                    <input name="zipcode" type="number" class="form-control" id="postcodeinput" aria-describedby="postcodeinput">
                                    <span class="glyphicon glyphicon-home form-control-feedback" aria-hidden="true"></span>
                                </div>

                                <div class="form-group has-feedback">
                                    <label class="control-label" for="firstnamelabel">* Fornavn</label>
                                    <input name="name" type="text" class="form-control" id="firstnameinput" aria-describedby="firstnameinput" required="">
                                    <span class="glyphicon glyphicon-user form-control-feedback" aria-hidden="true"></span>
                                </div>

                                <div class="form-group has-feedback">
                                    <label class="control-label" for="lastnamelabel">* Efternavn</label>
                                    <input name="surname" type="text" class="form-control" id="lastnameinput" aria-describedby="lastnameinput" required="">
                                    <span class="glyphicon glyphicon-user form-control-feedback" aria-hidden="true"></span>
                                </div>

                                <div class="form-group has-feedback">
                                    <label class="control-label" for="phonenumber">* Tlf. nummer</label>
                                    <input name="phonenumber" type="number" class="form-control" id="phonenumber" aria-describedby="phonenumber" required="">
                                    <span class="glyphicon glyphicon-phone form-control-feedback" aria-hidden="true"></span>
                                </div>

                                <div class="form-group has-feedback">
                                    <label class="control-label" for="address">* Adresse</label>
                                    <input name="address" type="text" class="form-control" id="address" aria-describedby="address" required="">
                                    <span class="glyphicon glyphicon-home form-control-feedback" aria-hidden="true"></span>
                                </div>

                                <br/>

                                <div class="form-group">
                                    <input  type="hidden" name="command" value="register">
                                    <input type="hidden" name="lastpage" value="QuickBuild">
                                    <button type="submit" class="btn btn-primary">Registrer</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </form>
        </div>

        <%----------------------------------------------------------------------------------------------%>
        <!-- Modal Login form -->
        <form id="logingform" action="FrontController" method="post">
            <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="LoginModal" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel">Login</h4>
                        </div>

                        <div class="modal-body">
                            <div class="form-group has-feedback">
                                <label class="control-label">Email</label>
                                <input name="email" type="email" class="form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status" placeholder="Email" required>
                                <span class="glyphicon glyphicon-envelope form-control-feedback" aria-hidden="true"></span>
                            </div>

                            <div class="form-group has-feedback">
                                <label class="control-label">Password</label>
                                <input name="password" type="password" class="form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status" placeholder="Password" required >
                                <span class="glyphicon glyphicon-asterisk form-control-feedback" aria-hidden="true"></span>
                            </div>

                            <input type="hidden" name="command" value="login">
                            <input type="hidden" name="lastpage" value="QuickBuild">
                            <button type="sumbit" class="btn btn-primary">Login</button>

                        </div>

                        <div class="modal-footer">
                            <h4 class="modal-title" id="myModalLabel">Register</h4>
                            Hvis du ikke allerede er medlem så  <a data-toggle="modal" data-target="#registerUser" data-dismiss="modal"> Registrer dig her</a>
                            <!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>-->
                        </div>

                    </div>
                </div>
            </div>
        </form>
    </div>

    <!-- Latest compiled JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="./Scripts/QuickBuildJS.js" type="text/javascript"></script>
</body>
</html>
