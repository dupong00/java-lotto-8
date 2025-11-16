package lotto.application.service;

import lotto.application.port.in.LottoStatusUseCase;
import lotto.application.port.out.LottoRepository;
import lotto.application.port.out.WinningLottoRepository;
import lotto.domain.Lotto;
import lotto.domain.LottoStatus;
import lotto.domain.WinningLotto;
import java.util.List;

public class LottoStatusService implements LottoStatusUseCase {

    private LottoRepository lottoRepository;
    private WinningLottoRepository winningLottoRepository;

    public LottoStatusService(LottoRepository lottoRepository, WinningLottoRepository winningLottoRepository) {
        this.lottoRepository = lottoRepository;
        this.winningLottoRepository = winningLottoRepository;
    }

    @Override
    public LottoStatus calculateStatus(int purchaseMoney){
        List<Lotto> userLottos = lottoRepository.findAll();
        WinningLotto winningLotto = winningLottoRepository.find();

        return LottoStatus.of(userLottos, winningLotto, purchaseMoney);
    }
}
