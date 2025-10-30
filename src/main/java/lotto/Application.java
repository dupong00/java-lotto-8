package lotto;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        OutputView.printMoneyQuestion();
        int count = InputView.readBuyLotto();

        OutputView.printCount(count);

        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Lotto lotto = new Lotto(generator.generateNumbers());
            lottos.add(lotto);
        }

        OutputView.printLottos(lottos);
    }
}
