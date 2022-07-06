package pl.btsoftware.ship.game.events;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "event_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
class EventCardEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_reward_id")
    private EventRewardEntity eventReward;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType type;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Conjunction conjunction;

//    @AllArgsConstructor
//    enum Conjunction {
//        AND("AND"), OR("OR");
//
//        private final String conjunction;
//
//        @JsonValue
//        String getConjunction() {
//            return conjunction;
//        }
//    }

    @AllArgsConstructor
    enum CardType {
        SNAIL("SNAIL"), BILL_OF_EXCHANGE("BILL_OF_EXCHANGE"), RUM("RUM"),
        CURE("CURE"), EXCHANGE("EXCHANGE"), DEPOSIT("DEPOSIT"), SHOPPING_LIST("SHOPPING_LIST"), JOKER("JOKER");

        private final String type;

        @JsonValue
        String getType() {
            return type;
        }
    }
}
