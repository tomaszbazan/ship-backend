package pl.btsoftware.ship.game.board;

import pl.btsoftware.ship.shared.PositionOnBoard;

public class WeightInformationChecker {
    public static int fieldMaximumWeight(PositionOnBoard positionOnBoard) {
        var board = BoardCreator.create();
        return board.fieldWeight(positionOnBoard);
    }
}
