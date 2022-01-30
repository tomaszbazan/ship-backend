package pl.btsoftware.ship.game.events;

import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.goods.GoodsEntity;
import pl.btsoftware.ship.game.goods.GoodsType;
import pl.btsoftware.ship.game.playerInGame.PlayerNextActionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.btsoftware.ship.game.events.SpecialFieldKind.BOTTLE;

public record EventReward(PositionOnBoard positionOnBoard, List<CardEntity> cards, Map<GoodsType, Integer> goods,
                          PlayerNextActionDto playerNextActionDto) {
    static EventReward from(PositionOnBoard playerPosition, PlayerNextActionDto playerNextAction, EventRewardEntity eventReward) {
        if(eventReward == null) {
            return from(playerPosition, playerNextAction);
        }
        return new EventReward(playerPosition, eventReward.getCards(), eventReward.getGoods().stream().collect(Collectors.toMap(GoodsEntity::getType, GoodsEntity::getAmount)), playerNextAction);
    }

    static EventReward from(PositionOnBoard playerPosition, PlayerNextActionDto playerNextAction) {
        return new EventReward(playerPosition, new ArrayList<>(), new HashMap<>(), playerNextAction);
    }

    static EventReward from(PositionOnBoard playerPosition) {
        return new EventReward(playerPosition, new ArrayList<>(), new HashMap<>(), null);
    }

    public Map<GoodsType, Integer> addGoods(Map<GoodsType, Integer> actualGoods) {
        if (goods == null || goods.isEmpty()) {
            return actualGoods;
        }

        HashMap<GoodsType, Integer> result = new HashMap<>(goods);
        actualGoods.forEach((key, value) -> result.merge(key, value, Integer::sum));
        return result;
    }

    public boolean foundBottle() {
        return playerNextActionDto != null && playerNextActionDto.type() != null && playerNextActionDto.type().equals(BOTTLE);
    }

    public PlayerNextActionDto hideDetails() {
        return new PlayerNextActionDto(null, null, null, BOTTLE);
    }
}
