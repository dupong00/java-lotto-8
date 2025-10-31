package lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void print(){
        System.out.println();
    }
    public void printMoneyQuestion() {
        System.out.println("구입금액을 입력해 주세요.");
    }
    public void printCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }
    public void printWinningLottoQuestion() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }
    public void printBonusNumberQuestion() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }
    public void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printTotalWinningStatus(Map<RANK, Integer> winningCount) {
        DecimalFormat df = new DecimalFormat("###,###");

        System.out.println("당첨 통계");
        System.out.println("---");
        for(Map.Entry<RANK, Integer> entry : winningCount.entrySet()) {
            RANK key = entry.getKey();

            if (key == RANK.MISS){
                continue;
            }

            int matchCount = key.getMatchCount();
            int value = entry.getValue();
            int money = key.getPrizeMoney();

            if(key == RANK.SECOND){
                System.out.println(matchCount + "개 일치, 보너스 볼 일치 (" + df.format(money) + "원) - " + value + "개");
                continue;
            }

            System.out.println(matchCount + "개 일치 (" + df.format(money) + "원) - " + value + "개");
        }
    }
    public void printROI(double roi){
        System.out.println("총 수익률은 " + roi + "%입니다.");
    }
}
