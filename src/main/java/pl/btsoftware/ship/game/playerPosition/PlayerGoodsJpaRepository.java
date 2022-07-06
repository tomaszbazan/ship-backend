package pl.btsoftware.ship.game.playerPosition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PlayerGoodsJpaRepository extends JpaRepository<PlayerGoodsEntity, PlayerGoodsEntity.PlayerGoodsId> {
}
