package org.bytesparadise.tools.jaxrs.sample.services.representation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@XmlRootElement(name = "link")
public class Link {
	protected final String relationship;
	protected final String href;
	protected final String type;

	public Link() {
		this(null, null, null);
	}
	public Link(String relationship, String href) {
		this(relationship, href, null);
	}

	public Link(String relationship, String href, String type) {
		this.relationship = relationship;
		this.href = href;
		this.type = type;
	}

	@XmlAttribute(name = "rel")
	public String getRelationship() {
		return relationship;
	}

	@XmlAttribute(name="href")
	public String getHref() {
		return href;
	}

	@XmlAttribute(name="type")
	public String getType() {
		return type;
	}

}
