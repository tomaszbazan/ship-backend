package pl.btsoftware.ship.game.goods;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
enum GoodsType {
    COFFEE("Coffee"),
    COCOA("Cocoa"),
    SUGARCANE("Sugarcane"),
    COTTON("Cotton"),
    TOBACCO("Tobacco"),
    TEA("Tea"),
    VANILLA("Vanilla"),
    CITRUS("Citrus"),
    GOLD("Gold");

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }
}
