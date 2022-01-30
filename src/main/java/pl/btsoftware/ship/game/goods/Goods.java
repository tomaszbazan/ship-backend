package pl.btsoftware.ship.game.goods;

public record Goods(GoodsType type, int amount) {
    public static Goods from(GoodsEntity goods) {
        return new Goods(goods.getType(), goods.getAmount());
    }
}
