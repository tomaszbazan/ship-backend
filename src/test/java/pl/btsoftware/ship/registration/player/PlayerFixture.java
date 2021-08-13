package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.btsoftware.ship.registration.game.GameName;

@Component
@AllArgsConstructor
public class PlayerFixture {
    private final PlayerService playerService;

    public void joinTeam(GameName gameName, String teamName, String teamPassword, String gamePassword) {
        playerService.joinPlayer(gameName, new RegisterPlayerRequest(teamName, teamPassword, gamePassword));
    }
}
