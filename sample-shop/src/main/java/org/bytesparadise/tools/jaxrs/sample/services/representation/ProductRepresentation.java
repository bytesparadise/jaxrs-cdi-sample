package org.bytesparadise.tools.jaxrs.sample.services.representation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.bytesparadise.tools.jaxrs.sample.domain.Product;

public abstract class ProductRepresentation {

	private Float price;

	private String partNumber;

	private List<Link> selfLinks = new ArrayList<Link>();

	public ProductRepresentation() {

	}

	public ProductRepresentation(Product product) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(this, product);
	}

	public List<Link> getSelfLinks() {
		return selfLinks;
	}
	
	public void addSelfLink(Link link) {
		selfLinks.add(link);
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

}
