package pl.btsoftware.ship.registration;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import pl.btsoftware.ship.registration.PlayerEntity.PlayerPassword;

public class PasswordCreator {
    public static GameEntity.GamePassword gamePassword(String password) {
        return new GameEntity.GamePassword(password, new Pbkdf2PasswordEncoder());
    }

    public static PlayerPassword correctPassword() {
        return new PlayerPassword("correctPassword", new Pbkdf2PasswordEncoder());
    }

    public static PlayerPassword playerPassword(String password) {
        return new PlayerPassword(password, new Pbkdf2PasswordEncoder());
    }
}
