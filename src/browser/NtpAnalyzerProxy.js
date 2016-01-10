var browser = require('cordova/platform');
var cordova = require('cordova');
var $ = require('jquery');

function diagnose(host, success, error) {
    $.ajax({
        url: "http://localhost:8080/ntpanalyzer/rest/diagnose?host=" + encodeURIComponent(host),
        success: function(result) { success(parseInt(result)); },
        error: error,
        crossDomain: true
    });
}

module.exports = {
    diagnose: diagnose
};

require("cordova/exec/proxy").add("NtpAnalyzer", module.exports);
