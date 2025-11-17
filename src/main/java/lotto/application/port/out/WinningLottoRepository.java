package lotto.application.port.out;

import lotto.domain.WinningLotto;

public interface WinningLottoRepository {
    void save(WinningLotto winningLotto);
    WinningLotto find();
}
