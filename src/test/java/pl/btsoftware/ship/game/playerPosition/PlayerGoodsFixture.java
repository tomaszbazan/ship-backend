package pl.btsoftware.ship.game.playerPosition;

import pl.btsoftware.ship.game.goods.GoodsDto;
import pl.btsoftware.ship.game.goods.GoodsType;

import java.util.Collections;
import java.util.List;

public class PlayerGoodsFixture {
    public static List<GoodsDto> jamaicaStartGoods() {
        return Collections.singletonList(new GoodsDto(GoodsType.CITRUS, 10));
    }
}
