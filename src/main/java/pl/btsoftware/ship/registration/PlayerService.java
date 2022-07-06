package pl.btsoftware.ship.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.playerPosition.PlayerJoinService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionSnapshot;
import pl.btsoftware.ship.game.playerPosition.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.game.state.GameStateService;
import pl.btsoftware.ship.registration.RegisterPlayerRestController.RegisterPlayerRequest;
import pl.btsoftware.ship.registration.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.exception.GameRegistrationClosedException;
import pl.btsoftware.ship.registration.exception.IncorrectPasswordException;
import pl.btsoftware.ship.registration.exception.PlayerAlreadyExistsException;
import pl.btsoftware.ship.shared.GameName;

import java.util.Optional;

@Service
@AllArgsConstructor
class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerJoinService playerJoinService;
    private final PlayerPositionService playerPositionRepository;
    private final GameService gameService;
    private final PasswordChecker passwordChecker;
    private final GameStateService gameStateService;

    @Transactional
    public PlayerPositionSnapshot joinPlayer(GameName gameName, RegisterPlayerRequest registerPlayerRequest) {
        GameEntity game = gameService.findGame(gameName).orElseThrow(() -> new GameNotExistsException(gameName));
        verifyPassword(game, registerPlayerRequest.gamePassword());

        Optional<PlayerEntity> player = playerRepository.findByName(registerPlayerRequest.player());
        if (player.isEmpty()) {
            verifyCanJoin(gameName);
            return createNewAndJoinToGame(gameName, registerPlayerRequest);
        }
        verifyPassword(player.get(), registerPlayerRequest.playerPassword());

        try {
            return playerPositionRepository.get(gameName, registerPlayerRequest.player());
        } catch (PlayerNotFoundInGameException e) {
            throw new PlayerAlreadyExistsException(player.get().playerName());
        }
    }

    private void verifyCanJoin(GameName gameName) {
        if(!gameStateService.canJoinToGame(gameName)) {
            throw new GameRegistrationClosedException(gameName);
        }
    }

    private PlayerPositionSnapshot createNewAndJoinToGame(GameName gameName, RegisterPlayerRequest registerPlayerRequest) {
        PlayerEntity newPlayer = playerRepository.save(PlayerEntity.from(registerPlayerRequest, passwordChecker.encodePassword(registerPlayerRequest.playerPassword())));
        return playerJoinService.add(gameName, newPlayer.playerName());
    }

    private void verifyPassword(PlayerEntity playerEntity, String playerPassword) {
        if (!passwordChecker.checkPassword(playerEntity, playerPassword)) {
            throw new IncorrectPasswordException(playerEntity.playerName());
        }
    }

    private void verifyPassword(GameEntity gameEntity, String gamePassword) {
        if (!passwordChecker.checkPassword(gameEntity, gamePassword)) {
            throw new IncorrectPasswordException(gameEntity.gameName());
        }
    }
}
