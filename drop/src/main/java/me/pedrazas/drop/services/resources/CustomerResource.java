package me.pedrazas.drop.services.resources;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import me.pedrazas.drop.services.resources.services.CustomerService;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.hazelcast.core.HazelcastInstance;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

	private HazelcastInstance client;

	public CustomerResource(HazelcastInstance client) {
		super();
		this.client = client;
	}

	@GET
	@Timed
	public Response count() {
		int numObjects = CustomerService.getCustomersCount(this.client);
		return Response.status(Response.Status.ACCEPTED).entity(numObjects)
				.build();
	}

	@POST
	@Timed
	public Response write(@Context HttpServletRequest request,
			@QueryParam("num") Optional<String> num) {

		List<Pair<String, String>> pairs = new ArrayList<Pair<String, String>>();
		Instant start = Instant.now();
		try {
			if (num.isPresent()) {
				int top = Integer.parseInt(num.get());
				for (int i = 0; i < top; i++) {
					CustomerService.addCustomer(client);
					System.out.println(i);
				}
				Pair<String, String> keyValue1 = new ImmutablePair<String, String>("total", Integer.toString(top));
				pairs.add(keyValue1);
				Instant end = Instant.now();
				Pair<String, String> keyValue2 = new ImmutablePair<String, String>("time", Duration.between(start, end).toString());
				pairs.add(keyValue2);
				return Response.status(Response.Status.ACCEPTED).entity(pairs).build();
			}

		} catch (Exception e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(num.get()).build();
		}
		return Response.status(Response.Status.NOT_ACCEPTABLE).entity(num.get()).build();
	}
}
