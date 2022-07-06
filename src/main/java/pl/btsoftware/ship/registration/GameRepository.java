package pl.btsoftware.ship.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.shared.GameName;

import java.util.Optional;

@Repository
interface GameRepository extends JpaRepository<GameEntity, GameEntity.GameId> {
    Optional<GameEntity> findByName(GameName game);
}
