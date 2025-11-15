package lotto.domain;

import java.util.List;

public class WinningLotto {
    private Lotto lotto;
    private final int bonusNumber;
    private final int round;

    public WinningLotto(int round, List<Integer> numbers, int bonusNumber) {
        this.round = round;
        this.lotto = new Lotto(numbers);
        this.bonusNumber = bonusNumber;

        validateBonusNumber(bonusNumber);
    }

    private void validateBonusNumber(int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicate(bonusNumber);
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_RANGE.getMessage());
        }
    }

    private void validateBonusNumberDuplicate(int bonusNumber) {
        if (lotto.contains(bonusNumber)){
                throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_DUPLICATE.getMessage());
        }
    }

    public RANK determineRANK(Lotto userLotto) {
        int matchCount = this.lotto.calculateMatchCount(userLotto);

        boolean bonusNumber = userLotto.contains(this.bonusNumber);

        return RANK.valueOf(matchCount, bonusNumber);
    }

}
