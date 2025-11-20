package lotto;

import lotto.adapter.in.InputMapper;
import lotto.adapter.in.InputView;
import lotto.adapter.in.LottoController;
import lotto.adapter.in.OutputView;

import lotto.adapter.out.InMemoryLottoRepository;
import lotto.adapter.out.InMemoryWinningLottoRepository;

import lotto.application.port.in.LottoPurchaseUseCase;
import lotto.application.port.in.LottoStatusUseCase;
import lotto.application.port.in.WinningLottoUseCase;

import lotto.application.port.out.LottoRepository;
import lotto.application.port.out.WinningLottoRepository;

import lotto.application.service.LottoService;
import lotto.application.service.LottoStatusService;
import lotto.application.service.WinningLottoService;

public class Application {
    public static void main(String[] args) {
        LottoRepository lottoRepository = new InMemoryLottoRepository();
        WinningLottoRepository winningLottoRepository = new InMemoryWinningLottoRepository();

        LottoPurchaseUseCase purchaseService = new LottoService(lottoRepository);
        WinningLottoUseCase winningLottoService = new WinningLottoService(winningLottoRepository);
        LottoStatusUseCase statusService = new LottoStatusService(lottoRepository, winningLottoRepository);

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        InputMapper inputMapper = new InputMapper();

        LottoController controller = new LottoController(
                inputView,
                outputView,
                inputMapper,
                purchaseService,
                statusService,
                winningLottoService
        );

        controller.run();
    }
}