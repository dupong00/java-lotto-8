package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printMoneyQuestion() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printCount(int count) {
        System.out.println();
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
    }
}
