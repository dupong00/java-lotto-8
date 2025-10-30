package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoService {

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

    public double calculateROI(Map<RANK, Integer> winningCounts){
        double total = 0;
        int count = 0;
        for (Map.Entry<RANK, Integer> entry : winningCounts.entrySet()){
            RANK key = entry.getKey();

            int prize = key.getPrizeMoney();
            int matchCount = entry.getValue();
            count += matchCount;

            total += matchCount * prize;
        }
        total /= count * 1000;
        total = Math.round(total * 10000) / 100.0;
        return total;

    }
}
