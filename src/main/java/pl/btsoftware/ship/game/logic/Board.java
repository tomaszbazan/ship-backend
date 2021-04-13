package pl.btsoftware.ship.game.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
class Board {
    private List<Field> fields = new ArrayList<>();

    void addFields(List<Field> fields) {
        this.fields.addAll(fields);
    }
}
