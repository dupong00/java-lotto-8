package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;

import camp.nextstep.edu.missionutils.test.Assertions;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    @Test
    @DisplayName("로또 용지 count만큼 생성")
    public void generateLottos(){
        int count = 3;
        LottoService lottoService = new LottoService();

        List<Lotto> lottos = lottoService.generateLottos(count);

        assertThat(lottos.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Lotto 당첨 집계 기능")
    public void countingLotto(){
        LottoService lottoService = new LottoService();
        List<Integer> winningLotto = List.of(1,2,3,4,5,6);
        int bonusNumber = 7;
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1,2,3,4,5,6)),
                new Lotto(List.of(1,2,3,4,5,7)),
                new Lotto(List.of(1,2,3,4,5,8)),
                new Lotto(List.of(1,2,3,4,8,9)),
                new Lotto(List.of(1,2,3,8,9,10)),
                new Lotto(List.of(1,2,8,9,10,11))
                );

        Map<RANK, Integer> result = lottoService.countingLotto(lottos, winningLotto, bonusNumber);

        assertThat(result.get(RANK.FIRST)).isEqualTo(1);
        assertThat(result.get(RANK.SECOND)).isEqualTo(1);
        assertThat(result.get(RANK.THIRD)).isEqualTo(1);
        assertThat(result.get(RANK.FOURTH)).isEqualTo(1);
        assertThat(result.get(RANK.FIFTH)).isEqualTo(1);
        assertThat(result.get(RANK.MISS)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산 기능")
    public void calculateROI() {
        LottoService lottoService = new LottoService();

        Map<RANK,Integer> winningLottos = new EnumMap<>(RANK.class);

        winningLottos.put(RANK.FIFTH,1);
        winningLottos.put(RANK.MISS,7);

        assertThat(lottoService.calculateROI(winningLottos)).isEqualTo(62.5);;
    }
}
