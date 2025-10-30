package lotto;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        //객체 생성
        LottoGenerator lottoGenerator = new LottoGenerator();
        Validator validator = new Validator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        int count;

        //메인 흐름
        outputView.printMoneyQuestion();
        count = validator.validateBuyLotto(inputView.read());

        outputView.printCount(count);

        List<Lotto> lottos = lottoGenerator.generateLottos(count);

        outputView.printLottos(lottos);
    }

    int bonusNumber;

        outputView.printWinningLottoQuestion();
    String winningLottos = inputView.read();

    String[] winningLotto = winningLottos.trim().split(",");

        outputView.printBonusNumberQuestion();
    bonusNumber = inputView.read();
}
