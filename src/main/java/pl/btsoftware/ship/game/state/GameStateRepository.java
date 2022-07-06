package pl.btsoftware.ship.game.state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.shared.GameName;

@Repository
interface GameStateRepository extends JpaRepository<GameStateEntity, GameName> {
}
