package org.bytesparadise.tools.jaxrs.sample.services;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.lang.reflect.InvocationTargetException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.bytesparadise.tools.jaxrs.sample.domain.Game;
import org.bytesparadise.tools.jaxrs.sample.services.representation.GameRepresentation;
import org.jboss.resteasy.annotations.providers.jaxb.Formatted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Consumes({ APPLICATION_XML, APPLICATION_JSON })
@Produces({ "application/xml", "application/json" })
public class GameResource {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory
			.getLogger(ProductResourceLocator.class);

	@PersistenceContext
	private EntityManager entityManager = null;

	@GET
	@Path("/{id}")
	@Formatted
	public GameRepresentation getProduct(@PathParam("id") Integer id) throws IllegalAccessException, InvocationTargetException {
		Game product = entityManager.find(Game.class, id);
		if (product == null) {
			throw new EntityNotFoundException("Game with id " + id
					+ " not found");
		}
		return new GameRepresentation(product);
	}

	@POST
	@Consumes({ "application/vnd.bytesparadise.game+xml", "application/xml",
			"application/json" })
	public void createProduct(GameRepresentation gameRepresentation) {
		throw new WebApplicationException(Status.FORBIDDEN);
	}

}
