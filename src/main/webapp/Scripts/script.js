/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function findInquiryByEmailAndId(cosEmail,inqId) {
    var email = cosEmail;
    var id = inqId;
    
    window.open("/QuickBuild/FrontController?command=viewinquiry&customer="+email+"&id="+id+"&lastpage=error","nameofwindow","directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=1600,height=600");
}

function findInquiriesByEmail(cosEmail) {
    var email = cosEmail;
    
    window.location.replace("/QuickBuild/FrontController?command=viewinquiries&email="+email);
}

function sortTable(idTable, sortIndex) {
  var table, rows, switching, i, x, y, shouldSwitch, ascOrDesc;
  
  table = document.getElementById(idTable);
  ascOrDesc = document.getElementsByClassName("ascdesc")[sortIndex].value;

  switching = true;
  
  // continue loop until no switch have been made
  while (switching) {
    switching = false; 
    rows = table.getElementsByTagName("tr");
    
    // loops throug, except the first, which is the header
    for (i = 1; i < (rows.length - 1); i++) {
        shouldSwitch = false;
        
        // two objects which is compared
        x = rows[i].getElementsByTagName("td")[sortIndex];
        y = rows[i + 1].getElementsByTagName("td")[sortIndex];
        
        // Check if it should be sorted in descending or ascending order
        // Check if the two rows should switch place
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase() && ascOrDesc === "asc") {
            shouldSwitch= true;
            break;
        } else if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase() && ascOrDesc === "desc"){
            shouldSwitch= true;
            break;
        }
    }
    if (shouldSwitch) {
      // It the switch should be made, replace the two rows
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
  
  if (ascOrDesc === "asc") document.getElementsByClassName("ascdesc")[sortIndex].value = "desc";
  else document.getElementsByClassName("ascdesc")[sortIndex].value = "asc";
}

function rowTextSearch(searchbar, tableId, index) {
    var input, filter, table, tr, td, i;
    input = searchbar;
    filter = input.value.toUpperCase();
    table = document.getElementById(tableId);
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[index];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }       
    }
}

function setMaxValue(calledFrom, id, divideBy){
    document.getElementById(id).setAttribute("max",(calledFrom.value/divideBy));
}

function disOrEnable(id){
    document.getElementById(id).disabled = !document.getElementById(id).disabled;
}

function hideById(id){
    document.getElementById(id).style.display = 'none';
}

function hideByClassName(className){
    var options = document.getElementsByClassName(className);

    for (var option in options) {

        option.style.display = 'none';
    }
}

function showById(id){
    document.getElementById(id).style.display = 'inline-block';
}

function showByClassName(className){
    var options = document.getElementsByClassName(className);

    for (var option in options) {

        option.style.visibility = 'visible';
    }
}

function chooseRoofMat(chooser,pitchedId,flatId){
    if(chooser.value === 'fladt'){
        showById(flatId);
        hideById(pitchedId);
    } else {
        showById(pitchedId);
        hideById(flatId);
    }
}

function rowSorting(tableId) {
    var inputs, filter, table, tr, td, i, notmatch;

    table = document.getElementById(tableId);
    tr = table.getElementsByTagName("tr");
    
    notmatch = false;
    
    inputs = document.getElementsByClassName("searchbar");
    
    for (i = 0; i < tr.length; i++) {
        for (var j = 0; j < inputs.length; j++) {
            td = tr[i].getElementsByTagName("td")[j];
            if (td) {
                filter = inputs[j].value.toUpperCase();
                
                if (!(td.innerHTML.toUpperCase().indexOf(filter) > -1)) {
                    notmatch = true;
                } 
            }
        }
        if (notmatch) tr[i].style.display = "none";
        else if (!notmatch) tr[i].style.display = "";
        
        notmatch = false;
    }
}



if(document.getElementById("inquirytabel") !== null) {
    rowSorting("inquirytabel");
}

if(document.getElementById("roofType") !== null) {
    var e = document.getElementById("roofType");
    var rt = e.options[e.selectedIndex].value;

    if (rt === "fladt"){
        document.getElementById("angle").disabled = true;
    } else {
        document.getElementById("angle").disabled = false;
    }
    
    chooseRoofMat(document.getElementById("roofType"),"pitchedMat","flatMat");
}

if(document.getElementById("withShack") !== null) {
    var e = document.getElementById("withShack");
    var rt = e.options[e.selectedIndex].value;
    console.log(rt);
    if (rt === "nej"){
        document.getElementById("shackLength").disabled = true;
        document.getElementById("shackWidth").disabled = true;
    } else {
        document.getElementById("shackLength").disabled = false;
        document.getElementById("shackWidth").disabled = false;
    }
}

if(document.getElementById('length') !== null && document.getElementById('width') !== null){
    var carportLength = document.getElementById('length');
    var carportWidth = document.getElementById('width');
    
    setMaxValue(carportLength,'shackLength',2);
    setMaxValue(carportWidth,'shackWidth',1);
}



