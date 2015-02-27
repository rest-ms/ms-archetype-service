package ${groupId}.${projectPackage};

import io.dropwizard.client.JerseyClientConfiguration;
import it.siletto.ms.auth.AuthConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppConfiguration extends AuthConfiguration {
	@Valid
	@NotNull
	@JsonProperty
	private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

	public JerseyClientConfiguration getJerseyClientConfiguration() {
		return httpClient;
	}
 }
