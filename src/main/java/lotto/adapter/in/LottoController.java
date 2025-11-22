package lotto.adapter.in;

import java.util.ArrayList;
import java.util.List;
import lotto.application.port.in.LottoPurchaseUseCase;
import lotto.application.port.in.LottoStatusUseCase;
import lotto.application.port.in.WinningLottoUseCase;
import lotto.domain.ErrorMessage;
import lotto.domain.Lotto;
import lotto.domain.LottoStatus;
import lotto.domain.WinningLotto;

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

    public void run() {
        int purchaseMoney = getValidPurchaseMoney();

        int manualCount = getValidManualCount(purchaseMoney);

        List<List<Integer>> manualNumbers = getValidManualNumbers(manualCount);

        List<Lotto> lottos = lottoPurchaseUseCase.purchaseLottos(purchaseMoney, manualNumbers);

        outputView.printPurchaseLotto(manualCount, lottos);

        setUpWinningLotto();

        showStatus(purchaseMoney);
    }

    private int getValidPurchaseMoney() {
        while (true) {
            try {
                String moneyInput = inputView.readPurchaseMoney();
                return inputMapper.parseIntMoney(moneyInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getValidManualCount(int money) {
        while (true) {
            try {
                String manualCountInput = inputView.readManualCount();
                int count = inputMapper.parseIntManualCount(manualCountInput);
                if (money / 1000 >= count) {
                    return count;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<List<Integer>> getValidManualNumbers(int manualCount) {
        List<List<Integer>> manualNumbers = new ArrayList<>();

        if (manualCount == 0) {
            return manualNumbers;
        }

        for (int i = 0; i < manualCount; i++) {
            manualNumbers.add(getOneValidLotto(i + 1, manualCount));
        }

        return manualNumbers;
    }

    private List<Integer> getOneValidLotto(int currentOrder, int totalCount) {
        while (true) {
            try {
                OutputMessage.MANUAL_INPUT.print(currentOrder, totalCount);

                String numbersInput = inputView.readManualNumber();
                List<Integer> numbers = inputMapper.toLottoList(numbersInput);

                new Lotto(numbers);

                return numbers;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setUpWinningLotto() {
        while (true) {
            try {
                boolean isAuto = getValidIsAuto();
                if (isAuto) {
                    getValidAutoWinningLotto();
                } else {
                    getValidManualWinningLotto();
                }

                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getValidAutoWinningLotto() {
        while (true) {
            try {
                String roundInput = inputView.readWinningLottoRound();
                int round = inputMapper.parseIntRound(roundInput);

                WinningLotto winningLotto = fetchWinningLotto(round);

                outputView.printWinningLotto(winningLotto);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto fetchWinningLotto(int round) {
        if (round == 0) {
            return winningLottoUseCase.setupLatestWinningLotto();
        }
        return winningLottoUseCase.setupAutoWinningLotto(round);
    }

    private void getValidManualWinningLotto() {
        while (true) {
            try {
                String winningNumbersStr = inputView.readWinningLotto();
                String bonusNumberStr = inputView.readBonusNumber();

                winningLottoUseCase.setupWinningLotto(winningNumbersStr, bonusNumberStr);

                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean getValidIsAuto(){
        while (true){
            try{
                String input = inputView.readAutoWinningLotto();

                if ("y".equalsIgnoreCase(input)) {
                    return true;
                }
                if ("n".equalsIgnoreCase(input)) {
                    return false;
                }

                throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_AUTO_LOTTO.getMessage());
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

    }


private void showStatus(int purchaseMoney) {
        LottoStatus status = lottoStatusUseCase.calculateStatus(purchaseMoney);

        outputView.printStatus(status);
    }

}