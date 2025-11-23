package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("정상적인 금액(1000원 단위)으로 생성하면 성공한다")
    @Test
    void 생성_성공() {
        int amount = 3000;
        Money money = new Money(amount);

        assertThat(money).isNotNull();
    }

    @DisplayName("1000원 미만의 금액으로 생성하면 예외가 발생한다")
    @Test
    void 생성_실패_최소금액_미만() {
        assertThatThrownBy(() -> new Money(900))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1000원 단위가 아닌 금액으로 생성하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1100, 1500, 1001})
    void 생성_실패_단위_불일치(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가진 돈보다 많이 구매하려고 하면 예외가 발생한다")
    @Test
    void 로또_구매_실패_초과_구매() {
        Money money = new Money(2000);
        assertThatThrownBy(() -> money.spend(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("남은 돈으로 구매 가능한 로또 개수를 정확히 계산한다")
    @Test
    void 티켓_개수_계산() {
        Money money = new Money(14000);
        int count = money.calculateTicketCount();
        assertThat(count).isEqualTo(14);
    }
}