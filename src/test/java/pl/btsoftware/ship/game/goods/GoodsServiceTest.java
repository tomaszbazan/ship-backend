package pl.btsoftware.ship.game.goods;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.btsoftware.ship.game.country.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest {

    @InjectMocks
    GoodsService goodsService;

    @Mock
    GoodsStartRepository goodsStartRepository;

    @Test
    void shouldMapStartGoodsOnVO() {
        // given
        Country country = Country.JAMAICA;
        List<GoodsStartEntity> goodsStartEntities = new ArrayList<>();
        goodsStartEntities.add(new GoodsStartEntity(UUID.randomUUID(), country, new GoodsEntity(UUID.randomUUID(), GoodsType.CITRUS, 10)));
        goodsStartEntities.add(new GoodsStartEntity(UUID.randomUUID(), country, new GoodsEntity(UUID.randomUUID(), GoodsType.COCOA, 150)));
        when(goodsStartRepository.findAllByCountry(country)).thenReturn(goodsStartEntities);

        // when
        List<Goods> goods = goodsService.findStartGoodsFor(country);

        // then
        assertThat(goods).extracting(Goods::type).containsExactlyInAnyOrder(GoodsType.CITRUS, GoodsType.COCOA);
        assertThat(goods).extracting(Goods::amount).containsExactlyInAnyOrder(10, 150);
    }

}
