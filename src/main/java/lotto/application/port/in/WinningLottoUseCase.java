package lotto.application.port.in;

import lotto.domain.WinningLotto;

public interface WinningLottoUseCase {
    void setupWinningLotto(String winningNumbersStr, String bonusNumberStr);
    WinningLotto setupAutoWinningLotto(int round);
    WinningLotto setupLatestWinningLotto();
}
