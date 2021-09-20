package pl.btsoftware.ship.game.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
class Board {
    private final List<BoardRow> rows = new ArrayList<>();

    void createRow(List<Field> fields) {
        this.rows.add(new BoardRow(fields));
    }
}

record BoardRow(List<Field> fields) {
}
