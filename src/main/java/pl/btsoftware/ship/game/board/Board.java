package pl.btsoftware.ship.game.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.shared.Round;

import java.util.ArrayList;
import java.util.List;

@Getter
class Board {
    private final List<BoardRow> rows = new ArrayList<>();
    private final Round round;

    public Board(Round round) {
        this.round = round;
    }

    void createRow(List<Field> fields) {
        this.rows.add(new BoardRow(fields));
    }

    int fieldWeight(PositionOnBoard positionOnBoard) {
        BoardRow boardRow = rows.get(positionOnBoard.getY() - 1);
        return boardRow.fields().get(positionOnBoard.getX() - 1).maxWeight();
    }
}

record BoardRow(List<Field> fields) {
}
