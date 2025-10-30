package lotto;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public void printMoneyQuestion() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
}
