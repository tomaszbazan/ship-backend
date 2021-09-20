package pl.btsoftware.ship.creators;

import pl.btsoftware.ship.game.goods.Goods;
import pl.btsoftware.ship.game.goods.GoodsType;

import java.util.Collections;
import java.util.List;

public class GoodsCreator {
    public static List<Goods> jamaicaStartGoods() {
        return Collections.singletonList(new Goods(GoodsType.CITRUS, 10));
    }
}
