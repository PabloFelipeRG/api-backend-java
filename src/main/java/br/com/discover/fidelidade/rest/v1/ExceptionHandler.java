package br.com.discover.fidelidade.rest.v1;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;

import br.com.discover.fidelidade.rest.v1.response.ErrorResponse;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {
	
	private static Logger LOGGER = Logger.getLogger(ExceptionHandler.class.getName());

	@Override
	public Response toResponse(Throwable e) {
		LOGGER.log(Level.SEVERE, e.getMessage(), e);
		
		ErrorResponse error = new ErrorResponse("Ocorreu um erro interno", e.getMessage());
		return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(error).build();
	}
}
