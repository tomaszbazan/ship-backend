package pl.btsoftware.ship.game.goods;

public record Goods(GoodsType type, int amount) {
    public static Goods from(GoodsEntity goodsEntity) {
        return new Goods(goodsEntity.getType(), goodsEntity.getAmount());
    }
}
