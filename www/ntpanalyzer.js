var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

channel.createSticky('onCordovaInfoReady');
// Tell cordova channel to wait on the CordovaInfoReady event
channel.waitForInitialization('onCordovaInfoReady');

function Analyzer() {
}

Analyzer.prototype.diagnose = function(host, successCallback, errorCallback) {
	argscheck.checkArgs('SFF', 'NtpAnalyzer.diagnose', arguments);
	exec(successCallback, errorCallback, "NtpAnalyzer", "diagnose", [host]);
};

module.exports = new Analyzer();
