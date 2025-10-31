package lotto;

public enum RANK {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);
    private final int matchCount;
    private final int prizeMoney;

    RANK(int matchCount, int prizeMoney){
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
    public int getMatchCount() {
        return matchCount;
    }
    public static RANK valueOf(int matchCount, boolean bonusMatch){
        if (matchCount == 6) {
            return RANK.FIRST;
        }
        if (matchCount == 5) {
            if (bonusMatch) {
                return RANK.SECOND;
            }
            return RANK.THIRD;
        }
        if (matchCount == 4) {
            return RANK.FOURTH;
        }
        if (matchCount == 3) {
            return RANK.FIFTH;
        }
        return RANK.MISS;
    }
}
