package pl.btsoftware.ship.game.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.playerInGame.PlayerInGameEntity;

@Data
@AllArgsConstructor
@Builder
public class PlayerSituation {
    @JsonProperty("player_name")
    private String playerName;
    private Country country;
    private PositionOnBoard coordinates;

    public static PlayerSituation from(PlayerInGameEntity playerInGame) {
        return PlayerSituation.builder()
                .playerName(playerInGame.getPlayerName().getName())
                .country(playerInGame.getCountry())
                .coordinates(createCoordinatesIfNonOfThemAreNull(playerInGame))
                .build();
    }

    private static PositionOnBoard createCoordinatesIfNonOfThemAreNull(PlayerInGameEntity playerInGame) {
        PositionOnBoard positionInGame = playerInGame.getPositionOnBoard();
        return positionInGame == null ? null : new PositionOnBoard(positionInGame.getX(), positionInGame.getY());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class IncorrectPositionException extends RuntimeException {
        public IncorrectPositionException() {
            super("Coordinate must meet conditions: " + PositionOnBoard.MIN_X + " <= x <= " + PositionOnBoard.MAX_X + " , " + PositionOnBoard.MIN_Y + " <= y <= " + PositionOnBoard.MAX_Y);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class MaximumPlayersInGameException extends RuntimeException {
        public MaximumPlayersInGameException() {
            super("Maximum number of players in game is 5. Limit is reached.");
        }
    }
}
