package pl.btsoftware.ship.game.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface GameRepository extends JpaRepository<GameEntity, GameId> {
    Optional<GameEntity> findByName(GameName gameName);
}
