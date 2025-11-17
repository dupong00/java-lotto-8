package lotto.adapter.in;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoStatus;
import lotto.domain.RANK;

public class OutputView {

    public void printPurchaseLotto(List<Lotto> lottos) {
        OutputMessage.PURCHASE_COUNT.print(lottos.size());

        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatus(LottoStatus status){
        OutputMessage.STATISTICS_HEADER.print();

        Map<RANK, Integer> rankCounts = status.getRankCounts();

        OutputMessage.RANK_RESULT.print(6, RANK.FIRST.prize, rankCounts.get(RANK.FIRST));
        OutputMessage.RANK_RESULT_SECOND.print(RANK.SECOND.prize, rankCounts.get(RANK.SECOND));
        OutputMessage.RANK_RESULT.print(5, RANK.THIRD.prize, rankCounts.get(RANK.THIRD));
        OutputMessage.RANK_RESULT.print(4, RANK.FOURTH.prize, rankCounts.get(RANK.FOURTH));
        OutputMessage.RANK_RESULT.print(3, RANK.FIFTH.prize, rankCounts.get(RANK.FIFTH));

        OutputMessage.ROI.print(status.getRateOfPrize());
    }
}