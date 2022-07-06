package pl.btsoftware.ship.game.playerPosition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.game.playerPosition.PlayerPosition.PlayerPositionId;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;

import java.util.List;
import java.util.Optional;

@Repository
interface PlayerPositionJpaRepository extends JpaRepository<PlayerPosition, PlayerPositionId> {

    @Query(value = """
            SELECT count(*)
            FROM (
                     SELECT distinct(pp.player)
                     FROM player_position pp
                     WHERE pp.game = :gameName
                     AND pp.round = :round
                     AND pp.action IN ('MOVE', 'JOIN')
                 ) AS ct;
            """, nativeQuery = true)
    long countPlayersInRound(String gameName, String round);

    @Query(value = """
            SELECT sub.player AS player, sub.country as country, sub.x AS x, sub.y AS y\s
            FROM (
               SELECT player, country, x, y, row_number() OVER(PARTITION BY country ORDER BY occurrence DESC) AS ct
               FROM player_position
               WHERE game = :gameName
            ) sub
            WHERE ct = 1;
            """, nativeQuery = true)
    List<PlayerPositionService.PlayerPositionRaw> getIdOfLastMoveForEachPlayerInGame(String gameName);

    Optional<PlayerPosition> findFirstByGameAndPlayerOrderByOccurrenceDesc(GameName game, PlayerName player);
}
