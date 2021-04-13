package pl.btsoftware.ship.game.logic;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.btsoftware.ship.game.registration.PlayerInGameEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class ActualBoardSituation {
    private List<PlayerPosition> players = new ArrayList<>();

    ActualBoardSituation(List<PlayerInGameEntity> playerInGameEntities) {
        playerInGameEntities.stream().map(playerInGameEntity -> {
            String playerName = playerInGameEntity.getPlayer().getName().getName();
            return PlayerPosition.builder()
                    .playerName(playerName)
                    .country(playerInGameEntity.getCountry())
                    .coordinates(playerInGameEntity.getPositionOnBoard())
                    .build();
        })
                .forEach(playerPosition -> this.players.add(playerPosition));
    }
}
