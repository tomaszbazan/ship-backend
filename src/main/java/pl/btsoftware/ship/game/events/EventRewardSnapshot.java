package pl.btsoftware.ship.game.events;

import pl.btsoftware.ship.game.goods.GoodsType;
import pl.btsoftware.ship.shared.PositionOnBoard;

import java.util.*;
import java.util.stream.Collectors;

public record EventRewardSnapshot(PositionOnBoard positionOnBoard, List<EventCardEntity> cards,
                                  Map<GoodsType, Integer> goods) {
    static EventRewardSnapshot from(EventRewardEntity eventReward) {
        return new EventRewardSnapshot(eventReward.getPositionOnBoard(), null, eventReward.getGoods().stream().collect(Collectors.toMap(EventGoodsEntity::getType, EventGoodsEntity::getAmount)));
    }

    static EventRewardSnapshot from(PositionOnBoard playerPosition) {
        return new EventRewardSnapshot(playerPosition, new ArrayList<>(), new HashMap<>());
    }

    public EventRewardSnapshot addGoods(Map<GoodsType, Integer> actualGoods) {
        if (goods == null || goods.isEmpty()) {
            return new EventRewardSnapshot(positionOnBoard, cards, actualGoods);
        }

        EnumMap<GoodsType, Integer> result = new EnumMap<>(goods);
        actualGoods.forEach((key, value) -> result.merge(key, value, Integer::sum));
        return new EventRewardSnapshot(positionOnBoard, cards, result);
    }

    EventRewardSnapshot removeGold(Integer amount) {
        Integer gold = goods.getOrDefault(GoodsType.GOLD, 0);
        goods.put(GoodsType.GOLD, gold - amount);
        return new EventRewardSnapshot(positionOnBoard, cards, goods);
    }
}
