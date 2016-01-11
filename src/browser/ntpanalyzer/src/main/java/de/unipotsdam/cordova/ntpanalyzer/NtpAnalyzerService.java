package de.unipotsdam.cordova.ntpanalyzer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/diagnose")
public class NtpAnalyzerService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String diagnose(@QueryParam("host") String host, @QueryParam("timeout") String rawTimeout) {
		Integer timeout = null;
		if (rawTimeout != null) {
			try {
				timeout = Integer.parseInt(rawTimeout);
			} catch (NumberFormatException e) {
				Response response = Response.status(Status.BAD_REQUEST).entity("Expected timeout to be a number").build();
				throw new WebApplicationException(response);
			}
		}

		if (host != null) {
			try {
				NtpAnalyzer analyzer = new NtpAnalyzer();
				int diagnose = analyzer.diagnoseNtpConnection(host, timeout);
				return Integer.toString(diagnose);
			} catch (Exception e) {
				Response response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
				throw new WebApplicationException(response);
			}
		} else {
			Response response = Response.status(Status.BAD_REQUEST).entity("Expected a non-empty host").build();
			throw new WebApplicationException(response);
		}
	}
}
