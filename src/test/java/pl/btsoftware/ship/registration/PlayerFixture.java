package pl.btsoftware.ship.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionSnapshot;
import pl.btsoftware.ship.registration.RegisterPlayerRestController.RegisterPlayerRequest;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

@Component
@AllArgsConstructor
public class PlayerFixture {
    private final PlayerService playerService;

    public PlayerPositionSnapshot joinPlayer(GameName game, PlayerName player, String playerPassword, String gamePassword) {
        return playerService.joinPlayer(game, new RegisterPlayerRequest(player, playerPassword, gamePassword));
    }
}
