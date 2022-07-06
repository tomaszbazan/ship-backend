package pl.btsoftware.ship.game.state;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.playerPosition.ActionType;
import pl.btsoftware.ship.game.playerPosition.PlayerMakeAction;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionService;

@Service
@AllArgsConstructor
public class NextRoundStateVerifierService {
    private final GameStateService gameStateService;
    private final PlayerPositionService playerPositionService;

    @EventListener
    public void playerMoved(PlayerMakeAction event) {
        if (playerMoved(event.action())) {
            boolean playersMakeMove = playerPositionService.allPlayersMakeMoveInCurrentRound(event.game());
            if (playersMakeMove) {
                gameStateService.nextRound(event.game());
            }
        }
    }

    private boolean playerMoved(ActionType action) {
        return switch (action) {
            case BOTTLE, TREASURE, ADVENTURE -> false;
            default -> true;
        };
    }
}
