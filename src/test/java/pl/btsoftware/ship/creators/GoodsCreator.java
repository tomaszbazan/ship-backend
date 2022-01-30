package pl.btsoftware.ship.creators;

import pl.btsoftware.ship.game.goods.Goods;
import pl.btsoftware.ship.game.goods.GoodsEntity;
import pl.btsoftware.ship.game.goods.GoodsType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GoodsCreator {
    public static List<GoodsEntity> jamaicaStartGoodsEntity() {
        return Collections.singletonList(new GoodsEntity(UUID.randomUUID(), GoodsType.CITRUS, 10));
    }

    public static List<Goods> jamaicaStartGoods() {
        return Collections.singletonList(new Goods(GoodsType.CITRUS, 10));
    }

    public static List<GoodsEntity> goodsEntity(GoodsType goodsType, int amount) {
        return Collections.singletonList(new GoodsEntity(UUID.randomUUID(), goodsType, amount));
    }

    public static Map<GoodsType, Integer> goodsMap(GoodsType goodsType, int amount) {
        return Collections.singletonMap(goodsType, amount);
    }
}
