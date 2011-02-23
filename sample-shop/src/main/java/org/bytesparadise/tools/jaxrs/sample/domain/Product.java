package org.bytesparadise.tools.jaxrs.sample.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING, length=4)
public abstract class Product {

	@Id
	private Integer id;

	private Float price;

	private String partNumber;
	
	public Product() {
		super();
	}
	
	public Product(Integer id, Float price, String partNumber) {
		super();
		this.id = id;
		this.price = price;
		this.partNumber = partNumber;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
