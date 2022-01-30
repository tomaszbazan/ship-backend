package pl.btsoftware.ship.game.events;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CardType {
    SNAIL("SNAIL"), BILL_OF_EXCHANGE("BILL_OF_EXCHANGE"), RUM("RUM"), SICK("SICK"),
    CURE("CURE"), EXCHANGE("EXCHANGE"), DEPOSIT("DEPOSIT"), SHOPPING_LIST("SHOPPING_LIST");

    private final String type;

    @JsonValue
    public String getType() {
        return type;
    }
}
