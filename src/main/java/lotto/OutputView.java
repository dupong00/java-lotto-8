package lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printMoneyQuestion() {
        System.out.println(OutputMessage.MONEY_QUESTION.getMessage());
    }
    public void printCount(int count) {
        System.out.printf(OutputMessage.COUNT_MESSAGE.getMessage() + "\n", count);
    }
    public void printWinningLottoQuestion() {
        System.out.println(OutputMessage.WINNING_LOTTO_QUESTION.getMessage());
    }
    public void printBonusNumberQuestion() {
        System.out.println(OutputMessage.BONUS_NUMBER_QUESTION.getMessage());
    }
    public void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printTotalWinningStatus(Map<RANK, Integer> winningCount) {
        DecimalFormat df = new DecimalFormat("###,###");

        System.out.println(OutputMessage.STATUS_MESSAGE.getMessage());

        for(Map.Entry<RANK, Integer> entry : winningCount.entrySet()) {
            RANK key = entry.getKey();

            if (key == RANK.MISS){
                continue;
            }

            int matchCount = key.getMatchCount();
            int value = entry.getValue();
            int money = key.getPrizeMoney();

            if(key == RANK.SECOND){
                System.out.printf(OutputMessage.RANK_SECOND.getMessage() + "\n", matchCount,df.format(money), value);
                continue;
            }

            System.out.printf(OutputMessage.RANK_STANDARD.getMessage() + "\n", matchCount,df.format(money), value);
        }
    }
    public void printROI(double roi){
        System.out.printf(OutputMessage.ROI_MESSAGE.getMessage() + "\n", roi);
    }
}
