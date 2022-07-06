package pl.btsoftware.ship.game.playerPosition;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.country.CountryFinder;
import pl.btsoftware.ship.game.goods.GoodsDto;
import pl.btsoftware.ship.game.goods.GoodsService;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerJoinService {
    private final PlayerPositionService playerPositionService;
    private final GoodsService goodsService;

    @Transactional
    public PlayerPositionSnapshot add(GameName game, PlayerName player) {
        Country country = calculateNextFreeCountry(game);
        List<GoodsDto> startGoods = goodsService.findStartGoodsFor(country);
        playerPositionService.joinPlayer(game, player, country, startGoods);

        return playerPositionService.get(game, player);
    }

    private Country calculateNextFreeCountry(GameName game) {
        long numberOfPlayersInGame = playerPositionService.numberOfPlayersInGame(game);
        return CountryFinder.nextFreeCountry(numberOfPlayersInGame);
    }
}
