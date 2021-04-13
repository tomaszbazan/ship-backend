package pl.btsoftware.ship.game.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.registration.Country;
import pl.btsoftware.ship.game.registration.PositionOnBoard;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PlayerPosition {
    @JsonProperty("player_name")
    private String playerName;
    private Country country;
    private PositionOnBoard coordinates;
}
