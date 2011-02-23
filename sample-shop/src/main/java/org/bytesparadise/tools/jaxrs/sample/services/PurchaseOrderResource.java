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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.bytesparadise.tools.jaxrs.sample.domain.Book;
import org.bytesparadise.tools.jaxrs.sample.domain.Game;
import org.bytesparadise.tools.jaxrs.sample.domain.Product;
import org.bytesparadise.tools.jaxrs.sample.domain.PurchaseOrder;
import org.bytesparadise.tools.jaxrs.sample.services.representation.BookRepresentation;
import org.bytesparadise.tools.jaxrs.sample.services.representation.CustomerRepresentation;
import org.bytesparadise.tools.jaxrs.sample.services.representation.GameRepresentation;
import org.bytesparadise.tools.jaxrs.sample.services.representation.Link;
import org.bytesparadise.tools.jaxrs.sample.services.representation.ProductRepresentation;
import org.bytesparadise.tools.jaxrs.sample.services.representation.PurchaseOrderRepresentation;
import org.jboss.resteasy.annotations.providers.jaxb.Formatted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Path("/orders")
public class PurchaseOrderResource {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(PurchaseOrderResource.class);

	@PersistenceContext
	private EntityManager entityManager = null;

	@GET
	@Path("/{id}")
	@Produces({ "application/vnd.bytesparadise.order+xml", MediaType.APPLICATION_XML, "application/json" })
	@Formatted
	public PurchaseOrderRepresentation getOrder(@PathParam("id") Integer id, @Context UriInfo uriInfo) throws IllegalAccessException, InvocationTargetException {
		PurchaseOrder order = entityManager.find(PurchaseOrder.class, id);
		if (order == null) {
			throw new EntityNotFoundException("Order with id " + id + " not found");
		}
		
		UriBuilder customerUriBuilder = uriInfo.getBaseUriBuilder().clone().path(CustomerResource.class).path("{id}");
		String customerUri = customerUriBuilder.build(order.getCustomer().getId()).toString();
		
		PurchaseOrderRepresentation purchaseOrderRepresentation = new PurchaseOrderRepresentation(order);
		CustomerRepresentation customerRepresentation = new CustomerRepresentation(order.getCustomer());
		customerRepresentation.addSelfLink(new Link("self", customerUri));
		purchaseOrderRepresentation.setCustomer(customerRepresentation);
		
		UriBuilder gameUriBuilder = uriInfo.getBaseUriBuilder().clone().path(ProductResourceLocator.class).path("games")
				.path("{id}");
		UriBuilder bookUriBuilder = uriInfo.getBaseUriBuilder().clone().path(ProductResourceLocator.class).path("books")
				.path("{id}");
		for (Product product : order.getProducts()) {
			ProductRepresentation productRepresentation = null;
			if(product instanceof Book) {
				productRepresentation = new BookRepresentation((Book)product);
				productRepresentation.addSelfLink(new Link("self", bookUriBuilder.build(product.getId()).toString()));
				
			}
			if(product instanceof Game) {
				productRepresentation = new GameRepresentation((Game)product);
				productRepresentation.addSelfLink(new Link("self", gameUriBuilder.build(product.getId()).toString()));
			}
			purchaseOrderRepresentation.addProduct(productRepresentation);
			
		}
		String orderUri = uriInfo.getAbsolutePath().toString();
		purchaseOrderRepresentation.addSelfLink(new Link("self", orderUri));
		
		return purchaseOrderRepresentation;
	}

}
