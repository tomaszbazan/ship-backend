package pl.btsoftware.ship.game.playerInGame;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.events.CardEntity;
import pl.btsoftware.ship.game.events.EventReward;
import pl.btsoftware.ship.game.goods.GoodsEntity;
import pl.btsoftware.ship.game.goods.GoodsType;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerEntity;
import pl.btsoftware.ship.registration.player.PlayerName;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.Clock.systemUTC;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

@Entity(name = "player_in_game")
@Data
@NoArgsConstructor
public class PlayerInGameEntity {
    @EmbeddedId
    @Column(nullable = false)
    private PlayerInGameId id;

    @OneToOne
    private PlayerEntity player;

    @OneToOne
    private GameEntity game;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "position_on_board_x")),
            @AttributeOverride(name = "y", column = @Column(name = "position_on_board_y"))
    })
    private PositionOnBoard positionOnBoard;

    @OneToMany(mappedBy = "playerInGame", fetch = FetchType.EAGER)
    private List<GoodsEntity> goods = new ArrayList<>();

    @OneToMany(mappedBy = "playerInGame")
    private List<CardEntity> cards = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime occurrence;

    PlayerInGameEntity(PlayerEntity player, GameEntity game, Country country) {
        this.id = PlayerInGameId.newId();
        this.player = player;
        this.game = game;
        this.country = country;
        this.occurrence = now(systemUTC());
    }

    public static PlayerInGameEntity from(PlayerInGameEntity entity, PositionOnBoard newPosition) {
        PlayerInGameEntity playerInGame = new PlayerInGameEntity();
        playerInGame.setId(PlayerInGameId.newId());
        playerInGame.setPlayer(entity.getPlayer());
        playerInGame.setGame(entity.getGame());
        playerInGame.setCountry(entity.getCountry());
        playerInGame.setPositionOnBoard(newPosition);
        playerInGame.setOccurrence(now(systemUTC()));

        return playerInGame;
    }

    public PlayerName getPlayerName() {
        return player.getName();
    }

    public GameName getGameName() {
        return game.getName();
    }

    public Map<GoodsType, Integer> goodsAsMap() {
        return getGoods().stream().collect(Collectors.toMap(GoodsEntity::getType, GoodsEntity::getAmount));
    }

    Integer shipWeight() {
        return getGoods().stream().filter(GoodsEntity::isNotGold).mapToInt(GoodsEntity::getAmount).sum();
    }
}
