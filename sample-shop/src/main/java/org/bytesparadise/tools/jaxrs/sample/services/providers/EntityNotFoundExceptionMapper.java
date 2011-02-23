/**
 * 
 */
package org.bytesparadise.tools.jaxrs.sample.services.providers;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author xcoulon
 * 
 */
@Provider
public class EntityNotFoundExceptionMapper implements
		ExceptionMapper<EntityNotFoundException> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	public Response toResponse(EntityNotFoundException exception) {
		return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN)
				.build();
	}

}

