package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.board.PlayerSituation;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.country.CountryFinder;
import pl.btsoftware.ship.game.goods.Goods;
import pl.btsoftware.ship.game.goods.GoodsService;
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
    private final GoodsService goodsService;

    public PlayerInGame addPlayerToGame(GameEntity game, PlayerEntity player) {
        long numberOfPlayersInGame = numberOfPlayersInGame(game.getName());
        Country country = CountryFinder.nextFreeCountry(numberOfPlayersInGame);
        List<Goods> startGoods = goodsService.findStartGoodsFor(country);
        PlayerInGameEntity playerInGame = playerInGameRepository.save(new PlayerInGameEntity(player, game, country, startGoods));
        return PlayerInGame.from(playerInGame);
    }

    public PlayerInGame findPlayerInGame(GameName gameName, PlayerName playerName) {
        PlayerInGameEntity playerInGame = playerInGameRepository.getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(gameName, playerName);
        return PlayerInGame.from(playerInGame);
    }

    public long numberOfPlayersInGame(GameName gameName) {
        return playerInGameRepository.countPlayersInGame(gameName.getName());
    }

    @Transactional(readOnly = true)
    public List<PlayerSituation> getActualSituationOnBoard(GameName gameName) {
        List<StringId> lastMoveIds = playerInGameRepository.getIdOfLastMoveForEachPlayerInGame(gameName.getName());
        List<PlayerInGameEntity> lastMoves = playerInGameRepository.findAllById(lastMoveIds.stream().map(ids -> new PlayerInGameId(UUID.fromString(ids.getId()))).collect(Collectors.toList()));
        return lastMoves.stream().map(PlayerSituation::from).collect(Collectors.toList());
    }
}
