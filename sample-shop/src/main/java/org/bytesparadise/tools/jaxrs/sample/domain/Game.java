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
@DiscriminatorValue(value = "game")
public class Game extends Product {

	private String name = null;

	public Game() {
		super();
	}
	
	
	public Game(int id, String name, Float price, String partNumber) {
		super(id, price, partNumber);
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
