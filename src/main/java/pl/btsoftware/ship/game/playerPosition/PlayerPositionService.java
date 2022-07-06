package pl.btsoftware.ship.game.playerPosition;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.events.EventRewardSnapshot;
import pl.btsoftware.ship.game.goods.GoodsDto;
import pl.btsoftware.ship.game.playerPosition.exception.GameNotFoundException;
import pl.btsoftware.ship.game.playerPosition.exception.PlayerNotFoundInGameException;
import pl.btsoftware.ship.game.playerPosition.validators.MoveCorrectnessValidator;
import pl.btsoftware.ship.game.state.GameStateService;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.shared.Round;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.btsoftware.ship.shared.Round.PREPARING;

@Service
@AllArgsConstructor
public class PlayerPositionService {
    private final PlayerPositionJpaRepository repository;
    private final PlayerGoodsJpaRepository goodsRepository;
    private final GameStateService gameStateService;
    private final ApplicationEventPublisher events;

    @Transactional(readOnly = true)
    public PlayerPositionSnapshot get(GameName gameName, PlayerName playerName) {
        return PlayerPositionSnapshot.from(findLastKnownPosition(gameName, playerName));
    }

    @Transactional(readOnly = true)
    public List<PlayerPositionDto> positionOfAllPlayers(GameName game) {
        return repository.getIdOfLastMoveForEachPlayerInGame(game.value()).stream().map(PlayerPositionRaw::toPlayerSituation)
                .toList().stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(l -> !l.isEmpty())
                .orElseThrow(() -> new GameNotFoundException(game));
    }

    @Transactional
    public void joinPlayer(GameName game, PlayerName player, Country country, List<GoodsDto> startGoods) {
        PlayerPosition playerPosition = repository.save(PlayerPosition.join(game, player, country));
        goodsRepository.saveAll(startGoods.stream().map(goods -> PlayerGoodsEntity.from(goods, playerPosition)).toList());
    }

    void moveOn(GameName game, PlayerName player, PositionOnBoard newPosition) {
        PlayerPosition lastKnownPosition = findLastKnownPosition(game, player);
        MoveCorrectnessValidator.canMoveOn(lastKnownPosition.getPositionOnBoard(), newPosition, lastKnownPosition.shipWeight());
        saveAction(game, player, newPosition, ActionType.MOVE);
    }

    public long numberOfPlayersInGame(GameName game) {
        return repository.countPlayersInRound(game.value(), PREPARING.toString());
    }

    public boolean allPlayersMakeMoveInCurrentRound(GameName game) {
        long noOfPlayersInGame = numberOfPlayersInGame(game);
        Round round = gameStateService.state(game);
        long noOfPlayersInRound = repository.countPlayersInRound(game.getGame(), round.toString());

        return noOfPlayersInRound == noOfPlayersInGame;
    }

    public void addReward(GameName game, PlayerName player, EventRewardSnapshot rewardSnapshot) {
        PlayerPosition lastKnownPosition = findLastKnownPosition(game, player);
        PlayerPosition playerPosition = repository.save(PlayerPosition.action(lastKnownPosition, lastKnownPosition.getPositionOnBoard(), gameStateService.state(game), ActionType.MOVE));
        EventRewardSnapshot reward = rewardSnapshot.addGoods(lastKnownPosition.goodsAsMap());
        goodsRepository.saveAll(reward.goods().entrySet().stream().map(e -> PlayerGoodsEntity.from(new GoodsDto(e.getKey(), e.getValue()), playerPosition)).toList());
    }

    private PlayerPosition findLastKnownPosition(GameName game, PlayerName player) {
        return repository.findFirstByGameAndPlayerOrderByOccurrenceDesc(game, player).orElseThrow(() -> new PlayerNotFoundInGameException(game, player));
    }

    void saveAction(GameName game, PlayerName player, ActionType action) {
        PlayerPosition lastKnownPosition = findLastKnownPosition(game, player);
        Round round = gameStateService.state(lastKnownPosition.getGame());
        PlayerPosition playerPosition = repository.save(PlayerPosition.action(lastKnownPosition, lastKnownPosition.getPositionOnBoard(), round, action));
        copyGoods(lastKnownPosition, playerPosition);
        events.publishEvent(new PlayerMakeAction(game, action));
    }

    private void saveAction(GameName game, PlayerName player, PositionOnBoard newPosition, ActionType action) {
        PlayerPosition lastKnownPosition = findLastKnownPosition(game, player);
        Round round = gameStateService.state(lastKnownPosition.getGame());
        PlayerPosition playerPosition = repository.save(PlayerPosition.action(lastKnownPosition, newPosition, round, action));
        copyGoods(lastKnownPosition, playerPosition);
        events.publishEvent(new PlayerMakeAction(game, action));
    }

    private void copyGoods(PlayerPosition lastKnownPosition, PlayerPosition playerPosition) {
        goodsRepository.saveAll(lastKnownPosition.copyGoods(playerPosition));
    }

    interface PlayerPositionRaw {
        String getPlayer();

        String getCountry();

        Integer getX();

        Integer getY();

        default PlayerPositionDto toPlayerSituation() {
            return new PlayerPositionDto(new PlayerName(getPlayer()), Country.from(getCountry()), getX(), getY());
        }
    }
}
