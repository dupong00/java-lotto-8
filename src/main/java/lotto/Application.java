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
        Map<RANK, Integer> winningCount = new EnumMap<>(RANK.class);

        //메인 흐름
        outputView.printMoneyQuestion();
        count = validator.validateBuyLotto(inputView.read());
        outputView.print();

        outputView.printCount(count);

        List<Lotto> lottos = lottoService.generateLottos(count);

        outputView.printLottos(lottos);
        outputView.print();

        outputView.printWinningLottoQuestion();
        List<Integer> winningLottos = validator.validateWinningLottoToParse(inputView.read());
        outputView.print();

        outputView.printBonusNumberQuestion();
        bonusNumber = validator.validateBonusNumber(inputView.read());
        outputView.print();

        for (RANK rank : RANK.values()) {
            winningCount.put(rank, 0);
        }

        for(Lotto lotto : lottos){
            int matchCount = lotto.getMatchCount(winningLottos);
            boolean isBonus = lotto.hasBonus(bonusNumber);
            RANK myrank = RANK.valueOf(matchCount, isBonus);

            winningCount.put(myrank, winningCount.get(myrank) + 1);
        }

        outputView.printTotalWinningStatus(winningCount);

        double roi = lottoService.calculateROI(winningCount);
        outputView.printROI(roi);
    }
}