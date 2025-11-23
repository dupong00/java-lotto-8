package lotto.application.port.in;

import java.util.List;
import lotto.domain.WinningLotto;

public interface WinningLottoUseCase {
    void setupWinningLotto(List<Integer> winningNumbersStr, int bonusNumberStr);
    WinningLotto setupAutoWinningLotto(int round);
    WinningLotto setupLatestWinningLotto();
}
