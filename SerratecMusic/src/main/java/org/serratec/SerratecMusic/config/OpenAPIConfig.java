package org.serratec.SerratecMusic.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
	
	@Value("${dominio.openapi.dev-url}")
	private String devUrl;
	
	@Value("${dominio.openapi.prod-url}")
	private String prodUrl;

	@Bean
	public OpenAPI myOpenAPI() {

		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Servidor de desenvolvimento");

		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("Servidor de produção");

		Contact contact = new Contact();
		contact.setEmail("contato@serratecmusic.com.br");
		contact.setName("Equipe Serratec Music");
		contact.setUrl("https://www.serratecmusic.com.br");

		License license = new License()
				.name("Apache License 2.0")
				.url("https://www.apache.org/licenses/LICENSE-2.0");

		Info info = new Info()
				.title("API Serratec Music")
				.version("1.0")
				.contact(contact)
				.description("API de gerenciamento de músicas, artistas, playlists e usuários — projeto avaliativo do Serratec.")
				.termsOfService("https://www.serratecmusic.com.br/termos")
				.license(license);

		return new OpenAPI()
				.info(info)
				.servers(List.of(devServer, prodServer));
	}

}
