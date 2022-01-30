package pl.btsoftware.ship.game.board;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.events.EventField;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.game.exception.GameNotExistsException;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class BoardInformationService {
    private final PlayerInGameService playerInGameService;
    private final EventsService eventsService;

    Board boardCreator(GameName gameName) {
        List<PlayerSituation> actualBoardSituation = actualSituation(gameName);
        List<EventField> eventFields = eventsService.findAllInGame(gameName);
        return BoardCreator.create(actualBoardSituation, eventFields);
    }

    public int fieldMaximumWeight(PositionOnBoard positionOnBoard) {
        Board board = BoardCreator.create(emptyList(), emptyList());
        return board.fieldWeight(positionOnBoard);
    }

    List<PlayerSituation> actualSituation(GameName gameName) {
        List<PlayerSituation> actualPlayersSituation = playerInGameService.getActualSituationOnBoard(gameName);
        if (actualPlayersSituation.isEmpty()) {
            throw new GameNotExistsException(gameName);
        }

        return actualPlayersSituation;
    }
}
