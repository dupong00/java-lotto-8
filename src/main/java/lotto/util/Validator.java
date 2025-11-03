package lotto.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.enums.ErrorMessage;

public class Validator {
    private static final int MIN_ORDER_UNIT = 1000;
    private static final int MIN_NUM= 1;
    private static final int MAX_NUM = 45;

    public int validateBuyLotto(String input) {
        int money;
        try{
            money = Integer.parseInt(input);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_NUMBER.getMessage());
        }

        if (money < MIN_ORDER_UNIT){
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_MIN_ORDER.getMessage());
        }

        if (money % MIN_ORDER_UNIT != 0){
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_MIN_UNIT.getMessage());
        }

        return money / MIN_ORDER_UNIT;
    }

    public void validateWinningLotto(List<Integer> winningLotto) {
        validateNumberSize(winningLotto);
        validateUniqueNumber(winningLotto);
        for(int number : winningLotto){
            validateNumberRange(number);
        }
    }

    public int validateBonusNumber(String input, List<Integer> winningLottos) {
        int bonusNumber;
        try{
             bonusNumber = Integer.parseInt(input);
             validateNumberRange(bonusNumber);
             validateNotDuplicationNumber(bonusNumber, winningLottos);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_NUMBER.getMessage());
        }
        return bonusNumber;
    }

    private void validateNumberRange(int number) {
        if (MIN_NUM > number || number > MAX_NUM){
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }
    private void validateNotDuplicationNumber(int bonusNumber, List<Integer> winningLottos) {
        for(int number:winningLottos){
            if (number == bonusNumber){
                throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
            }
        }
    }
    private void validateUniqueNumber(List<Integer> winningLotto) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for(int number:winningLotto){
            if(uniqueNumbers.contains(number)){
                throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
            }
            uniqueNumbers.add(number);
        }
    }
    private void validateNumberSize(List<Integer> winningLotto) {
        if(winningLotto.size() != 6){
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }
    }
}
