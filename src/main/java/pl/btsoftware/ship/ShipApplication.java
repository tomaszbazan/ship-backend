package pl.btsoftware.ship;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class ShipApplication {

	public static void main(String[] args) {
		createSpringApplication().run(args);
	}

	public static SpringApplication createSpringApplication() {
		return new SpringApplication(ShipApplication.class);
	}
}
