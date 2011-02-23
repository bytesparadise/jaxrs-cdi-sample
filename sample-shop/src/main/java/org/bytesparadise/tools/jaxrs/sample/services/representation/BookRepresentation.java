/**
 * 
 */
package org.bytesparadise.tools.jaxrs.sample.services.representation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.beanutils.BeanUtils;
import org.bytesparadise.tools.jaxrs.sample.domain.Book;

/**
 * @author xcoulon
 * 
 */
@XmlRootElement(name = "book")
@XmlType(propOrder = { "author", "title", "partNumber", "price", "selfLinks"})
public class BookRepresentation extends ProductRepresentation {

	private String title = null;

	private String author = null;

	public BookRepresentation() {
		super();
	}
	
	public BookRepresentation(Book book) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(this, book);
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	
	@Override
	@XmlElement(name = "link")
	public List<Link> getSelfLinks() {
		return super.getSelfLinks();
	}

	@Override
	@XmlElement(name="price")
	public Float getPrice() {
		return super.getPrice();
	}
	
	@Override
	@XmlElement(name="partNumber")
	public String getPartNumber() {
		return super.getPartNumber();
	}
}
