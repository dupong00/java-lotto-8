package lotto.application.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.application.port.in.WinningLottoUseCase;
import lotto.application.port.out.WinningLottoRepository;
import lotto.application.port.out.WinningNumberPort;
import lotto.domain.ErrorMessage;
import lotto.domain.WinningLotto;

public class WinningLottoService implements WinningLottoUseCase {
    private final WinningLottoRepository winningLottoRepository;

    private final WinningNumberPort winningNumberPort;

    public WinningLottoService(WinningLottoRepository winningLottoRepository,
                               WinningNumberPort winningNumberPort) {
        this.winningNumberPort = winningNumberPort;
        this.winningLottoRepository = winningLottoRepository;
    }

    @Override
    public void setupWinningLotto(List<Integer> winningNumbers, int bonusNumber){
        WinningLotto winningLotto = new WinningLotto(1, winningNumbers, bonusNumber);

        winningLottoRepository.save(winningLotto);
    }

    @Override
    public WinningLotto setupAutoWinningLotto(int round) {
        WinningLotto winningLotto = winningNumberPort.getWinningLotto(round);
        winningLottoRepository.save(winningLotto);
        return winningLotto;
    }

    @Override
    public WinningLotto setupLatestWinningLotto() {
        WinningLotto winningLotto = winningNumberPort.getLatestWinningLotto();
        winningLottoRepository.save(winningLotto);
        return winningLotto;
    }
}
