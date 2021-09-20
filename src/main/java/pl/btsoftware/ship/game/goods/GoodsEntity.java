package pl.btsoftware.ship.game.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsEntity {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoodsType type;

    @Column(nullable = false)
    private int amount;

    public static GoodsEntity from(Goods goods) {
        return new GoodsEntity(UUID.randomUUID(), goods.type(), goods.amount());
    }
}
