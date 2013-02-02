package com.interview.service;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.apache.http.client.ClientProtocolException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class RestClient {

	/**
	 */
	public static String BASE_URL = "http://localhost:9957/interview/api/";

	/**
	 * @param args
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		listAll();
		createuser();
		authenticate();
	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void listAll() throws ClientProtocolException, IOException {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(UriBuilder.fromUri(BASE_URL)
				.build());
		String x = service.path("/v1/listuser")
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println("Response " + x);
	}

	/**
	 * Req02===> register a user accepting (username, password, firstname, lastname)   and return the userId (primary key ) of the user table
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void createuser() throws ClientProtocolException, IOException {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(UriBuilder.fromUri(BASE_URL)
				.build());

		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("username", "denjoko");
		form.add("password", "donjuan");
		form.add("firstName", "Mujoko");
		form.add("lastName", "Jawa");
		String response = service.path("/v1/createUser2")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(String.class, form);
		System.out.println("Response " + response);
	}

	/**
	 * Req01==> Authenticate  username + password
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void authenticate() throws ClientProtocolException,
			IOException {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(UriBuilder.fromUri(BASE_URL)
				.build());

		MultivaluedMap<String, String> form = new MultivaluedMapImpl();
		form.add("username", "denjoko");
		form.add("password", "donjuan");
		ClientResponse response = service.path("/v1/authenticate")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		System.out.println("Response " + response);
	}

}
