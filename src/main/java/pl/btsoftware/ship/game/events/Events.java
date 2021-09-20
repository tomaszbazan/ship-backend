package pl.btsoftware.ship.game.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "events")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Events {
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
    private ActionKind action;

    @Column(nullable = false)
    private boolean removable;
}
