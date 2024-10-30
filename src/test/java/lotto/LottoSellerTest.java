package lotto;

import static lotto.exception.Exception.LOTTO_AMOUNT_NOT_DIVISIBLE;
import static lotto.exception.Exception.MINIMUM_LOTTO_COUNT_REQUIRED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LottoSellerTest {

    @Test
    void 사용자가_지불한_값에_따라_로또_리스트를_생성한다() {
        //given
        int amount = 1000;

        //when
        LottoBundle lotteries = LottoSeller.sell(amount);

        //then
        assertThat(lotteries.getLotteries().size()).isEqualTo(amount / 1000);
    }

    @Test
    void 판매_금액이_1000원_미만일_경우_예외가_발생한다() {
        int invalidAmount = 500;

        assertThatThrownBy(() -> LottoSeller.sell(invalidAmount)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MINIMUM_LOTTO_COUNT_REQUIRED.getMessage());
    }

    @Test
    void 판매_금액이_1000원으로_나누어_떨어지지_않을_경우_예외가_발생한다() {
        int invalidAmount = 1500;

        assertThatThrownBy(() -> LottoSeller.sell(invalidAmount)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_AMOUNT_NOT_DIVISIBLE.getMessage());
    }
}