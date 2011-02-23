/**
 * 
 */
package org.bytesparadise.tools.jaxrs.sample.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author xcoulon
 * 
 */
@Entity
@DiscriminatorValue(value = "book")
public class Book extends Product {

	private String title = null;

	private String author = null;
	
	public Book() {
		super();
	}
	
	public Book(int id, String author, String title, Float price, String partNumber) {
		super(id, price, partNumber);
		this.title = title;
		this.author = author;
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
}
