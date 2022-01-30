package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.btsoftware.ship.game.playerInGame.PlayerInGame;
import pl.btsoftware.ship.registration.game.GameName;

@Component
@AllArgsConstructor
public class PlayerFixture {
    private final PlayerService playerService;

    public PlayerInGame joinPlayer(GameName gameName, String playerName, String playerPassword, String gamePassword) {
        return playerService.joinPlayer(gameName, new RegisterPlayerRequest(playerName, playerPassword, gamePassword));
    }
}
