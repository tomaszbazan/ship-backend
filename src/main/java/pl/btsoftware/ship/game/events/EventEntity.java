package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.goods.GoodsEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventEntity {
    @EmbeddedId
    private FieldId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpecialFieldKind type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NextActionKind nextAction;
//
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
}
