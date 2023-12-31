package kg.hackaton.agrokoombackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass=true)
@OpenAPIDefinition(info = @Info(title = "AgroKoom", version = "1.0", description = "basic functional"),
                    servers = {@Server(url = "/", description = "Default Server URL")})
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class AgrokoomBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgrokoomBackendApplication.class, args);
    }

}
