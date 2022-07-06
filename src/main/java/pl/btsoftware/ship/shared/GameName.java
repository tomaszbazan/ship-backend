package pl.btsoftware.ship.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class GameName implements Serializable {
    @NonNull
    @JsonProperty("name")
    private String game;

    public String value() {
        return game;
    }

    @Override
    public String toString() {
        return value();
    }
}
