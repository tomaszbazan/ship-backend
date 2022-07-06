package pl.btsoftware.ship.game.playerPosition;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.goods.GoodsDto;
import pl.btsoftware.ship.game.goods.GoodsType;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;
import pl.btsoftware.ship.shared.PositionOnBoard;
import pl.btsoftware.ship.shared.Round;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.Clock.systemUTC;
import static java.time.Instant.now;
import static pl.btsoftware.ship.game.playerPosition.ActionType.*;
import static pl.btsoftware.ship.shared.Round.PREPARING;

@Entity(name = "player_position")
@Data
@NoArgsConstructor
class PlayerPosition {
    @EmbeddedId
    @Column(nullable = false)
    private PlayerPositionId id;

    @Embedded
    @Column(nullable = false)
    private PlayerName player;

    @Embedded
    @Column(nullable = false)
    private GameName game;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @Embedded
    private PositionOnBoard positionOnBoard;

    @OneToMany(mappedBy = "playerPosition")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PlayerGoodsEntity> goods = new ArrayList<>();

    @OneToMany(mappedBy = "playerPosition")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PlayerCardsEntity> cards = new ArrayList<>();

    @Column(nullable = false)
    private Instant occurrence;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionType action;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Round round;

    static PlayerPosition join(GameName game, PlayerName player, Country country) {
        PlayerPosition playerInGame = new PlayerPosition();
        playerInGame.setId(PlayerPositionId.newId());
        playerInGame.setGame(game);
        playerInGame.setPlayer(player);
        playerInGame.setCountry(country);
        playerInGame.setOccurrence(now(systemUTC()));
        playerInGame.setAction(JOIN);
        playerInGame.setRound(PREPARING);

        return playerInGame;
    }

    static PlayerPosition action(PlayerPosition lastPosition, PositionOnBoard newPosition, Round round, ActionType action) {
        PlayerPosition playerInGame = from(lastPosition, newPosition);
        playerInGame.setAction(action);
        playerInGame.setRound(round);

        return playerInGame;
    }

    private static PlayerPosition from(PlayerPosition lastPosition, PositionOnBoard newPosition) {
        PlayerPosition playerInGame = new PlayerPosition();
        playerInGame.setId(PlayerPositionId.newId());
        playerInGame.setPlayer(lastPosition.getPlayer());
        playerInGame.setGame(lastPosition.getGame());
        playerInGame.setCountry(lastPosition.getCountry());
        playerInGame.setPositionOnBoard(newPosition);
        playerInGame.setOccurrence(now(systemUTC()));

        return playerInGame;
    }

    Integer shipWeight() {
        return goods.stream().filter(PlayerGoodsEntity::isNotGold).mapToInt(PlayerGoodsEntity::getAmount).sum();
    }

    Map<GoodsType, Integer> goodsAsMap() {
        return getGoods().stream().collect(Collectors.toMap(PlayerGoodsEntity::getType, PlayerGoodsEntity::getAmount));
    }

    List<PlayerGoodsEntity> copyGoods(PlayerPosition playerPosition) {
        return goods.stream().map(g -> PlayerGoodsEntity.copy(g, playerPosition)).toList();
    }

    List<GoodsDto> goodsDtos() {
        return goods.stream().map(g -> new GoodsDto(g.getType(), g.getAmount())).toList();
    }

    boolean firstMove() {
        return JOIN.equals(action);
    }

    @Embeddable
    @ToString
    static class PlayerPositionId implements Serializable {
        @NonNull
        private UUID id;

        public PlayerPositionId() {
            this.id = UUID.randomUUID();
        }

        static PlayerPositionId newId() {
            return new PlayerPositionId();
        }
    }
}
