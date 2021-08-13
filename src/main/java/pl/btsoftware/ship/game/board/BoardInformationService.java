package pl.btsoftware.ship.game.board;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class BoardInformationService {
    private final PlayerInGameService playerInGameService;

    @Transactional(readOnly = true)
    ActualBoardSituation actualSituation(GameName gameName) {
        List<ActualBoardSituation.PositionInGame> playersActualPosition = playerInGameService.getLastMoveForEachPlayerInGame(gameName);
        if (playersActualPosition.isEmpty()) {
            throw new GameNotExistsException(gameName);
        }
        return ActualBoardSituation.from(playersActualPosition);
    }

    @Transactional(readOnly = true)
    ActualBoardSituation actualSituation(GameName gameName, PlayerName playerName) {
        ActualBoardSituation actualBoardSituation = actualSituation(gameName);
        return new ActualBoardSituation(removeAllPlayersExcept(playerName, actualBoardSituation));
    }

    private List<PlayerPosition> removeAllPlayersExcept(PlayerName playerName, ActualBoardSituation actualBoardSituation) {
        return actualBoardSituation.getPlayers().stream().filter(player -> player.getPlayerName().equals(playerName.getName())).collect(Collectors.toList());
    }
}
