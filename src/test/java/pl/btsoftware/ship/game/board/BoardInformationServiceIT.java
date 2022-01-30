package pl.btsoftware.ship.game.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.btsoftware.ship.IntegrationTest;
import pl.btsoftware.ship.game.country.Country;
import pl.btsoftware.ship.game.playerInGame.PlayerMoverService;
import pl.btsoftware.ship.registration.game.GameFixture;
import pl.btsoftware.ship.registration.game.GameName;
import pl.btsoftware.ship.registration.player.PlayerFixture;
import pl.btsoftware.ship.registration.player.PlayerName;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardInformationServiceIT extends IntegrationTest {
    @Autowired
    private PlayerMoverService playerMoverService;

    @Autowired
    private BoardInformationService boardInformationService;

    @Autowired
    private GameFixture gameFixture;

    @Autowired
    private PlayerFixture playerFixture;

    @Test
    void shouldReturnOnlyLastMoveForEachPlayer() {
        // given
        GameName gameName = new GameName(UUID.randomUUID().toString());
        createGame(gameName);
        allCountriesJoinToGame(gameName);
        allCountriesAreMaking3RandomMoves(gameName);

        // when
        List<PlayerSituation> situations = boardInformationService.actualSituation(gameName);

        // then
        assertThat(situations).hasSize(Country.values().length);
        assertThat(situations).extracting(PlayerSituation::getCountry).doesNotContainNull();
        assertThat(situations).extracting(PlayerSituation::getPlayerName).doesNotContainNull();
        assertThat(situations).extracting(PlayerSituation::getCoordinates).doesNotContainNull();
    }

    private void allCountriesAreMaking3RandomMoves(GameName gameName) {
        Stream.of(Country.values()).forEach(country -> {
            int countryIndex = 1;
            for (int i = 0; i < 3; i++) {
                playerMoverService.movePlayer(gameName, new PlayerName(country.getName()), positionOnBoard(countryIndex, i));
            }
            countryIndex += 1;
        });
    }

    private void createGame(GameName gameName) {
        gameFixture.createGame(gameName.getName(), "anyPassword");
    }

    private void allCountriesJoinToGame(GameName gameName) {
        Stream.of(Country.values()).forEach(country -> playerFixture.joinPlayer(gameName, country.getName(), "anyPassword", "anyPassword"));
    }

    private PositionOnBoard positionOnBoard(int countryIndex, int inc) {
        return new PositionOnBoard(countryIndex + inc, countryIndex + inc);
    }
}
