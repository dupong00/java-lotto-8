package lotto.application.port.in;

import java.util.List;
import lotto.domain.Lotto;

public interface LottoPurchaseUseCase {
    List<Lotto> purchaseLottos(String amountString);
}
