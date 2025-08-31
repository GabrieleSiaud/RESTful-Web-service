package lt.viko.eif.gsiaudvytyte.TreciasDarbas1.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info =
@Info(title = "My REST API", version = "1.0.0",
    description = "My OpenAPIDefinition description"),
    servers = {
        @Server(url = "http://localhost:8000", description = "Default URL")
        })
public class OpenAPIConfig {
}
