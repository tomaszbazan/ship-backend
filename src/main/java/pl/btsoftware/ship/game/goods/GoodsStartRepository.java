package pl.btsoftware.ship.game.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.game.country.Country;

import java.util.List;
import java.util.UUID;

@Repository
interface GoodsStartRepository extends JpaRepository<GoodsStartEntity, UUID> {
    GoodsStartEntity findFirstByCountry(Country country);
}
