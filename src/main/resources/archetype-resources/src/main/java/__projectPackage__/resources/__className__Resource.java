package ${groupId}.${projectPackage}.resources;

import io.dropwizard.jersey.caching.CacheControl;
import ${groupId}.${projectPackage}.client.${className}Service;
import ${groupId}.${projectPackage}.dto.SimpleDTO;
import it.siletto.ms.auth.RestrictedTo;
import it.siletto.ms.auth.User;
import it.siletto.ms.base.cors.Cors;
import it.siletto.ms.base.cors.CorsPreflight;
import it.siletto.ms.base.resources.BaseResource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ${className}Resource extends BaseResource {

	@Inject
	protected ${className}Service ${projectPackage}Service;
	
	@GET
	@Timed
	@CacheControl(noCache = true)
	@Cors
	@Path("/hello/{name}")
	public SimpleDTO hello(/*@RestrictedTo("user") User user, */@PathParam("name") String name) {
		
		return ${projectPackage}Service.sayHello(name);
	}
	
	@OPTIONS
	@CorsPreflight(headers="Authentication")
	public void preflight(){}



}
