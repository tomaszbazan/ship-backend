package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardEntity {
    @Id
    UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Conjunction conjunction;

    @ManyToOne
    private PlayerInGameEntity playerInGame;

    @ManyToOne
    private EventRewardEntity eventReward;
}
