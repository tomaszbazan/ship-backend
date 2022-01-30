package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.goods.GoodsEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "event_reward")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventRewardEntity {
    @Id
    private UUID id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "x")),
            @AttributeOverride(name = "y", column = @Column(name = "y"))
    })
    private PositionOnBoard positionOnBoard;

//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Conjunction conjunction;
//
//    @Column(nullable = false)
//    private boolean reward;
//
    @OneToMany(mappedBy = "eventReward", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinTable(name = "event_reward",
//            joinColumns = {@JoinColumn(name = "x"), @JoinColumn(name = "y")},
//            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private List<CardEntity> cards = new ArrayList<>();
//
    @OneToMany(mappedBy = "eventReward", fetch = FetchType.EAGER)
//    @JoinTable(name = "event_reward",
//            joinColumns = {@JoinColumn(name = "x"), @JoinColumn(name = "y")},
//            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<GoodsEntity> goods = new ArrayList<>();

    @Override
    public String toString() {
        return "EventRewardEntity{" +
                "id=" + id +
                ", positionOnBoard=" + positionOnBoard +
                ", cards=" + cards.size() +
                ", goods=" + goods.size() +
                '}';
    }
}
