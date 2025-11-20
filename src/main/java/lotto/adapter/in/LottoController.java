package lotto.adapter.in;

import java.util.List;
import lotto.application.port.in.LottoPurchaseUseCase;
import lotto.application.port.in.LottoStatusUseCase;
import lotto.application.port.in.WinningLottoUseCase;
import lotto.domain.Lotto;
import lotto.domain.LottoStatus;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputMapper inputMapper;

    private final LottoPurchaseUseCase lottoPurchaseUseCase;
    private final LottoStatusUseCase lottoStatusUseCase;
    private final WinningLottoUseCase winningLottoUseCase;

    public LottoController(InputView inputView, OutputView outputView, InputMapper inputMapper,
                           LottoPurchaseUseCase purchase, LottoStatusUseCase status,
                           WinningLottoUseCase winningLottoUseCase) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputMapper = inputMapper;

        this.lottoPurchaseUseCase = purchase;
        this.lottoStatusUseCase = status;
        this.winningLottoUseCase = winningLottoUseCase;
    }

    public void run(){
        int purchaseMoney = purchaseLottos();

        setUpWinningLotto();

        showStatus(purchaseMoney);
    }

    private int purchaseLottos(){
        while(true){
            try{
                String moneyInput = inputView.readPurchaseMoney();
                int money = inputMapper.parseIntMoney(moneyInput);

                List<Lotto> lottos = lottoPurchaseUseCase.purchaseLottos(money);

                outputView.printPurchaseLotto(lottos);

                return money;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }

        }
    }

    private void setUpWinningLotto(){
        while(true){
            try{
                String winningNumbersStr = inputView.readWinningLotto();
                String bonusNumberStr = inputView.readBonusNumber();

                winningLottoUseCase.setupWinningLotto(winningNumbersStr, bonusNumberStr);

                break;
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void showStatus(int purchaseMoney){
        LottoStatus status = lottoStatusUseCase.calculateStatus(purchaseMoney);

        outputView.printStatus(status);
    }
}
