package ${groupId}.${projectPackage};

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ${groupId}.${projectPackage}.client.${className}Service;
import ${groupId}.${projectPackage}.client.impl.${className}ServiceJpaImpl;
import ${groupId}.${projectPackage}.resources.${className}Resource;
import it.siletto.ms.auth.CredentialsAuthenticator;
import it.siletto.ms.auth.RestrictedToProvider;
import it.siletto.ms.auth.User;
import it.siletto.ms.auth.service.CypherService;
import it.siletto.ms.auth.service.impl.CypherServiceRSAImpl;
import it.siletto.ms.base.cors.CorsFilterFactory;
import it.siletto.ms.base.health.BasicHealthCheck;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class ${className}App extends Application<AppConfiguration> {
    private static AppConfiguration cfg;

    public static AppConfiguration getConfig() {
        return cfg;
    }

    public static void main(String[] args) throws Exception {
        new ${className}App().run(args);
    }

    public ${className}App() {

    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> configBootstrap) {
    }

    @SuppressWarnings("unchecked")
	@Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        cfg = appConfiguration;

        Injector injector = Guice.createInjector(new Module(){
        	@Override
        	public void configure(Binder binder) {
        		binder.bind(CypherService.class).to(CypherServiceRSAImpl.class);
        		binder.bind(${className}Service.class).to(${className}ServiceJpaImpl.class);
        	}
        },
        new JpaPersistModule("mysql")
        );
        
        PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();
        
        CredentialsAuthenticator authenticator = injector.getInstance(CredentialsAuthenticator.class);
        authenticator.setPrivateKeyFile(appConfiguration.getPrivateKeyFile());

        environment.jersey().register(injector.getInstance(${className}Resource.class));

        environment.healthChecks().register("test", new BasicHealthCheck());

        environment.jersey().register(new RestrictedToProvider<User>(authenticator, "MyRealm"));
		
        environment.jersey().getResourceConfig().getResourceFilterFactories().add(new CorsFilterFactory());
                
    }
}




