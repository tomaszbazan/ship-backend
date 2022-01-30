package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.registration.game.GameEntity;

import javax.persistence.*;

@Entity
@Table(name = "event_in_game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
class EventInGameEntity {
    @EmbeddedId
    private FieldId id;

    @OneToOne
    private GameEntity game;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpecialFieldKind type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private NextActionKind nextAction;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Conjunction conjunction;
//
//    @Column(nullable = false)
//    private boolean reward;
//
//    @OneToMany(mappedBy = "eventInGame", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinTable(name = "event_reward",
//            joinColumns = {@JoinColumn(name = "x"), @JoinColumn(name = "y")},
//            inverseJoinColumns = @JoinColumn(name = "card_id"))
//    private List<CardEntity> cards = new ArrayList<>();
//
//    @OneToMany(mappedBy = "eventInGame", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinTable(name = "event_reward",
//            joinColumns = {@JoinColumn(name = "x"), @JoinColumn(name = "y")},
//            inverseJoinColumns = @JoinColumn(name = "goods_id"))
//    private List<GoodsEntity> goods = new ArrayList<>();

    @Column(nullable = false)
    private boolean removable;

    static EventInGameEntity from(EventEntity event, GameEntity game) {
//        return new EventInGameEntity(event.getId(), game, event.getType(), event.getTitle(), event.getDescription(), event.getNextAction(), event.getConjunction(), event.isReward(), event.getCards(), event.getGoods(), event.isRemovable());
        return new EventInGameEntity(event.getId(), game, event.getType(), event.getTitle(), event.getDescription(), event.getNextAction(), event.isRemovable());
    }
}
