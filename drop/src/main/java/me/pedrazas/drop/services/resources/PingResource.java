package me.pedrazas.drop.services.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import me.pedrazas.drop.services.om.Ping;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
public class PingResource {

	@GET
    @Timed
    public Ping sayPing(@QueryParam("name") Optional<String> name) {        
        return new Ping(1, "Pong");
    }
}
