package pl.btsoftware.ship.game.playerPosition;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

@Service
@RequiredArgsConstructor
public class PlayerActionService {
    private final PlayerPositionService playerPositionService;

    @Transactional
    public void foundTreasure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.TREASURE);
    }

    @Transactional
    public void acceptTreasure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.TREASURE_ACCEPTED);
    }

    @Transactional
    public void declineTreasure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.TREASURE_DECLINED);
    }

    @Transactional
    public void foundBottle(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.BOTTLE);
    }

    @Transactional
    public void acceptBottle(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.BOTTLE_ACCEPTED);
    }

    @Transactional
    public void declineBottle(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.BOTTLE_DECLINED);
    }

    @Transactional
    public void foundAdventure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.ADVENTURE);
    }

    @Transactional
    public void acceptAdventure(GameName game, PlayerName player) {
        playerPositionService.saveAction(game, player, ActionType.ADVENTURE_ACCEPTED);
    }
}
