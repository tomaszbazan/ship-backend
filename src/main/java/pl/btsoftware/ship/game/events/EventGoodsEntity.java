package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.goods.GoodsType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "event_goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
class EventGoodsEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_reward_id")
    private EventRewardEntity eventReward;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoodsType type;

    @Column(nullable = false)
    private int amount;
}
