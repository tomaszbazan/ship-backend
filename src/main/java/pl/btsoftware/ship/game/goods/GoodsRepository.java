package pl.btsoftware.ship.game.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface GoodsRepository extends JpaRepository<GoodsEntity, UUID> {
}
