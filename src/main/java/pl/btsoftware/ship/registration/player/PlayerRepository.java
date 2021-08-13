package pl.btsoftware.ship.registration.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, PlayerId> {
    Optional<PlayerEntity> findByName(PlayerName playerName);
}
