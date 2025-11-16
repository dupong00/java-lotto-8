package lotto;

import lotto.application.service.LottoService;
import lotto.domain.ErrorMessage;
import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        this.lottoService = new LottoService();
    }

    @Test
    @DisplayName("성공: 3000원 입력 시 로또 3장을 정상 생성한다")
    void 로또_구매_성공() {
        String amount = "3000";

        List<Lotto> purchasedLottos = lottoService.purchaseLottos(amount);

        assertThat(purchasedLottos).hasSize(3);
    }

    @Test
    @DisplayName("실패: 숫자가 아닌 금액(1000j) 입력 시 예외 발생")
    void 로또_구매_금액_숫자_아닌_경우() {
        String amount = "1000j";

        assertThatThrownBy(() -> lottoService.purchaseLottos(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_PURCHASE_NOT_NUMBER.getMessage());
    }

    @Test
    @DisplayName("실패: 1000원 단위가 아닌 금액(1500) 입력 시 예외 발생")
    void 로또_구매_금액_1000단위_아닌_경우() {
        String amount = "1500";

        assertThatThrownBy(() -> lottoService.purchaseLottos(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_PURCHASE_NOT_UNIT.getMessage());
    }

    @Test
    @DisplayName("실패: 최소 금액(1000원) 미만 입력 시 예외 발생")
    void 로또_구매_금액_1000원_미만인_경우() {
        String amount = "500";

        assertThatThrownBy(() -> lottoService.purchaseLottos(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_PURCHASE_NOT_MIN_ORDER.getMessage());
    }
}