package pl.btsoftware.ship.game.playerPosition;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.events.*;
import pl.btsoftware.ship.game.playerPosition.validators.GameValidator;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PlayerMoveService {
    private final PlayerPositionService playerPositionService;
    private final EventsService eventsService;
    private final GameValidator gameValidator;
    private final EventSelector eventSelector;

    @Transactional
    Optional<EventSnapshot.EventDescription> movePlayer(GameName game, PlayerName player, PositionOnBoard newPosition) {
        gameValidator.canMove(game);

        playerPositionService.moveOn(game, player, newPosition);

        Optional<EventSnapshot> event = eventsService.findEvent(newPosition, game);
        event.map(EventSnapshot::eventDescription).ifPresent(e -> eventSelector.found(e.type(), game, player));
        return event.map(EventSnapshot::description);
    }
}
