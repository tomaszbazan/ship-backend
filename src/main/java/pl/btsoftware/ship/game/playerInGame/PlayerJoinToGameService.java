package pl.btsoftware.ship.game.playerInGame;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.country.CountryFinder;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerEntity;

@Service
@AllArgsConstructor
@Slf4j
public class PlayerJoinToGameService {
    private final PlayerInGameRepository playerInGameRepository;
    private final GoodsService goodsService;

    @Transactional
    public PlayerInGame add(GameEntity game, PlayerEntity player) {
        long numberOfPlayersInGame = numberOfPlayersInGame(game.getName());
        Country country = CountryFinder.nextFreeCountry(numberOfPlayersInGame);
        PlayerInGameEntity playerInGame = playerInGameRepository.save(new PlayerInGameEntity(player, game, country));
        goodsService.createStartGoodsFor(country, playerInGame);

        return PlayerInGame.from(playerInGame, true);
    }

    private long numberOfPlayersInGame(GameName gameName) {
        return playerInGameRepository.countPlayersInGame(gameName.getName());
    }
}
