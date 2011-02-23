package org.bytesparadise.tools.jaxrs.sample.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@Entity
public class PurchaseOrder {

	@Id
	private Integer id;

	private Float totalPrice;

    private Boolean cancelled = false;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Customer customer;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products = new ArrayList<Product>();

    public PurchaseOrder() {
    	super();
	}
    
	public PurchaseOrder(Integer id, Customer customer, List<Product> products,
			float totalPrice) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.customer = customer;
		customer.getOrders().add(this);
		this.products.addAll(products);
	}


	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
