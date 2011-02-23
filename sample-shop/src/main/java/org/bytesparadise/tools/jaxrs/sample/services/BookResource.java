package org.bytesparadise.tools.jaxrs.sample.services;
 
import java.lang.reflect.InvocationTargetException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bytesparadise.tools.jaxrs.sample.domain.Book;
import org.bytesparadise.tools.jaxrs.sample.services.representation.BookRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces({ MediaType.APPLICATION_XML, "application/json" })
@Stateless
public class BookResource {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(ProductResourceLocator.class);

	@PersistenceContext
	private EntityManager entityManager = null;

	@GET
	@Path("/{id}")
	@Produces({ "application/vnd.bytesparadise.book+xml", "application/xml", "application/json" })
	public BookRepresentation getProduct(@PathParam("id") Integer id) throws IllegalAccessException, InvocationTargetException {
		Book product = entityManager.find(Book.class, id);
		if (product == null) {
			throw new EntityNotFoundException("Book with id " + id + " not found");
		}
		return new BookRepresentation(product);
	}

}
