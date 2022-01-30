package pl.btsoftware.ship.game.goods;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.events.EventReward;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameEntity;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameService;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GoodsService {
    private final GoodsStartRepository goodsStartRepository;
    private final GoodsRepository goodsRepository;
    private final PlayerInGameService playerInGameService;

    @Transactional
    public List<GoodsEntity> createStartGoodsFor(Country country, PlayerInGameEntity player) {
        List<Goods> goods = goodsStartRepository.findFirstByCountry(country)
                .getGoods()
                .stream()
                .map(Goods::from)
                .collect(Collectors.toList());

        return saveAll(goods, player);
    }

    public void addGoods(GameName gameName, PlayerName playerName, EventReward reward) {
        PlayerInGameEntity playerInGame = playerInGameService.findPlayerInGameEntity(gameName, playerName);
        Map<GoodsType, Integer> newGoods = reward.addGoods(playerInGame.goodsAsMap());

        saveAllEntity(newGoods, playerInGame);
    }

    public void analyzeGoodsInEvent(PlayerInGameEntity lastKnownPosition, PlayerInGameEntity newMove, EventReward reward) {
        Map<GoodsType, Integer> newGoods = reward.addGoods(lastKnownPosition.goodsAsMap());

        saveAllEntity(newGoods, newMove);
    }

    public void analyzeGoodsInEvent(PlayerInGameEntity playerInGame, EventReward reward) {
        Map<GoodsType, Integer> newGoods = reward.addGoods(playerInGame.goodsAsMap());

        saveAllEntity(newGoods, playerInGame);
    }

    public List<GoodsEntity> saveAllEntity(Map<GoodsType, Integer> goods, PlayerInGameEntity player) {
        return goodsRepository.saveAll(GoodsEntity.copyWith(goods, player));
    }

    private List<GoodsEntity> saveAll(List<Goods> goods, PlayerInGameEntity player) {
        return goodsRepository.saveAll(goods.stream().map(goods1 -> GoodsEntity.from(goods1, player)).collect(Collectors.toList()));
    }
}
