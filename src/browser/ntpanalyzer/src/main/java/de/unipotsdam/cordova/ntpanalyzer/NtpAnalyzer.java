package de.unipotsdam.cordova.ntpanalyzer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class NtpAnalyzer {

	public static final int DIAGNOSIS_NO_PROBLEMS = 0;
	public static final int DIAGNOSIS_UNREACHABLE = 1;
	public static final int DIAGNOSIS_COMMUNICATION_ERROR = 2;
	public static final int DIAGNOSIS_COMPUTATION_ERROR = 3;

	public static final int DEFAULT_TIMEOUT = 10000;

	public int diagnoseNtpConnection(String host, Integer timeout) {
		InetAddress hostAddress = null;
		try {
			hostAddress = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			return DIAGNOSIS_UNREACHABLE;
		}

		NTPUDPClient client = null;
		TimeInfo result = null;
		try {
			client = new NTPUDPClient();
			client.setDefaultTimeout(timeout != null ? timeout : DEFAULT_TIMEOUT);
			client.open();

			result = client.getTime(hostAddress);
		} catch (SocketTimeoutException e) {
			return DIAGNOSIS_UNREACHABLE;
		} catch (IOException e) {
			return DIAGNOSIS_COMMUNICATION_ERROR;
		} finally {
			if (client != null && client.isOpen())
				client.close();
		}

		result.computeDetails();
		for (String comment : result.getComments()) {
			if (comment.startsWith("Error:"))
				return DIAGNOSIS_COMPUTATION_ERROR;
		}

		return DIAGNOSIS_NO_PROBLEMS;
	}
}
