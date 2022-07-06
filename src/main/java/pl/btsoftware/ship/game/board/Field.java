package pl.btsoftware.ship.game.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.events.SpecialFieldKind;

import java.util.List;

record Field(int x, int y, @JsonProperty("max_weight") int maxWeight, SpecialField event, List<Player> players,
             @JsonProperty("is_start_point") boolean isStartPoint, @JsonProperty("is_end_point") boolean isEndPoint) {
    record Player(@JsonProperty("name") String playerName, Country country) {
    }
    record SpecialField(SpecialFieldKind kind) {
    }
}
