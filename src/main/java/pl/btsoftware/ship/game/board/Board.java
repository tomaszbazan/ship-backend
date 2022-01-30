package pl.btsoftware.ship.game.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
class Board {
    private final List<BoardRow> rows = new ArrayList<>();

    void createRow(List<Field> fields) {
        this.rows.add(new BoardRow(fields));
    }

    public int fieldWeight(PositionOnBoard positionOnBoard) {
        BoardRow boardRow = rows.get(positionOnBoard.getY() - 1);
        return boardRow.fields().get(positionOnBoard.getX() - 1).maxWeight();
    }
}

record BoardRow(List<Field> fields) {
}
