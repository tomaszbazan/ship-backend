package pl.btsoftware.ship.game.playerInGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.List;

@Repository
interface PlayerInGameRepository extends JpaRepository<PlayerInGameEntity, PlayerInGameId> {

    @Query(value = "SELECT count(*)\n" +
            "FROM (\n" +
            "         SELECT distinct(pig.player_id)\n" +
            "         FROM player_in_game pig\n" +
            "         JOIN game g on pig.game_id = g.id\n" +
            "         WHERE g.name = :gameName\n" +
            "     ) AS ct;", nativeQuery = true)
    long countPlayersInGame(String gameName);

    @Query(value = "SELECT CAST(sub.id AS varchar) AS id\n" +
            "FROM (\n" +
            "   SELECT id, game_id, row_number() OVER(PARTITION BY country ORDER BY occurrence DESC) AS ct\n" +
            "   FROM player_in_game\n" +
            ") sub\n" +
            "JOIN game g ON g.id = sub.game_id\n" +
            "WHERE g.name = :gameName AND ct = 1;", nativeQuery = true)
    List<StringId> getIdOfLastMoveForEachPlayerInGame(@Param("gameName") String gameName);

    PlayerInGameEntity getFirstByGame_NameAndPlayer_NameOrderByOccurrenceDesc(GameName gameName, PlayerName playerName);
}
