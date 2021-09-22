package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.events.EventInGameEntity;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

@Service
@AllArgsConstructor
public class PlayerMoverService {
    private final PlayerInGameRepository playerInGameRepository;
    private final EventsService eventsService;

    public PlayerNextActionDto movePlayer(GameName gameName, PlayerName playerName, PositionOnBoard newPosition) {
        PlayerInGameEntity lastKnownPosition = findLastKnownPosition(gameName, playerName);
        if (lastKnownPosition == null) {
            throw new PlayerNotFoundInGameException(gameName, playerName);
        }
        lastKnownPosition.canMoveOn(newPosition);

        EventInGameEntity event = eventsService.getEvent(newPosition, gameName); // TODO: analyze action
        PlayerInGameEntity newMove = PlayerInGameEntity.from(lastKnownPosition, newPosition);
        playerInGameRepository.save(newMove);
        return PlayerNextActionDto.from(event);
    }

    private PlayerInGameEntity findLastKnownPosition(GameName gameName, PlayerName playerName) {
        return playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName);
    }
}
