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
import org.bytesparadise.tools.jaxrs.sample.domain.Game;

/**
 * @author xcoulon
 * 
 */
@XmlRootElement(name = "game")
@XmlType(propOrder = { "name", "partNumber", "price", "selfLinks" })
public class GameRepresentation extends ProductRepresentation {

	private String name = null;

	public GameRepresentation() {
		super();
	}

	public GameRepresentation(Game game) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(this, game);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
