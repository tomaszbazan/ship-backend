package pl.btsoftware.ship.game.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
class Field {
    private int x;
    private int y;
    @JsonProperty("max_weight")
    private int maxWeight;
    private SpecialField special;
    @JsonProperty("is_start_point")
    private boolean isStartPoint;
    @JsonProperty("is_end_point")
    private boolean isEndPoint;
}
