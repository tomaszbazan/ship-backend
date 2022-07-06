package pl.btsoftware.ship.game.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.shared.GameName;

@Repository
interface GameConfigurationRepository extends JpaRepository<GameConfigurationEntity, GameName> {
}
