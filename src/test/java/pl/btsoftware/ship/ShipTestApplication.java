package pl.btsoftware.ship;

import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class ShipTestApplication {

	public static void main(String[] args) {
		SpringApplication application = ShipApplication.createSpringApplication();

		application.addInitializers(new IntegrationTest.Initializer());

		application.run(args);
	}
}
