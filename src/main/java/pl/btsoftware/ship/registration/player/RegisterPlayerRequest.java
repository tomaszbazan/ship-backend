package pl.btsoftware.ship.registration.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class RegisterPlayerRequest {
    String teamName;
    String teamPassword;
    String gamePassword;
}
