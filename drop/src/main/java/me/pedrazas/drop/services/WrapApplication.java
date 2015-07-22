package me.pedrazas.drop.services;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import me.pedrazas.drop.services.healthcheck.PingHealthCheck;
import me.pedrazas.drop.services.resources.CustomerResource;
import me.pedrazas.drop.services.resources.PingResource;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class WrapApplication extends Application<WrapConfiguration> {

	private ClientConfig clientConfig;

	public static void main(String[] args) throws Exception {
		new WrapApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<WrapConfiguration> bootstrap) {

	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(WrapConfiguration configuration, Environment environment) {
		clientConfig = new ClientConfig();
		clientConfig.addAddress(configuration.getHazelcasrUrl());
		HazelcastInstance client = HazelcastClient
				.newHazelcastClient(clientConfig);

		final PingResource resource = new PingResource();
		final CustomerResource customer = new CustomerResource(client);
		final PingHealthCheck healthCheck = new PingHealthCheck();
		environment.healthChecks().register("ping", healthCheck);
		environment.jersey().register(resource);
		environment.jersey().register(customer);

		configureCors(environment);
	}

	private void configureCors(Environment environment) {
		Dynamic filter = environment.servlets().addFilter("CORS",
				CrossOriginFilter.class);
		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),
				true, "/*");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM,
				"GET,PUT,POST,DELETE,OPTIONS");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
		filter.setInitParameter(
				CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
		filter.setInitParameter("allowedHeaders",
				"Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
		filter.setInitParameter("allowCredentials", "true");
	}
}