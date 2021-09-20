package pl.btsoftware.ship.game.goods;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.btsoftware.ship.game.country.Country;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GoodsService {
    private final GoodsStartRepository goodsStartRepository;

    public List<Goods> findStartGoodsFor(Country country) {
        return goodsStartRepository.findAllByCountry(country)
                .stream()
                .map(GoodsStartEntity::getGoods)
                .map(Goods::from)
                .collect(Collectors.toList());
    }
}
