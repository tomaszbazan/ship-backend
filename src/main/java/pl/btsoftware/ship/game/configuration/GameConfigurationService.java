package pl.btsoftware.ship.game.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.registration.GameCreated;

@Service
@AllArgsConstructor
public class GameConfigurationService {
    private final GameConfigurationRepository gameConfigurationRepository;

    @EventListener
    public void handleGameCreated(GameCreated event) {
        gameConfigurationRepository.save(GameConfigurationEntity.from(event));
    }
}
