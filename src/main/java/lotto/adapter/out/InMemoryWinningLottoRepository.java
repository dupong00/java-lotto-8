package lotto.adapter.out;

import lotto.application.port.out.WinningLottoRepository;
import lotto.domain.WinningLotto;


public class InMemoryWinningLottoRepository implements WinningLottoRepository {
    private WinningLotto winningLotto;

    @Override
    public void save(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    @Override
    public WinningLotto find() {
        if (winningLotto == null) {
            throw new IllegalStateException("아직 당첨 번호가 설정되지 않았습니다.");
        }
        return winningLotto;
    }
}
