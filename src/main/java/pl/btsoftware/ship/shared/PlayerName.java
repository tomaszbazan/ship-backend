package pl.btsoftware.ship.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class PlayerName {
    @NonNull
    @JsonProperty("name")
    private String player;

    public String value() {
        return player;
    }

    @Override
    public String toString() {
        return value();
    }
}
