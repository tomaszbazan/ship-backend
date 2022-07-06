package pl.btsoftware.ship.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.shared.PlayerName;

import java.util.Optional;

@Repository
interface PlayerRepository extends JpaRepository<PlayerEntity, PlayerEntity.PlayerId> {
    Optional<PlayerEntity> findByName(PlayerName player);
}
