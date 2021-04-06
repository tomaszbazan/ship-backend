package pl.btsoftware.ship;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/{asdf:.+}")
    public String home(@PathVariable String asdf) {
        return "Hello: " + asdf;
    }
}
