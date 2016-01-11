var argscheck = require('cordova/argscheck'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function Analyzer() {
}

Analyzer.prototype.diagnose = function(params, successCallback, errorCallback) {
    // OFF means Object, Function, Function
    argscheck.checkArgs('OFF', 'NtpAnalyzer.diagnose', arguments);
    exec(successCallback, errorCallback, "NtpAnalyzer", "diagnose", [params.host, params.timeout]);
};

module.exports = new Analyzer();
