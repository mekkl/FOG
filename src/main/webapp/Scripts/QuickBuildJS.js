function confirmFunction() {
    alert("Forespørgsel er nu sendt til Fog. Tak for din henvendelse");
}
function saved() {
    alert("Forspørgslen er nu gemt. \n\
    Tag forbehold for at du kan blive kontaktet af Fog om gemte ordre.");
}


// Run on start
check();
///////////////////////////////////////////////


// Adds options to jsp after Calculate button
function check() {
    if ($("#shackCheckboxCheck").val() !== "") {
        $("#shackCheckbox").attr("checked", true);
        $("#shackLength").show();
        $("#shackWidth").show();

        $("#shackLengthInput").attr("required", true);
        $("#shackWidthInput").attr("required", true);
        restrictLength();
        restrictWidth();
    }
    if (document.getElementById("roofTypeCheck").value === "rejsning") {
        $("#angle").show();
        $("#roofPitched").show();
        $("#roofFlat").hide();
    } else {
        $("#angle").hide();
        $("#roofFlat").show();
        $("#roofPitched").hide();

    }
    if (document.getElementById("shackCheckboxCheck").value === "") {
        $("#shackLength").hide();
        $("#shackWidth").hide();
        $("#shackLengthInput").attr("value", null);
        $("#shackWidthInput").attr("value", null);
    }

    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth() + 1;
    var day = d.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    if (month < 10) {
        month = "0" + month;
    }
    //Add date restriction to wished delivery date
    $("#wishedDelivery").attr("min", year + "-" + month + "-" + day);
}

function restrictWidth() {
    $("#shackWidthInput").attr({
        "max": $("#width").val(),
        "min": widthRules(),
        "placeholder": "Tillad længde fra " + widthRules() + " til " + $("#width").val()
    });
}
;
function restrictLength() {
    $("#shackLengthInput").attr({
        "max": $("#length").val() / 2,
        "min": 100,
        "placeholder": "Tillad bredde fra 100 til " + ($("#length").val() / 2)
    });
}
;
function widthRules() {
    if ($("#width").val() <= 400) {
        return $("#width").val();
    } else
        return 100;
}
;

$('#registration').submit(function () {
    if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test($('#password1').val())) {
        alert("Dit password skal minimum indeholde : 8 karakterer, et lille bogstav, et stort bogstav og et tal");
        return false;
    }
    if ($('#password2').val() !== $('#password1').val()) {
        alert("Passwords matcher ikke!");
        return false;
    } else {
        alert('Du er hermed registreret, login for at gemme din carport');
        return true;
    }
});
// Adds angle option, when rejsning is chosen in the dropdown.
$('select[name=roofType]').on('change', function () {
    if (this.value === "rejsning") {
        $("#angle").show();
        $("#roofPitched").show();
        $("#roofFlat").hide();
    } else {
        $("#angle").hide();
        $("#roofFlat").show();
        $("#roofPitched").hide();
    }
});

// Adds Length and Height visibilty to jsp
$('input[name=shackCheckbox]').on('change', function () {
    if ($(this).is(':checked')) {
        $("#shackLength").show();
        $("#shackWidth").show();
        $("#shackLengthInput").attr("required", true);
        $("#shackWidthInput").attr("required", true);
        restrictLength();
        restrictWidth();
    } else {
        $("#shackLengthInput").attr("required", false);
        $("#shackWidthInput").attr("required", false);
        $("#shackLengthInput").val(null);
        $("#shackWidthInput").val(null);
        $("#shackLength").hide();
        $("#shackWidth").hide();
        $("#shackCheckbox").attr("value", null);
    }
});

$('select[name=length]').on('change', function () {
    $("#shackLengthInput").attr({
        "max": $("#length").val() / 2,
        "min": 100,
        "placeholder": "Tillad bredde fra 100 til " + ($("#length").val() / 2)
    });
});

$('select[name=width]').on('change', function () {
    $("#shackWidthInput").attr({
        "max": $("#width").val(),
        "min": widthRules(),
        "placeholder": "Tillad længde fra " + widthRules() + " til " + $("#width").val()
    });
});

// Adds angle option, when rejsning is chosen in the dropdown.
$('select[name=roofType]').on('change', function () {
    if (this.value === "rejsning") {
        $("#angle").show();
    } else {
        $("#angle").hide();
    }
});

$('#orderForm').on('change', function () {
    $("#sendSaveInquiry").hide();
});

$('#svg').on('click', () => {
    if($('.svgTop').css('display') === 'none') {
        $('.svgSide').hide();
        $('.svgTop').fadeIn(500);
    }
    else {
        $('.svgTop').hide();
        $('.svgSide').fadeIn(500);
    }
});
