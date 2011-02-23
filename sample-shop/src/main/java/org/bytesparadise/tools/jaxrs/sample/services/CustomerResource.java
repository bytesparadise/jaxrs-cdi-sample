package org.bytesparadise.tools.jaxrs.sample.services;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.bytesparadise.tools.jaxrs.sample.domain.Address;
import org.bytesparadise.tools.jaxrs.sample.domain.Customer;
import org.bytesparadise.tools.jaxrs.sample.domain.PurchaseOrder;
import org.bytesparadise.tools.jaxrs.sample.services.representation.CustomerRepresentation;
import org.bytesparadise.tools.jaxrs.sample.services.representation.CustomersRepresentation;
import org.bytesparadise.tools.jaxrs.sample.services.representation.Link;
import org.bytesparadise.tools.jaxrs.sample.services.representation.Page;
import org.bytesparadise.tools.jaxrs.sample.services.representation.PurchaseOrderRepresentation;
import org.jboss.resteasy.annotations.providers.jaxb.Formatted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Path(CustomerResource.URI_BASE)
@Consumes(MediaType.APPLICATION_XML)
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class CustomerResource {

	private final Logger logger = LoggerFactory
			.getLogger(CustomerResource.class);

	@PersistenceContext
	private EntityManager entityManager = null;

	public static final String URI_BASE = "/customers";

	@POST
	public Response createCustomer(CustomerRepresentation customerRepresentation) throws IllegalAccessException, InvocationTargetException {
		Customer customer = customerRepresentation.toCustomer();
		entityManager.persist(customer);
		logger.info("Created customer: {}", customer.getId());
		return Response.created(URI.create(URI_BASE + customer.getId()))
				.build();
	}

	@GET
	@Path("{id}")
	@Formatted
	public Response getCustomer(@PathParam("id") Integer id,
			@Context UriInfo uriInfo, @Context HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		Customer customer = entityManager.find(Customer.class, id);
		if (customer == null) {
			throw new EntityNotFoundException("Customer with id " + id
					+ " not found");
		}
		CustomerRepresentation customerRepresentation = new CustomerRepresentation(customer);
		UriBuilder orderUriBuilder = uriInfo.getBaseUriBuilder().clone()
				.path(PurchaseOrderResource.class).path("{id}");
		for (PurchaseOrder order : customer.getOrders()) {
			PurchaseOrderRepresentation orderRepresentation = new PurchaseOrderRepresentation(order);
			orderRepresentation.addSelfLink(new Link("self", orderUriBuilder.build(
					order.getId()).toString()));
			customerRepresentation.addOrder(orderRepresentation);
		}

		customerRepresentation.addSelfLink(new Link("self", uriInfo.getAbsolutePath()
				.toString()));

		ResponseBuilder responseBuilder = Response.ok().entity(customerRepresentation);
		return responseBuilder.build();
	}

	@GET
	@Path("{id}")
	@Produces({ "text/x-vcard" })
	public Response getCustomerAsVCard(@PathParam("id") Integer id,
			@Context UriInfo uriInfo) throws IllegalAccessException, InvocationTargetException {
		Customer customer = entityManager.find(Customer.class, id);
		if (customer == null) {
			throw new EntityNotFoundException("Customer with id " + id
					+ " not found");
		}
		CustomerRepresentation customerRepresentation = new CustomerRepresentation(customer);
		ResponseBuilder responseBuilder = Response.ok().entity(customerRepresentation);
		CacheControl cacheControl = new CacheControl();
		cacheControl.setMaxAge(300);
		responseBuilder.cacheControl(cacheControl);

		return responseBuilder.build();
	}

	@GET
	@Formatted
	public CustomersRepresentation getCustomers(@QueryParam("start") int start,
			@QueryParam("size") @DefaultValue("2") int size,
			@Context UriInfo uriInfo) throws IllegalAccessException, InvocationTargetException {
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.queryParam("start", "{start}");
		builder.queryParam("size", "{size}");

		Page<Customer> customersPage = new Page<Customer>(
				entityManager.createNamedQuery("Customer.findAll"), start, size);
		CustomersRepresentation customerDTOs = new CustomersRepresentation();
		for (Customer customer : customersPage.getList()) {
			CustomerRepresentation customerRepresentation = new CustomerRepresentation(customer);
			URI selfUri = uriInfo.getAbsolutePathBuilder().clone().path("{id}")
					.build(customer.getId());
			Link selfLink = new Link("self", selfUri.toString());
			customerRepresentation.getSelfLinks().add(selfLink);
			customerDTOs.addCustomerDTO(customerRepresentation);
		}
		// next link
		if (customersPage.hasNextPage()) {
			int next = customersPage.getStart() + customersPage.getPageSize();
			URI nextUri = builder.clone().build(next,
					customersPage.getPageSize());
			Link nextLink = new Link("next", nextUri.toString(),
					"application/xml");
			customerDTOs.addLink(nextLink);
		}
		// previous link
		if (customersPage.hasPreviousPage()) {
			int previous = customersPage.getStart()
					- customersPage.getPageSize();
			if (previous < 0) {
				previous = 0;
			}
			URI previousUri = builder.clone().build(previous,
					customersPage.getPageSize());
			Link previousLink = new Link("previous", previousUri.toString(),
					"application/xml");
			customerDTOs.addLink(previousLink);
		}
		return customerDTOs;
	}

	@PUT
	@Path("{id}")
	public void updateCustomer(@PathParam("id") int id, Customer update) {
		Customer current = entityManager.find(Customer.class, id);
		if (current == null) {
			throw new EntityNotFoundException("Customer with id " + id
					+ " not found");
		}
		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		Address address = new Address();
		current.setAddress(address);
		address.setStreet(update.getAddress().getStreet());
		address.setCity(update.getAddress().getCity());
		address.setZip(update.getAddress().getZip());
		address.setCountry(update.getAddress().getCountry());

	}

	@DELETE
	@Path("{id}")
	public void deleteCustomer(@PathParam("id") Integer id) {
		Customer customer = entityManager.find(Customer.class, id);
		entityManager.remove(customer);
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
