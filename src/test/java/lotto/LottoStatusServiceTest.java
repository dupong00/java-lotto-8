package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import lotto.adapter.out.InMemoryLottoRepository;
import lotto.adapter.out.InMemoryWinningLottoRepository;
import lotto.application.port.out.LottoRepository;
import lotto.application.port.out.WinningLottoRepository;
import lotto.application.service.LottoStatusService;
import lotto.domain.Lotto;
import lotto.domain.LottoStatus;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStatusServiceTest {
    private LottoStatusService lottoStatusService;

    private LottoRepository lottoRepository;
    private WinningLottoRepository winningLottoRepository;

    @BeforeEach
    void setUp() {
        lottoRepository = new InMemoryLottoRepository();
        winningLottoRepository = new InMemoryWinningLottoRepository();

        lottoStatusService = new LottoStatusService(lottoRepository, winningLottoRepository);

        Lotto lottoToSave = new Lotto(List.of(1,2,3,10,11,12));
        WinningLotto winningLottoToSave = new WinningLotto(1, List.of(1,2,3,4,5,6),7);

        lottoRepository.save(lottoToSave);
        winningLottoRepository.save(winningLottoToSave);
    }

    @Test
    void 통계_계산_성공(){
        int purchaseMoney = 8000;

        LottoStatus resultStatus = lottoStatusService.calculateStatus(purchaseMoney);

        assertThat(resultStatus.getTotalPrize()).isEqualTo(5000);
        assertThat(resultStatus.getRateOfPrize()).isEqualTo(62.5);
    }
}
