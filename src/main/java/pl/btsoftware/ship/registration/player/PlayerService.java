package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.playerInGame.PlayerInGame;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.game.playerInGame.PlayerJoinToGameService;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.GameService;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.player.exception.IncorrectPasswordException;
import pl.btsoftware.ship.registration.player.exception.PlayerAlreadyExistsException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerJoinToGameService playerJoinToGameService;
    private final PlayerInGameService playerInGameService;
    private final GameService gameService;
    private final PasswordChecker passwordChecker;

    @Transactional
    PlayerInGame joinPlayer(GameName gameName, RegisterPlayerRequest registerPlayerRequest) {
        GameEntity game = gameService.findGame(gameName).orElseThrow(() -> new GameNotExistsException(gameName));
        verifyPassword(game, registerPlayerRequest.gamePassword());

        Optional<PlayerEntity> player = playerRepository.findByName(registerPlayerRequest.player());
        if (player.isEmpty()) {
            PlayerEntity newPlayer = playerRepository.save(PlayerEntity.from(registerPlayerRequest, passwordChecker.encodePassword(registerPlayerRequest.playerPassword())));
            return playerJoinToGameService.add(game, newPlayer);
        }
        verifyPassword(player.get(), registerPlayerRequest.playerPassword());

        try {
            return playerInGameService.findPlayerInGame(gameName, registerPlayerRequest.player());
        } catch (PlayerNotFoundInGameException e) {
            throw new PlayerAlreadyExistsException(player.get().getName());
        }
    }

    private void verifyPassword(PlayerEntity playerEntity, String playerPassword) {
        if (!passwordChecker.checkPassword(playerEntity, playerPassword)) {
            throw new IncorrectPasswordException(playerEntity.getName());
        }
    }

    private void verifyPassword(GameEntity gameEntity, String gamePassword) {
        if (!passwordChecker.checkPassword(gameEntity, gamePassword)) {
            throw new IncorrectPasswordException(gameEntity.getName());
        }
    }
}
