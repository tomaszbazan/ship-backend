package pl.btsoftware.ship.game.playerPosition;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.goods.GoodsDto;
import pl.btsoftware.ship.shared.GameName;
import pl.btsoftware.ship.shared.PlayerName;
import pl.btsoftware.ship.shared.PositionOnBoard;

import java.util.List;

public record PlayerPositionSnapshot(
        @JsonProperty("player") PlayerName playerName,
        @JsonProperty("game") GameName gameName,
        Country country,
        PositionOnBoard position,
        ActionType lastAction,
        List<GoodsDto> goods,
        List<CardsDto> cards,
        @JsonProperty("first_login") boolean firstLogin) {

    public static PlayerPositionSnapshot from(PlayerPosition playerPosition) {
        if (playerPosition == null) {
            return null;
        }

        return new PlayerPositionSnapshot(playerPosition.getPlayer(), playerPosition.getGame(), playerPosition.getCountry(), playerPosition.getPositionOnBoard(), playerPosition.getAction(), playerPosition.goodsDtos(), playerPosition.getCards().stream().map(CardsDto::from).toList(), playerPosition.firstMove());
    }

    public record CardsDto(String type, int amount) {
        static CardsDto from(PlayerCardsEntity playerCards) {
            return new CardsDto(playerCards.getType().getName(), playerCards.getAmount());
        }
    }
}
