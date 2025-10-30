package lotto;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        //객체 생성
        LottoGenerator lottoGenerator = new LottoGenerator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        //변수 생성
        int count = inputView.readBuyLotto();

        //메인 흐름
        outputView.printMoneyQuestion();

        outputView.printCount(count);

        List<Lotto> lottos = lottoGenerator.generateLottos(count);

        outputView.printLottos(lottos);
    }
}
