package pl.btsoftware.ship.game.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerInGameRepository extends JpaRepository<PlayerInGameEntity, PlayerInGameId> {
    long countByGame_Name(GameName game);
}
