package pl.btsoftware.ship.registration.player;

import javax.validation.constraints.NotEmpty;

record RegisterPlayerRequest(@NotEmpty String playerName, @NotEmpty String playerPassword,
                             @NotEmpty String gamePassword) {
    PlayerName player() {
        return new PlayerName(playerName);
    }
}
