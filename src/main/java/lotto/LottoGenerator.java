package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {

    public List<Lotto> generateLottos(int count){
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++){
            Lotto lotto = new Lotto(generateNumbers());
            lottos.add(lotto);
        }

        return lottos;
    }

    private List<Integer> generateNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
