package pl.btsoftware.ship.game.goods;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GoodsCreator {
    public static List<GoodsDto> jamaicaStartGoods() {
        return Collections.singletonList(new GoodsDto(GoodsType.CITRUS, 10));
    }

    public static Map<GoodsType, Integer> goodsMap(GoodsType goodsType, int amount) {
        return Collections.singletonMap(goodsType, amount);
    }
}
