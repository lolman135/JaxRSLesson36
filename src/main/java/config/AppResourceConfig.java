package config;

import controller.UserController;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api/v1")
public class AppResourceConfig extends ResourceConfig {

    public AppResourceConfig() {
        register(UserController.class);
//        register(ProductController.class);
        register(ApplicationBinder.class);
    }
}
