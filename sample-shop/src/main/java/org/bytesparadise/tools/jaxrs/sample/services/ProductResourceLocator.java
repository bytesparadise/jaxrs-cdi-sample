package org.bytesparadise.tools.jaxrs.sample.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Path("/products")
public class ProductResourceLocator {

	@EJB
	private BookResource bookResource;

	@EJB
	GameResource gameResource;

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory
			.getLogger(ProductResourceLocator.class);

	@Path("/{type}")
	public Object getProductResourceLocator(@PathParam("type") String type) {
		if ("books".equals(type)) {
			return bookResource;
		}
		if ("games".equals(type)) {
			return gameResource;
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

}
