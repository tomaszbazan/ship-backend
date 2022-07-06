package pl.btsoftware.ship.creators;


import pl.btsoftware.ship.game.playerPosition.CardsType;
import pl.btsoftware.ship.game.playerPosition.PlayerPositionSnapshot;

import java.util.Collections;
import java.util.List;

public class CardsCreator {
    public static List<PlayerPositionSnapshot.CardsDto> cards() {
        return Collections.singletonList(new PlayerPositionSnapshot.CardsDto(CardsType.SNAIL.getName(), 1));
    }

    public static List<PlayerPositionSnapshot.CardsDto> empty() {
        return Collections.emptyList();
    }
}
