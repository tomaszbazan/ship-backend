package pl.btsoftware.ship.game.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
class Field {
    int x;
    int y;
    @JsonProperty("max_weight")
    int maxWeight;
    SpecialField special;
    @JsonProperty("is_start_point")
    boolean isStartPoint;
    @JsonProperty("is_end_point")
    boolean isEndPoint;
}
