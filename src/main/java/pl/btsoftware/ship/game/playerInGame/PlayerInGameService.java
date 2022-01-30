package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.board.PlayerSituation;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.country.CountryFinder;
import pl.btsoftware.ship.game.goods.GoodsEntity;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.game.playerInGame.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerEntity;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

interface StringId {
    String getId();
}

@Service
@AllArgsConstructor
public class PlayerInGameService {
    private final PlayerInGameRepository playerInGameRepository;

    @Transactional(readOnly = true)
    public PlayerInGameEntity findPlayerInGameEntity(GameName gameName, PlayerName playerName) {
        PlayerInGameEntity playerInGame = playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName);
        if(playerInGame == null) {
            throw new PlayerNotFoundInGameException(gameName, playerName);
        }
        return playerInGame;
    }

    @Transactional(readOnly = true)
    public PlayerInGame findPlayerInGame(GameName gameName, PlayerName playerName) {
        PlayerInGameEntity playerInGame = findPlayerInGameEntity(gameName, playerName);
        return PlayerInGame.from(playerInGame, false);
    }

    @Transactional(readOnly = true)
    public List<PlayerSituation> getActualSituationOnBoard(GameName gameName) {
        List<StringId> lastMoveIds = playerInGameRepository.getIdOfLastMoveForEachPlayerInGame(gameName.getName());
        List<PlayerInGameEntity> lastMoves = playerInGameRepository.findAllById(lastMoveIds.stream().map(ids -> new PlayerInGameId(UUID.fromString(ids.getId()))).collect(Collectors.toList()));
        return lastMoves.stream().map(PlayerSituation::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PositionOnBoard findLastPlayerPosition(GameName gameName, PlayerName playerName) {
        PlayerInGameEntity playerInGame = findPlayerInGameEntity(gameName, playerName);
        return playerInGame.getPositionOnBoard();
    }
}
