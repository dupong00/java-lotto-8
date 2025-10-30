package lotto;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public static void printMoneyQuestion() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
}
