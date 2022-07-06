package pl.btsoftware.ship.game.playerPosition;

import lombok.*;
import pl.btsoftware.ship.game.goods.GoodsDto;
import pl.btsoftware.ship.game.goods.GoodsType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "player_goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
class PlayerGoodsEntity {
    @EmbeddedId
    @Column(nullable = false)
    private PlayerGoodsId id;

    @ManyToOne
    @JoinColumn(name = "player_position_id", nullable = false)
    private PlayerPosition playerPosition;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoodsType type;

    @Column(nullable = false)
    private int amount;

    public static PlayerGoodsEntity from(GoodsDto goodsDto) {
        return new PlayerGoodsEntity(PlayerGoodsId.newId(), null, goodsDto.type(), goodsDto.amount());
    }

    public static PlayerGoodsEntity from(GoodsDto goodsDto, PlayerPosition playerPosition) {
        return new PlayerGoodsEntity(PlayerGoodsId.newId(), playerPosition, goodsDto.type(), goodsDto.amount());
    }

    public static PlayerGoodsEntity copy(PlayerGoodsEntity goods, PlayerPosition playerPosition) {
        return new PlayerGoodsEntity(PlayerGoodsId.newId(), playerPosition, goods.getType(), goods.getAmount());
    }

    boolean isNotGold() {
        return !this.type.equals(GoodsType.GOLD);
    }

    @Embeddable
    static class PlayerGoodsId implements Serializable {
        @NonNull
        private UUID id;

        public PlayerGoodsId() {
            this.id = UUID.randomUUID();
        }

        static PlayerGoodsId newId() {
            return new PlayerGoodsId();
        }
    }
}
