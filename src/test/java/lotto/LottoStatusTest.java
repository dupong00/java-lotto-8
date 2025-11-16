package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoStatus;
import lotto.domain.RANK;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LottoStatusTest {
    private List<Lotto> userLotto;
    private WinningLotto winningLotto;
    private LottoStatus lottoStatus;

    @BeforeEach
    void setup() {
        userLotto =  new ArrayList<>();
        userLotto.add(new Lotto(List.of(8, 21, 23, 41, 42, 43)));
        userLotto.add(new Lotto(List.of(3, 5, 11, 16, 32, 38)));
        userLotto.add(new Lotto(List.of(7, 11, 16, 35, 36, 44)));
        userLotto.add(new Lotto(List.of(1, 8, 11, 31, 41, 42)));
        userLotto.add(new Lotto(List.of(13, 14, 16, 38, 42, 45)));
        userLotto.add(new Lotto(List.of(7, 11, 30, 40, 42, 43)));
        userLotto.add(new Lotto(List.of(2, 13, 22, 32, 38, 45)));
        userLotto.add(new Lotto(List.of(1, 3, 5, 14, 22, 45)));

        winningLotto = new WinningLotto(1, List.of(1,2,3,4,5,6), 7);

        lottoStatus = LottoStatus.of(userLotto, winningLotto, 8000);
    }

    @Test
    public void 당첨금액_총합_테스트() {
        assertThat(lottoStatus.getTotalPrize()).isEqualTo(5_000);
    }

    @Test
    public void 수익률_계산_테스트() {
        assertThat(lottoStatus.getRateOfPrize()).isEqualTo(62.5);
    }


}
