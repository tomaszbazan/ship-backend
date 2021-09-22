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
public class EventInGameEntity {
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
    @Column(nullable = false)
    private ActionKind action;

    @Column(nullable = false)
    private boolean removable;

    static EventInGameEntity from(Event event, GameEntity game) {
        return new EventInGameEntity(event.getId(), game, event.getType(), event.getTitle(), event.getDescription(), event.getAction(), event.isRemovable());
    }
}
