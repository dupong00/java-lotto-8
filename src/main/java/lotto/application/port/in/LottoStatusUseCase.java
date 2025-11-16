package lotto.application.port.in;

import lotto.domain.LottoStatus;

public interface LottoStatusUseCase {
    LottoStatus calculateStatus(int purchaseMoney);
}
