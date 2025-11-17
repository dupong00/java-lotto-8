package lotto.application.port.in;

public interface WinningLottoUseCase {
    void setupWinningLotto(String winningNumbersStr, String bonusNumberStr);
}
