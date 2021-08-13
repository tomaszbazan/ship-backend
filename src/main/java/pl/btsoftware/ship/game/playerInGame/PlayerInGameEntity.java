package pl.btsoftware.ship.game.playerInGame;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.playerInGame.exception.InvalidStartPointException;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.goods.GoodsEntity;
import pl.btsoftware.ship.game.playerInGame.exception.InvalidMoveException;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerEntity;
import pl.btsoftware.ship.registration.player.PlayerName;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.Clock.*;

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

    @OneToMany
    @JoinTable(name = "player_in_game_goods",
            joinColumns = @JoinColumn(name = "player_in_game_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<GoodsEntity> goodsList;

    @Column(nullable = false)
    private LocalDateTime occurrence;

    PlayerInGameEntity(PlayerEntity player, GameEntity game, Country country) {
        this.id = PlayerInGameId.newId();
        this.player = player;
        this.game = game;
        this.country = country;
        this.occurrence = LocalDateTime.now(systemUTC());
    }

    public static PlayerInGameEntity from(PlayerInGameEntity entity, PositionOnBoard coordinates) {
        PlayerInGameEntity playerInGame = new PlayerInGameEntity();
        playerInGame.setId(PlayerInGameId.newId());
        playerInGame.setPlayer(entity.getPlayer());
        playerInGame.setGame(entity.getGame());
        playerInGame.setCountry(entity.getCountry());
        playerInGame.setPositionOnBoard(coordinates);
        playerInGame.setOccurrence(LocalDateTime.now(systemUTC()));

        return playerInGame;
    }

    public void canMoveOn(PositionOnBoard newPosition) {
        if (firstMove() && !newPosition.startLine()) {
            throw new InvalidStartPointException();
        }
        if (!firstMove() && (incorrectDistance(newPosition) || getPositionOnBoard().equals(newPosition))) {
            throw new InvalidMoveException();
        }
    }

    private boolean incorrectDistance(PositionOnBoard newPosition) {
        return calculateDistance(positionOnBoard.getX(), newPosition.getX()) > 1 || calculateDistance(positionOnBoard.getY(), newPosition.getY()) > 1;
    }

    private int calculateDistance(int startPosition, int newPosition) {
        return Math.abs(startPosition - newPosition);
    }

    private boolean firstMove() {
        return getPositionOnBoard() == null;
    }

    public PlayerName getPlayerName() {
        return player.getName();
    }

    public GameName getGameName() {
        return game.getName();
    }
}
