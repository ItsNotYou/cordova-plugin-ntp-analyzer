var argscheck = require('cordova/argscheck'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

function Analyzer() {
}

Analyzer.prototype.diagnose = function(host, successCallback, errorCallback) {
    argscheck.checkArgs('SFF', 'NtpAnalyzer.diagnose', arguments);
    exec(successCallback, errorCallback, "NtpAnalyzer", "diagnose", [host]);
};

module.exports = new Analyzer();
