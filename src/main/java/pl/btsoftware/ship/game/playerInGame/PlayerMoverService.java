package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@Service
@AllArgsConstructor
public class PlayerMoverService {
    private final PlayerInGameRepository playerInGameRepository;

    public void movePlayer(GameName gameName, PlayerName playerName, PositionOnBoard newPosition) {
        PlayerInGameEntity lastKnownPosition = findLastKnownPosition(gameName, playerName);
        if (lastKnownPosition == null) {
            throw new PlayerNotFoundInGameException(gameName, playerName);
        }
        lastKnownPosition.canMoveOn(newPosition);

        PlayerInGameEntity newMove = PlayerInGameEntity.from(lastKnownPosition, newPosition);
        playerInGameRepository.save(newMove);
    }

    private PlayerInGameEntity findLastKnownPosition(GameName gameName, PlayerName playerName) {
        return playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName);
    }
}
