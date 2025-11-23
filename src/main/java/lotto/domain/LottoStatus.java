package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoStatus {

    private final Map<RANK, Integer> rankCounts;
    private final Money money;

    private LottoStatus(Map<RANK, Integer> rankCounts, Money money) {
        this.rankCounts = rankCounts;
        this.money = money;
    }

    public Map<RANK, Integer> getRankCounts(){
        return rankCounts;
    };

    public static LottoStatus of(List<Lotto> userLotto, WinningLotto winningLotto, Money money) {
        Map<RANK, Integer> rankCounts = new EnumMap<>(RANK.class);
        for (RANK rank : RANK.values()) {
            rankCounts.put(rank, 0);
        }

        for (Lotto lotto : userLotto) {
            RANK rank = winningLotto.determineRANK(lotto);
            rankCounts.put(rank, rankCounts.get(rank) + 1);
        }

        return new LottoStatus(rankCounts, money);
    }

    public long getTotalPrize(){
        long totalPrize = 0L;

        for (Map.Entry<RANK, Integer> entry : rankCounts.entrySet()) {
            RANK rank = entry.getKey();
            long count = entry.getValue();

            totalPrize += (rank.prize * count);
        }

        return totalPrize;
    }

    public double getRateOfPrize(){
        long totalPrize = getTotalPrize();
        int purchaseMoney = money.getAmount();
        if (purchaseMoney == 0){
            return 0.0;
        }

        double rate = (double) totalPrize / purchaseMoney * 100.0;

        return Math.round(rate * 10.0) / 10.0;
    }
}
