package pl.btsoftware.ship.game.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.events.EventRewardEntity;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private GoodsStartEntity goodsStart;

    @ManyToOne(fetch = FetchType.LAZY)
    private GoodsEndEntity goodsEnd;

    @ManyToOne
    private PlayerInGameEntity playerInGame;

    @ManyToOne
    private EventRewardEntity eventReward;

    public GoodsEntity(UUID id, GoodsType type, int amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public GoodsEntity(UUID id, GoodsType type, int amount, PlayerInGameEntity playerInGame) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.playerInGame = playerInGame;
    }

    public static GoodsEntity from(Goods goods, PlayerInGameEntity playerInGame) {
        return new GoodsEntity(UUID.randomUUID(), goods.type(), goods.amount(), playerInGame);
    }

    public static GoodsEntity from(Goods goods) {
        return new GoodsEntity(UUID.randomUUID(), goods.type(), goods.amount());
    }

    public static List<GoodsEntity> copyWith(Map<GoodsType, Integer> goods, PlayerInGameEntity player) {
        List<GoodsEntity> goodsList = new ArrayList<>();
        goods.forEach((k, v) -> goodsList.add(new GoodsEntity(UUID.randomUUID(), k, v, player)));
        return goodsList;
    }

    public boolean isNotGold() {
        return !getType().equals(GoodsType.GOLD);
    }
}
