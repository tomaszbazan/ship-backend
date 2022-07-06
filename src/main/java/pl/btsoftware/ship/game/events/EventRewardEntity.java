package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.btsoftware.ship.shared.PositionOnBoard;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "event_reward")
@NoArgsConstructor
@AllArgsConstructor
@Data
class EventRewardEntity {
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
    @OneToMany(mappedBy = "eventReward", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<EventCardEntity> cards = new ArrayList<>();

    @OneToMany(mappedBy = "eventReward", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<EventGoodsEntity> goods = new ArrayList<>();
}
