package de.unipotsdam.cordova.ntpanalyzer;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NtpAnalyzerPlugin extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("diagnose")) {
			String host = args.getString(0);
			this.diagnose(host, callbackContext);
			return true;
		}
		return false;
	}

	private void diagnose(final String host, final CallbackContext callbackContext) {
		if (host != null) {
			cordova.getThreadPool().execute(new Runnable() {
				public void run() {
					try {
						NtpAnalyzer analyzer = new NtpAnalyzer();
						int diagnose = analyzer.diagnoseNtpConnection(host);
						callbackContext.success(diagnose);
					} catch (Exception e) {
						callbackContext.error(e.getMessage());
					}
				}
			});
		} else {
			callbackContext.error("Expected a non-empty host");
		}
	}
}