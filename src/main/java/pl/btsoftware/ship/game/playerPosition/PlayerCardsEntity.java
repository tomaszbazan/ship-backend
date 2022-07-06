package pl.btsoftware.ship.game.playerPosition;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import pl.btsoftware.ship.game.goods.GoodsType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "player_cards")
@Data
@NoArgsConstructor
class PlayerCardsEntity {
    @EmbeddedId
    @Column(nullable = false)
    private PlayerCardsId id;

    @ManyToOne
    @JoinColumn(name = "player_position_id", nullable = false)
    private PlayerPosition playerPosition;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardsType type;

    @Column(nullable = false)
    private int amount;

    @Embeddable
    static class PlayerCardsId implements Serializable {
        @NonNull
        private UUID id;

        public PlayerCardsId() {
            this.id = UUID.randomUUID();
        }

        static PlayerCardsId newId() {
            return new PlayerCardsId();
        }
    }
}
