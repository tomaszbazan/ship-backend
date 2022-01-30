package pl.btsoftware.ship;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class TestController {

    @GetMapping("/")
    public String home() {
        log.info("root");
        return "Hello";
    }
}
