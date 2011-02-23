package org.bytesparadise.tools.jaxrs.sample.services.representation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.bytesparadise.tools.jaxrs.sample.domain.PurchaseOrder;

@XmlRootElement(name = "order")
@XmlType(propOrder = { "totalPrice", "cancelled", "selfLinks", "customer", "products" })
public class PurchaseOrderRepresentation {

	private Float totalPrice;

	private Boolean cancelled = false;

	private List<Link> selfLinks = null;

	private CustomerRepresentation customer = null;

	private List<ProductRepresentation> products = null;

	public PurchaseOrderRepresentation() {

	}

	public PurchaseOrderRepresentation(PurchaseOrder order) throws IllegalAccessException, InvocationTargetException {
		setCancelled(order.getCancelled());
		setTotalPrice(order.getTotalPrice());
	}

	@XmlElementWrapper(name = "products")
	@XmlElements({ @XmlElement(name = "book", type = BookRepresentation.class), @XmlElement(name = "game", type = GameRepresentation.class) })
	public List<ProductRepresentation> getProducts() {
		return products;
	}

	public void addProduct(ProductRepresentation productRepresentation) {
		if(products == null) {
			products = new ArrayList<ProductRepresentation>();
		}
		products.add(productRepresentation);
	}

	@XmlElement(name = "customer")
	public CustomerRepresentation getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerRepresentation customer) {
		this.customer = customer;
	}

	@XmlElement(name = "link")
	public List<Link> getSelfLinks() {
		return selfLinks;
	}

	public void addSelfLink(Link self) {
		if(selfLinks == null) {
			selfLinks = new ArrayList<Link>();
		}
		selfLinks.add(self);
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

}
