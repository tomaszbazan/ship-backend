package pl.btsoftware.ship.game.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
class SpecialField {
    private SpecialFieldKind kind;
    private int number;
}
