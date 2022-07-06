package pl.btsoftware.ship.game.goods;

public record GoodsDto(GoodsType type, int amount) {
    public static GoodsDto from(GoodsStartEntity goods) {
        return new GoodsDto(goods.getType(), goods.getAmount());
    }
}
