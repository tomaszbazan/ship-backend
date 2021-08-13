package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.board.ActualBoardSituation;
import pl.btsoftware.ship.game.country.CountryFinder;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerEntity;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerInGameService {
    private final PlayerInGameRepository playerInGameRepository;

    public PlayerInGame addPlayerToGame(GameEntity game, PlayerEntity player) {
        long numberOfPlayersInGame = numberOfPlayersInGame(game.getName());
        Country country = CountryFinder.nextFreeCountry(numberOfPlayersInGame);
        PlayerInGameEntity playerInGame = playerInGameRepository.save(new PlayerInGameEntity(player, game, country));
        return PlayerInGame.from(playerInGame);
    }

    public PlayerInGame findPlayerInGame(GameName gameName, PlayerName playerName) {
        PlayerInGameEntity playerInGame = playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName);
        return PlayerInGame.from(playerInGame);
    }

    public long numberOfPlayersInGame(GameName gameName) {
        return playerInGameRepository.countPlayersInGame(gameName.getName());
    }

    public List<ActualBoardSituation.PositionInGame> getLastMoveForEachPlayerInGame(GameName gameName) {
        return playerInGameRepository.getLastMoveForEachPlayerInGame(gameName.getName());
    }
}
