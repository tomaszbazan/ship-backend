package pl.btsoftware.ship.game.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class ActualBoardSituation {
    private List<PlayerPosition> players;

    public static ActualBoardSituation from(List<PositionInGame> playersPosition) {
        return new ActualBoardSituation(playersPosition.stream().map(ActualBoardSituation::map).collect(Collectors.toList()));
    }

    private static PlayerPosition map(PositionInGame positionInGame) {
        return PlayerPosition.from(positionInGame);
    }

    public interface PositionInGame {
        String getPlayerName();
        String getCountry();
        Integer getCoordinateX();
        Integer getCoordinateY();
    }
}
