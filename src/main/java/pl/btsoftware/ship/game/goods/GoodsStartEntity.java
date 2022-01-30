package pl.btsoftware.ship.game.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.country.Country;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "goods_start")
@Data
@AllArgsConstructor
@NoArgsConstructor
class GoodsStartEntity {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @OneToMany(mappedBy = "goodsStart")
    private List<GoodsEntity> goods;
}
