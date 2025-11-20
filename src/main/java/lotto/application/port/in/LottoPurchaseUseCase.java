package lotto.application.port.in;

import java.util.List;
import lotto.domain.Lotto;

public interface LottoPurchaseUseCase {
    List<Lotto> purchaseLottos(int money, List<List<Integer>> manualNumbers);
}
