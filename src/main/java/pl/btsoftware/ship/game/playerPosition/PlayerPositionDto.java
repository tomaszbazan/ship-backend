package pl.btsoftware.ship.game.playerPosition;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.shared.PlayerName;

public record PlayerPositionDto(
        @JsonProperty("player_name") PlayerName playerName,
        Country country,
        PositionOnBoard coordinates) {

    public PlayerPositionDto(PlayerName playerName, Country country) {
        this(playerName, country, null);
    }

    PlayerPositionDto(PlayerName playerName, Country country, Integer x, Integer y) {
        this(playerName, country, createCoordinatesIfNonOfThemAreNull(x, y));
    }

    private static PositionOnBoard createCoordinatesIfNonOfThemAreNull(Integer x, Integer y) {
        return x == null || y == null ? null : new PositionOnBoard(x, y);
    }
}
