package pl.btsoftware.ship.game.goods;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.country.Country;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "goods_end")
@Data
@NoArgsConstructor
class GoodsEndEntity {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoodsType type;

    @Column(nullable = false)
    private int amount;
}
