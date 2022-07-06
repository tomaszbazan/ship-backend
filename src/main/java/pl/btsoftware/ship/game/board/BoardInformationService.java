package pl.btsoftware.ship.game.board;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.events.EventFieldDto;
import pl.btsoftware.ship.game.events.EventsService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionService;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionDto;
import pl.btsoftware.ship.game.state.GameStateService;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PositionOnBoard;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardInformationService {
    private final PlayerPositionService playerPositionService;
    private final EventsService eventsService;
    private final GameStateService stateService;

    Board boardCreator(GameName gameName) {
        List<PlayerPositionDto> actualBoardSituation = playerPositionService.positionOfAllPlayers(gameName);
        List<EventFieldDto> eventFields = eventsService.findAllInGame(gameName);
        return BoardCreator.create(actualBoardSituation, eventFields, stateService.state(gameName));
    }
}
