package lotto.adapter.in;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoStatus;
import lotto.domain.RANK;
import lotto.domain.WinningLotto;

public class OutputView {

    public void printPurchaseLotto(int manualCount, List<Lotto> lottos) {
        if(manualCount != 0){
            OutputMessage.PURCHASE_MANUAL_COUNT.print(manualCount);
        }
        OutputMessage.PURCHASE_AUTO_COUNT.print(lottos.size() -  manualCount);

        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningLotto(WinningLotto winningLotto) {
        int round = winningLotto.getRound();
        OutputMessage.WINNING_LOTTO_AUTO_SUCCESS.print(round);

        List<Integer> numbers = winningLotto.getLotto().getNumbers();
        int bonusNumber = winningLotto.getBonusNumber();

        OutputMessage.WINNING_LOTTO_NUMBERS.print(numbers);
        OutputMessage.WINNING_LOTTO_BONUS_NUMBER.print(bonusNumber);
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