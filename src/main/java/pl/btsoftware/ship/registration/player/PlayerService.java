package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.game.playerInGame.PlayerInGame;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.GamePassword;
import pl.btsoftware.ship.registration.game.GameService;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.player.exception.IncorrectPasswordException;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerInGameService playerInGameService;
    private final GameService gameService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    PlayerInGame joinPlayer(GameName gameName, RegisterPlayerRequest registerPlayerRequest) {
        GameEntity game = gameService.findGame(gameName).orElseThrow(() -> new GameNotExistsException(gameName));
        checkGamePassword(game, registerPlayerRequest.getGamePassword());

        PlayerInGame playerInGame = playerInGameService.findPlayerInGame(gameName, new PlayerName(registerPlayerRequest.getTeamName()));
        if(playerInGame != null) {
            return playerInGame;
        }
        PlayerEntity player = playerRepository.save(PlayerEntity.from(registerPlayerRequest, passwordEncoder));
        return playerInGameService.addPlayerToGame(game, player);
    }

    private void checkGamePassword(GameEntity gameEntity, String gamePassword) {
        GamePassword createdGamePassword = gameEntity.getPassword();
        if (!passwordEncoder.matches(gamePassword, createdGamePassword.value())) {
            throw new IncorrectPasswordException(gameEntity.getName());
        }
    }
}
