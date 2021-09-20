package pl.btsoftware.ship.game.board;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;

import java.util.List;

@Service
@AllArgsConstructor
class BoardInformationService {
    private final PlayerInGameService playerInGameService;

    ActualBoardSituation actualSituation(GameName gameName) {
        List<PlayerSituation> actualPlayersSituation = playerInGameService.getActualSituationOnBoard(gameName);
        if (actualPlayersSituation.isEmpty()) {
            throw new GameNotExistsException(gameName);
        }
        return new ActualBoardSituation(actualPlayersSituation);
    }
}
