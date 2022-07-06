package pl.btsoftware.ship.game.goods;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.btsoftware.ship.game.country.Country;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodsService {
    private final GoodsStartRepository goodsStartRepository;

    @Transactional(readOnly = true)
    public List<GoodsDto> findStartGoodsFor(Country country) {
        return goodsStartRepository.findAllByCountry(country)
                .stream()
                .map(GoodsDto::from).toList();
    }
}
