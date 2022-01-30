package pl.btsoftware.ship.configuration;

import org.springframework.core.convert.converter.Converter;
import pl.btsoftware.ship.game.events.SpecialFieldKind;

public class SpecialFieldKindConverter implements Converter<String, SpecialFieldKind> {
    @Override
    public SpecialFieldKind convert(String source) {
        return SpecialFieldKind.valueOf(source.toUpperCase());
    }
}
