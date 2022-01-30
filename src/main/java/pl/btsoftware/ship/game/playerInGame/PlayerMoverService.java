package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.events.EventReward;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.game.events.SpecialFieldKind;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.game.playerInGame.validators.MoveCorrectnessValidator;
import pl.btsoftware.ship.game.playerInGame.validators.WeightCorrectnessValidator;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

import static pl.btsoftware.ship.game.events.SpecialFieldKind.*;

@Service
@AllArgsConstructor
@Slf4j
public class PlayerMoverService {
    private final PlayerInGameRepository playerInGameRepository;
    private final EventsService eventsService;
    private final WeightCorrectnessValidator weightCorrectnessValidator;
    private final GoodsService goodsService;

    @Transactional
    public PlayerNextActionDto movePlayer(GameName gameName, PlayerName playerName, PositionOnBoard newPosition) {
        PlayerInGameEntity lastKnownPosition = findPlayerPositionInGame(gameName, playerName);

        MoveCorrectnessValidator.canMoveOn(lastKnownPosition.getPositionOnBoard(), newPosition);
        weightCorrectnessValidator.canMoveOn(newPosition, lastKnownPosition.shipWeight());

        PlayerInGameEntity player = playerInGameRepository.save(PlayerInGameEntity.from(lastKnownPosition, newPosition));
        goodsService.saveAllEntity(lastKnownPosition.goodsAsMap(), player);

        EventReward event = eventsService.findEvent(newPosition, gameName);

        if (event.foundBottle()) {
            return event.hideDetails();
        } else {
            return event.playerNextActionDto();
        }
    }

    private PlayerInGameEntity findPlayerPositionInGame(GameName gameName, PlayerName playerName) {
        PlayerInGameEntity lastKnownPosition = findLastKnownPosition(gameName, playerName);
        if (lastKnownPosition == null) {
            throw new PlayerNotFoundInGameException(gameName, playerName);
        }
        return lastKnownPosition;
    }

    private PlayerInGameEntity findLastKnownPosition(GameName gameName, PlayerName playerName) {
        return playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName);
    }
}
