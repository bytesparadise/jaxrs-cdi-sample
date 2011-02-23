package org.bytesparadise.tools.jaxrs.sample.application;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bytesparadise.tools.jaxrs.sample.domain.Address;
import org.bytesparadise.tools.jaxrs.sample.domain.Book;
import org.bytesparadise.tools.jaxrs.sample.domain.Customer;
import org.bytesparadise.tools.jaxrs.sample.domain.Game;
import org.bytesparadise.tools.jaxrs.sample.domain.PurchaseOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class DataInitializer {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DataInitializer.class);

	@PersistenceContext
	private EntityManager entityManager = null;

	@PostConstruct
	public void loadData() {
		try {
			Customer customer = new Customer(1, "Xavier", "Coulon");
			customer.setAddress(new Address("256 Red Hat Lane", "Lille",
					"59650", null, "FRANCE"));
			entityManager.persist(customer);
			Book book = new Book(10, "Bill Burke", "RESTful Java with JAX-RS", 30.0f, "ISBN-12345");
			entityManager.persist(book);
			Game game = new Game(20, "Quake", 25.0f, "IDSOFT-12345");
			entityManager.persist(game);
			PurchaseOrder order = new PurchaseOrder(100, customer, Arrays.asList(book, game), 55.0f);
			entityManager.persist(order);
			LOGGER.warn("Customer stored with id '{}'", customer.getId());
		} catch (Throwable e) {
			// catching real exception because of
			// https://issues.jboss.org/browse/EJBTHREE-2237
			LOGGER.error("Failed to persist data because of", e);
		}
	}

}
