package com.example.services;

import com.example.models.Product;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

@Path("/Product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {

		List<Product> Products = new ArrayList<Product>();
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(Products).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(Product Product) {

		Product c = new Product();
		JSONObject rta = new JSONObject();
		c.setName(Product.getName());
		c.setDescription(Product.getDescription());

		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(rta.toJSONString()).build();
	}

	@OPTIONS
	public Response cors(@javax.ws.rs.core.Context HttpHeaders requestHeaders) {
		return Response
				.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods",
						"GET, POST, PUT, DELETE, OPTIONS")
				.header("Access-Control-Allow-Headers",
						"AUTHORIZATION, content-type, accept").build();
	}

}
