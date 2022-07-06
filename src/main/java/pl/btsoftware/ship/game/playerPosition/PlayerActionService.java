package pl.btsoftware.ship.game.playerPosition;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

@Service
@RequiredArgsConstructor
public class PlayerActionService {
    private final PlayerPositionService playerPositionService;

    public void foundTreasure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.TREASURE);
    }

    public void acceptTreasure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.TREASURE_ACCEPTED);
    }

    public void declineTreasure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.TREASURE_DECLINED);
    }

    public void foundBottle(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.BOTTLE);
    }

    public void acceptBottle(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.BOTTLE_ACCEPTED);
    }

    public void declineBottle(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.BOTTLE_DECLINED);
    }

    public void foundAdventure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.ADVENTURE);
    }

    public void acceptAdventure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.ADVENTURE_ACCEPTED);
    }
}
