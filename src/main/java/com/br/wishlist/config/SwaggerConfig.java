package com.br.wishlist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI defineOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");

        Contact contact = new Contact();
        contact.setUrl("https://github.com/robertorsm");
        contact.setName("Roberto Sousa Martins");

        Info info = new Info()
                .title("Wishlist API")
                .description("Serviço de gerenciamento de wishlist")
                .version("1.0")
                .contact(contact);
        return new OpenAPI().info(info).servers(List.of(server));

    }
}
