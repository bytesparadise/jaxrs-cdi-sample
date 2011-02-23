package org.bytesparadise.tools.jaxrs.sample.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@NamedQuery(name="Customer.findAll", query="from Customer")
public class Customer {
	
	@Id
	private Integer id;

	private String firstName;

	private String lastName;
	
	@Embedded
	private Address address = new Address();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="customer", cascade=CascadeType.ALL)
	@OrderBy("id")
    private Set<PurchaseOrder> orders = new HashSet<PurchaseOrder>();

	public Customer() {
		super();
	}
	
	
	public Customer(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public Set<PurchaseOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<PurchaseOrder> orders) {
		this.orders = orders;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
