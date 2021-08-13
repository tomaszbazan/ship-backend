package pl.btsoftware.ship.game.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.btsoftware.ship.game.country.Country;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PlayerPosition {
    @JsonProperty("player_name")
    private String playerName;
    private Country country;
    private PositionOnBoard coordinates;

    static PlayerPosition from(ActualBoardSituation.PositionInGame positionInGame) {
        return PlayerPosition.builder()
                .playerName(positionInGame.getPlayerName())
                .country(Country.valueOf(positionInGame.getCountry()))
                .coordinates(createCoordinatesIfNonOfThemAreNull(positionInGame))
                .build();
    }

    private static PositionOnBoard createCoordinatesIfNonOfThemAreNull(ActualBoardSituation.PositionInGame positionInGame) {
        return positionInGame.getCoordinateX() == null || positionInGame.getCoordinateY() == null ? null : new PositionOnBoard(positionInGame.getCoordinateX(), positionInGame.getCoordinateY());
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
