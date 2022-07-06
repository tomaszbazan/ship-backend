package pl.btsoftware.ship.registration;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.registration.PlayerEntity.PlayerPassword;

@Service
@AllArgsConstructor
class PasswordChecker {
    private final PasswordEncoder passwordEncoder;

    boolean checkPassword(GameEntity gameEntity, String gamePassword) {
        return passwordEncoder.matches(gamePassword, gameEntity.passwordValue());
    }

    boolean checkPassword(PlayerEntity playerEntity, String playerPassword) {
        PlayerPassword createdPlayerPassword = playerEntity.getPassword();
        return passwordEncoder.matches(playerPassword, createdPlayerPassword.value());
    }

    String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
