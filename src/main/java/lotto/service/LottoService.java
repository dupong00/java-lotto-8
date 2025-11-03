package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.enums.ErrorMessage;
import lotto.domain.RANK;
import lotto.domain.Lotto;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;

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

        double rate = (total / (count * LOTTO_PRICE)) * 100;
        return Math.round(rate * 10) / 10.0;
    }

    public List<Integer> parse(String input){
        String[] parts = input.trim().split(",");
        List<Integer> winningLotto = new ArrayList<>();
        for (String  part : parts){
            if (part == null || part.trim().isEmpty()) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_BLANK.getMessage());
            }
            try{
                int number = Integer.parseInt(part);
                winningLotto.add(number);
            }catch (NumberFormatException e){
                throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER.getMessage());
            }

        }
        return winningLotto;
    }

}
