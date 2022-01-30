package pl.btsoftware.ship.creators;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import pl.btsoftware.ship.registration.game.GamePassword;
import pl.btsoftware.ship.registration.player.PlayerPassword;

public class PasswordCreator {
    public static GamePassword gamePassword(String password) {
        return new GamePassword(password, new Pbkdf2PasswordEncoder());
    }

    public static PlayerPassword correctPassword() {
        return new PlayerPassword("correctPassword", new Pbkdf2PasswordEncoder());
    }

    public static PlayerPassword playerPassword(String password) {
        return new PlayerPassword(password, new Pbkdf2PasswordEncoder());
    }
}
