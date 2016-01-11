var browser = require('cordova/platform');
var cordova = require('cordova');

function ajax(options) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == XMLHttpRequest.DONE ) {
            if(xmlhttp.status >= 200 && xmlhttp.status <= 299){
                options.success(xmlhttp.responseText);
            } else {
                options.error(xmlhttp);
            }
        }
    };

    xmlhttp.open("GET", options.url, true);
    xmlhttp.send();
}

function diagnose(success, error, params) {
    var url = "http://localhost:8080/ntpanalyzer/rest/diagnose";
    url += "?host=" + encodeURIComponent(params[0]);
    url += params[1] ? "&timeout=" + encodeURIComponent(params[1]) : "";

    ajax({
        url: url,
        success: function(result) { success(parseInt(result)); },
        error: error
    });
}

module.exports = {
    diagnose: diagnose
};

require("cordova/exec/proxy").add("NtpAnalyzer", module.exports);
