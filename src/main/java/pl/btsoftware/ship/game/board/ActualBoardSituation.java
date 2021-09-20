package pl.btsoftware.ship.game.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameEntity;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class ActualBoardSituation {
    private List<PlayerSituation> players;

    public static ActualBoardSituation from(List<PlayerInGameEntity> playersInGame) {
        return new ActualBoardSituation(playersInGame.stream().map(ActualBoardSituation::map).collect(Collectors.toList()));
    }

    private static PlayerSituation map(PlayerInGameEntity positionInGame) {
        return PlayerSituation.from(positionInGame);
    }
}
