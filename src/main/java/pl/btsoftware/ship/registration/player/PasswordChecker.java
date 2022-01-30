package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.registration.game.GameEntity;
import pl.btsoftware.ship.registration.game.GamePassword;

@Service
@AllArgsConstructor
public class PasswordChecker {
    private final PasswordEncoder passwordEncoder;

    boolean checkPassword(GameEntity gameEntity, String gamePassword) {
        GamePassword createdGamePassword = gameEntity.getPassword();
        return passwordEncoder.matches(gamePassword, createdGamePassword.value());
    }

    boolean checkPassword(PlayerEntity playerEntity, String playerPassword) {
        PlayerPassword createdPlayerPassword = playerEntity.getPassword();
        return passwordEncoder.matches(playerPassword, createdPlayerPassword.value());
    }

    String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
