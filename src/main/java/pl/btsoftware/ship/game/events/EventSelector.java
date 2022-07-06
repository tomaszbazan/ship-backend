package pl.btsoftware.ship.game.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.events.exception.AdventureAcceptAutomaticallyException;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventSelector {
    private final BottleService bottleService;
    private final AdventureService adventureService;
    private final TreasureService treasureService;

    public EventsService.EventDescription accept(SpecialFieldKind type, GameName game, PlayerName player) {
        log.info("Accepted: " + type);
        return switch (type) {
            case BOTTLE -> bottleService.accept(game, player);
            case ADVENTURE -> throw new AdventureAcceptAutomaticallyException();
            case TREASURE -> treasureService.accept(game, player);
        };
    }

    public void decline(SpecialFieldKind type, GameName game, PlayerName player) {
        log.info("Decline: " + type);
        switch (type) {
            case BOTTLE -> bottleService.decline(game, player);
            case ADVENTURE -> throw new AdventureAcceptAutomaticallyException();
            case TREASURE -> treasureService.decline(game, player);
        }
    }

    public void found(SpecialFieldKind type, GameName game, PlayerName player) {
        log.info("Found: " + type);
        switch (type) {
            case BOTTLE -> bottleService.found(game, player);
            case ADVENTURE -> adventureService.found(game, player);
            case TREASURE -> treasureService.found(game, player);
        }
    }

}
