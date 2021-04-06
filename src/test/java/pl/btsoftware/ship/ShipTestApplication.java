package pl.btsoftware.ship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShipTestApplication {

	public static void main(String[] args) {
		SpringApplication application = ShipApplication.createSpringApplication();

		application.addInitializers(new IntegrationTestConfiguration.Initializer());

		application.run(args);
	}
}
