/**
 * 
 */
package org.jboss.resteasy.test.providers.jaxb.regression;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

/**
 * @author Xi
 * 
 */
public class CustomOverrideTest {

	@Test
	public void shouldRetrieveCustomType() throws Exception {
		HttpGet httpGet = new HttpGet("http://localhost:8080/sample-shop/customers/1");
		httpGet.addHeader(new BasicHeader("accept", "text/x-vcard"));
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(httpGet);
		Assert.assertEquals("Wrong return code", 200, response.getStatusLine().getStatusCode());

	}
	@Test
	public void shouldRetrieveXMLType() throws Exception {
		HttpGet httpGet = new HttpGet("http://localhost:8080/sample-shop/customers/1");
		httpGet.addHeader(new BasicHeader("accept", "application/xml"));
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse response = httpClient.execute(httpGet);
		Assert.assertEquals("Wrong return code", 200, response.getStatusLine().getStatusCode());

	}
}
