package lotto;

import lotto.adapter.out.InMemoryLottoRepository;
import lotto.application.service.LottoService;
import lotto.domain.ErrorMessage;
import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import lotto.application.port.out.LottoRepository;

class LottoServiceTest {
    private LottoService lottoService;
    private LottoRepository lottoRepository;

    @BeforeEach
    void setUp() {
        lottoRepository = new InMemoryLottoRepository();
        this.lottoService = new LottoService(lottoRepository);
    }

    @Test
    @DisplayName("성공: 3000원 입력 시 수동 2장 자동 1장을 정상 생성한다")
    void 로또_구매_성공() {
        int amount = 3000;

        List<List<Integer>> manualNumbers = List.of(
                List.of(1,2,3,4,5,6),
                List.of(7,8,9,10,11,12)
        );
        List<Lotto> purchasedLottos = lottoService.purchaseLottos(amount, manualNumbers);

        assertThat(purchasedLottos).hasSize(3);

        assertThat(purchasedLottos.getFirst().getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("실패: 1000원 단위가 아닌 금액(1500) 입력 시 예외 발생")
    void 로또_구매_금액_1000단위_아닌_경우() {
        int amount = 1500;
        List<List<Integer>> manualNumbers = List.of();

        assertThatThrownBy(() -> lottoService.purchaseLottos(amount, manualNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_PURCHASE_NOT_UNIT.getMessage());
    }

    @Test
    @DisplayName("실패: 최소 금액(1000원) 미만 입력 시 예외 발생")
    void 로또_구매_금액_1000원_미만인_경우() {
        int amount = 500;
        List<List<Integer>> manualNumbers = List.of();

        assertThatThrownBy(() -> lottoService.purchaseLottos(amount, manualNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_PURCHASE_NOT_MIN_ORDER.getMessage());
    }

    @Test
    @DisplayName("실패: 구입 금액보다 수동 로또 장수가 더 많으면 예외 발생")
    void 수동_구매_비용_부족() {
        int amount = 1000;
        List<List<Integer>> manualNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        );

        assertThatThrownBy(() -> lottoService.purchaseLottos(amount, manualNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_PURCHASE_OVER.getMessage());
    }
}