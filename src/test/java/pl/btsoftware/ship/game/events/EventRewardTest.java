package pl.btsoftware.ship.game.events;

import org.junit.jupiter.api.Test;
import pl.btsoftware.ship.creators.CardsCreator;
import pl.btsoftware.ship.creators.GoodsCreator;
import pl.btsoftware.ship.game.board.PositionOnBoard;
import pl.btsoftware.ship.game.goods.GoodsType;
import pl.btsoftware.ship.game.playerInGame.PlayerNextActionDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static pl.btsoftware.ship.game.events.CardType.CURE;
import static pl.btsoftware.ship.game.goods.GoodsType.GOLD;

class EventRewardTest {
    @Test
    void shouldReturnTheSameListOfGoodsWhenRewardGoodsAreEmpty() {
        // given
        GoodsType goodsType = GoodsType.CITRUS;
        int goodsAmount = 10;
        Map<GoodsType, Integer> currentGoods = GoodsCreator.goodsMap(goodsType, goodsAmount);
        EventReward eventReward = new EventReward(new PositionOnBoard(1, 1), null, null, null);

        // when
        Map<GoodsType, Integer> newGoods = eventReward.addGoods(currentGoods);

        // then
        assertThat(newGoods).hasSize(1).containsOnly(entry(goodsType, 10));
    }

    @Test
    void shouldAddGoodsAmountToCurrentList() {
        // given
        GoodsType goodsType = GoodsType.CITRUS;
        int goodsAmount = 10;
        Map<GoodsType, Integer> currentGoods = GoodsCreator.goodsMap(goodsType, goodsAmount);
        EventReward eventReward = new EventReward(new PositionOnBoard(1, 1), null, GoodsCreator.goodsMap(goodsType, 10), null);

        // when
        Map<GoodsType, Integer> newGoods = eventReward.addGoods(currentGoods);

        // then
        assertThat(newGoods).hasSize(1).containsOnly(entry(goodsType, 20));
    }

    @Test
    void shouldAddNewTypeOfGoodsToCurrentList() {
        // given
        GoodsType goodsType = GoodsType.CITRUS;
        int goodsAmount = 10;
        Map<GoodsType, Integer> currentGoods = GoodsCreator.goodsMap(goodsType, goodsAmount);
        EventReward eventReward = new EventReward(new PositionOnBoard(1, 1), null, GoodsCreator.goodsMap(GOLD, 10), null);

        // when
        Map<GoodsType, Integer> newGoods = eventReward.addGoods(currentGoods);

        // then
        assertThat(newGoods).hasSize(2).containsOnly(entry(goodsType, 10), entry(GOLD, 10));
    }

    @Test
    void shouldReturnEmptyArraysWhenRewardIsNull() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(1, 1);
        PlayerNextActionDto playerNextAction = new PlayerNextActionDto("any", "any", NextActionKind.NEXT_MOVE, SpecialFieldKind.BOTTLE);

        // when
        EventReward eventReward = EventReward.from(positionOnBoard, playerNextAction);

        // then
        assertThat(eventReward).isNotNull();
        assertThat(eventReward.goods()).isEmpty();
        assertThat(eventReward.cards()).isEmpty();
        assertThat(eventReward.playerNextActionDto()).isEqualTo(playerNextAction);
        assertThat(eventReward.positionOnBoard()).isEqualTo(positionOnBoard);
    }

    @Test
    void shouldMakeMapFromGoodsWhenRewardIsNotNull() {
        // given
        PositionOnBoard positionOnBoard = new PositionOnBoard(1, 1);
        PlayerNextActionDto playerNextAction = new PlayerNextActionDto("any", "any", NextActionKind.NEXT_MOVE, SpecialFieldKind.BOTTLE);
        List<CardEntity> cards = CardsCreator.cardsEntity(CURE);
        EventRewardEntity eventRewardEntity = new EventRewardEntity(UUID.randomUUID(), positionOnBoard, cards, GoodsCreator.goodsEntity(GOLD, 10));

        // when
        EventReward eventReward = EventReward.from(positionOnBoard, playerNextAction, eventRewardEntity);

        // then
        assertThat(eventReward).isNotNull();
        assertThat(eventReward.goods()).containsEntry(GOLD, 10);
        assertThat(eventReward.cards()).isEqualTo(cards);
        assertThat(eventReward.playerNextActionDto()).isEqualTo(playerNextAction);
        assertThat(eventReward.positionOnBoard()).isEqualTo(positionOnBoard);
    }
}
