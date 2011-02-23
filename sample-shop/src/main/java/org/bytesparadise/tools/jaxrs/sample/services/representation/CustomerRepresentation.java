package org.bytesparadise.tools.jaxrs.sample.services.representation;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.bytesparadise.tools.jaxrs.sample.domain.Address;
import org.bytesparadise.tools.jaxrs.sample.domain.Customer;

@XmlRootElement(name = "customer")
@XmlType(propOrder = { "firstName", "lastName", "address", "orders", "selfLinks" })
public class CustomerRepresentation {

	private Integer id;

	private String firstName;

	private String lastName;

	private Address address;

	private final Set<PurchaseOrderRepresentation> orders = new HashSet<PurchaseOrderRepresentation>();

	private final List<Link> selfLinks = new ArrayList<Link>();

	public CustomerRepresentation() {
	}
	
	
	public CustomerRepresentation(Customer customer) throws IllegalAccessException, InvocationTargetException {
		setAddress(customer.getAddress());
		setFirstName(customer.getFirstName());
		setLastName(customer.getLastName());
	}

	public Customer toCustomer() throws IllegalAccessException, InvocationTargetException {
		Customer customer = new Customer();
		customer.setAddress(getAddress());
		customer.setFirstName(getFirstName());
		customer.setLastName(getLastName());
		return customer;
	}

	@XmlTransient
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@XmlElementWrapper(name="orders")
	@XmlElements(@XmlElement(name = "order"))
	public Set<PurchaseOrderRepresentation> getOrders() {
		return orders;
	}

	public void addOrder(PurchaseOrderRepresentation order) {
		orders.add(order);
	}
	
	@XmlElement(name = "link")
	public List<Link> getSelfLinks() {
		return selfLinks;
	}

	public void addSelfLink(Link link) {
		selfLinks.add(link);
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
