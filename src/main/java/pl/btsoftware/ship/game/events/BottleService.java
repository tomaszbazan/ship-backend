package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.events.exception.BottleEventNotFoundException;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.game.playerInGame.PlayerNextActionDto;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

import static pl.btsoftware.ship.game.events.SpecialFieldKind.BOTTLE;

@Service
@AllArgsConstructor
@Slf4j
public class BottleService {
    private final EventRewardRepository eventRewardRepository;
    private final EventsInGameRepository eventsInGameRepository;
    private final PlayerInGameService playerInGameService;
    private final GoodsService goodsService;

    @Transactional
    PlayerNextActionDto accept(GameName gameName, PlayerName playerName) {
        PositionOnBoard playerPosition = playerInGameService.findLastPlayerPosition(gameName, playerName);
        EventInGameEntity event = findEvent(gameName, playerPosition);
        eventsInGameRepository.delete(event);
        EventRewardEntity reward = eventRewardRepository.findByPositionOnBoard(playerPosition);
        PlayerNextActionDto playerNextAction = new PlayerNextActionDto(event.getTitle(), event.getDescription(), event.getNextAction(), event.getType());
        EventReward eventReward = EventReward.from(playerPosition, playerNextAction, reward);
        goodsService.addGoods(gameName, playerName, eventReward);
        return playerNextAction;
    }

    private EventInGameEntity findEvent(GameName gameName, PositionOnBoard playerPosition) {
        EventInGameEntity event = eventsInGameRepository.findByIdAndGame_Name(FieldId.from(playerPosition), gameName);
        if (event == null || !event.getType().equals(BOTTLE)) {
            throw new BottleEventNotFoundException();
        }
        return event;
    }
}
