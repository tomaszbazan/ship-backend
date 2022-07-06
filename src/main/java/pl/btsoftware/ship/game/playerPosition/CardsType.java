package pl.btsoftware.ship.game.playerPosition;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CardsType {
    SNAIL("Snail"),
    BILL_OF_EXCHANGE("Bill of exchange"),
    RUM("Rum"),
    CURE("cure"),
    EXCHANGE("Exchange"),
    DEPOSIT("Deposit"),
    SHOPPING_LIST("Shopping list"),
    JOKER("Joker");

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }
}
