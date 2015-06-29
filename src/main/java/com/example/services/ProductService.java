package com.example.services;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Path("/Product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {

	private static String URL = "mongodb://experiment_latency:experiment_latency@ds043971.mongolab.com:43971/experiment_latency";
	private static String DB = "experiment_latency";
	private static String CLL = "experiment_latency";

	@GET
	@Path("/Filter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInfoFilter(@QueryParam("Comercio") String comercio,
			@QueryParam("Categoria") String categoria,
			@QueryParam("Producto") String producto) {
		List<DBObject> myDoc = null;
		try {
			MongoClient mongo = new MongoClient(new MongoClientURI(URL));
			DB db = mongo.getDB(DB);

			DBCollection table = db.getCollection(CLL);

			BasicDBObject searchQuery = new BasicDBObject();

			if (comercio != null && !comercio.equals(""))
				searchQuery.put("Comercio", comercio);

			if (categoria != null && !categoria.equals(""))
				searchQuery.put("Categoria", categoria);

			if (producto != null && !producto.equals(""))
				searchQuery.put("Producto", producto);

			myDoc = table.find(searchQuery).toArray();

		} catch (UnknownHostException e) {
			return Response.status(500)
					.header("Access-Control-Allow-Origin", "*")
					.entity(e.getMessage()).build();
		}
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(myDoc.toString()).build();
	}

	@GET
	@Path("/MapReduce")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInfoMapReduce(@QueryParam("Comercio") String comercio,
			@QueryParam("Categoria") String categoria,
			@QueryParam("Producto") String producto) {
		List<DBObject> myDoc = null;
		try {
			MongoClient mongo = new MongoClient(new MongoClientURI(URL));
			DB db = mongo.getDB(DB);

			DBCollection table = db.getCollection(CLL);

			BasicDBObject searchQuery = new BasicDBObject();

			if (comercio != null && !comercio.equals(""))
				searchQuery.put("Comercio", comercio);

			if (categoria != null && !categoria.equals(""))
				searchQuery.put("Categoria", categoria);

			if (producto != null && !producto.equals(""))
				searchQuery.put("Producto", producto);

			myDoc = table.find(searchQuery).toArray();

		} catch (UnknownHostException e) {
			return Response.status(500)
					.header("Access-Control-Allow-Origin", "*")
					.entity(e.getMessage()).build();
		}
		return Response.status(200).header("Access-Control-Allow-Origin", "*")
				.entity(myDoc.toString()).build();
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

