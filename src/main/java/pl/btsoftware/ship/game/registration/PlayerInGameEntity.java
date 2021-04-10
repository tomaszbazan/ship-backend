package pl.btsoftware.ship.game.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Country country;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "position_on_board_x")),
            @AttributeOverride(name = "y", column = @Column(name = "position_on_board_y"))
    })
    private PositionOnBoard positionOnBoard;

    PlayerInGameEntity(PlayerEntity player, GameEntity game, Country country) {
        this.id = new PlayerInGameId();
        this.player = player;
        this.game = game;
        this.country = country;
    }
}
