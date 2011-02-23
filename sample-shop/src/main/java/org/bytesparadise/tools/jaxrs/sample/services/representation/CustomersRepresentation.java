package org.bytesparadise.tools.jaxrs.sample.services.representation;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@XmlRootElement(name = "customers")
public class CustomersRepresentation {
	protected final Collection<CustomerRepresentation> customerRepresentations = new ArrayList<CustomerRepresentation>();
	protected final List<Link> links = new ArrayList<Link>();
	
	@XmlElementRef
	public Collection<CustomerRepresentation> getCustomers() {
		return customerRepresentations;
	}

	public void addCustomerDTO(CustomerRepresentation customerRepresentation) {
		customerRepresentations.add(customerRepresentation);
	}
	
	@XmlElementRef
	public List<Link> getLinks() {
		return links;
	}
	
	public void addLink(Link link) {
		links.add(link);
	}
	
	

	@XmlTransient
	public String getNext() {
		if (links == null)
			return null;
		for (Link link : links) {
			if ("next".equals(link.getRelationship()))
				return link.getHref();
		}
		return null;
	}

	@XmlTransient
	public String getPrevious() {
		if (links == null)
			return null;
		for (Link link : links) {
			if ("previous".equals(link.getRelationship()))
				return link.getHref();
		}
		return null;
	}

}
