package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.events.exception.EventNotFoundException;
import pl.btsoftware.ship.game.playerPosition.ActionType;
import pl.btsoftware.ship.game.playerPosition.PlayerActionService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionSnapshot;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

@Service
@AllArgsConstructor
@Slf4j
class BottleService {
    private final EventsService eventsService;
    private final PlayerPositionService playerPositionService;
    private final PlayerActionService playerActionService;

    @Transactional
    EventsService.EventDescription accept(GameName game, PlayerName player) {
        PlayerPositionSnapshot playerPosition = playerPositionService.get(game, player);
        if (playerPosition.lastAction() != ActionType.BOTTLE) {
            throw new EventNotFoundException();
        }
        EventRewardSnapshot reward = eventsService.findReward(playerPosition.position());
        eventsService.removeReward(game, playerPosition.position());
        playerActionService.acceptBottle(game, player);
        playerPositionService.addReward(game, player, reward.removeGold(5));
        return eventsService.findEventDescription(playerPosition.position());
    }

    @Transactional
    void decline(GameName game, PlayerName player) {
        PlayerPositionSnapshot playerPosition = playerPositionService.get(game, player);
        if (playerPosition.lastAction() != ActionType.BOTTLE) {
            throw new EventNotFoundException();
        }
        playerActionService.declineBottle(game, player);
    }

    void found(GameName game, PlayerName player) {
        playerActionService.foundBottle(game, player);
    }
}
