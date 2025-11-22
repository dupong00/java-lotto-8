package lotto.application.port.out;

import lotto.domain.WinningLotto;

public interface WinningNumberPort {
    WinningLotto getWinningLotto(int round);
    WinningLotto getLatestWinningLotto();
}
