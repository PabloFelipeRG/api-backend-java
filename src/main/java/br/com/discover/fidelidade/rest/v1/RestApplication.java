package br.com.discover.fidelidade.rest.v1;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.discover.fidelidade.rest.v1.resource.EnderecoResource;
import br.com.discover.fidelidade.rest.v1.resource.UsuarioResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("/v1")
public class RestApplication extends Application {

	private Set<Object> resources = new HashSet<Object>();
	
	public RestApplication() {
		
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle("Fidelidade API");
		beanConfig.setVersion("v1");
		beanConfig.setSchemes(new String[]{"http"});
		beanConfig.setBasePath("/fidelidade-api");
		beanConfig.setResourcePackage("br.com.discover.fidelidade.rest.v1");
		beanConfig.setScan(true);
		
		resources.add(new ApiListingResource());
        resources.add(new SwaggerSerializers());
        resources.add(new ExceptionHandler());
        resources.add(new UsuarioResource());
        resources.add(new EnderecoResource());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return resources;
	}
}
