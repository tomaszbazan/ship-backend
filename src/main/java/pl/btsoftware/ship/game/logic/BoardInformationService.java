package pl.btsoftware.ship.game.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.registration.GameName;
import pl.btsoftware.ship.game.registration.PlayerInGameEntity;
import pl.btsoftware.ship.game.registration.PlayerInGameRepository;

import java.util.List;

@Service
@AllArgsConstructor
class BoardInformationService {
    private final PlayerInGameRepository playerInGameRepository;

    ActualBoardSituation actualSituation(GameName gameName) {
        List<PlayerInGameEntity> playerInGameEntityList = playerInGameRepository.getAllByGame_Name(gameName);
        return new ActualBoardSituation(playerInGameEntityList);
    }
}
