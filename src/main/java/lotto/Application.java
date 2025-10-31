package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        //객체 생성
        LottoService lottoService = new LottoService();
        Validator validator = new Validator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        int count;
        int bonusNumber;
        double roi;
        Map<RANK, Integer> winningCount;

        //메인 흐름
        try {
            outputView.printMoneyQuestion();
            count = validator.validateBuyLotto(inputView.read());

            outputView.printCount(count);

            List<Lotto> lottos = lottoService.generateLottos(count);

            outputView.printLottos(lottos);

            outputView.printWinningLottoQuestion();
            List<Integer> winningLottos = validator.validateWinningLottoToParse(inputView.read());

            outputView.printBonusNumberQuestion();
            bonusNumber = validator.validateBonusNumber(inputView.read(), winningLottos);

            winningCount = lottoService.countingLotto(lottos, winningLottos, bonusNumber);

            outputView.printTotalWinningStatus(winningCount);

            roi = lottoService.calculateROI(winningCount);
            outputView.printROI(roi);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}