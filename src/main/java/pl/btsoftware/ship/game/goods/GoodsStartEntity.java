package pl.btsoftware.ship.game.goods;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.country.Country;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "goods_start")
@Data
@NoArgsConstructor
class GoodsStartEntity {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @OneToOne
    private GoodsEntity goods;
}
