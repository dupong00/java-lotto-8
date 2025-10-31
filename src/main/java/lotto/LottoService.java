package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
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

    public Map<RANK, Integer> countingLotto(List<Lotto> lottos, List<Integer> winningLottos, int bonusNumber) {
        Map<RANK, Integer> winningCount = new EnumMap<>(RANK.class);
        for (RANK rank : RANK.values()) {
            winningCount.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = lotto.getMatchCount(winningLottos);
            boolean isBonus = lotto.hasBonus(bonusNumber);
            RANK myrank = RANK.valueOf(matchCount, isBonus);

            winningCount.put(myrank, winningCount.get(myrank) + 1);
        }
        return winningCount;
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
