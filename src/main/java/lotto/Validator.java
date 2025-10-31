package lotto;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private static final int MIN_ORDER_UNIT = 1000;
    private static final int MIN_NUM= 1;
    private static final int MAX_NUM = 45;
    private static final String ERROR = "[ERROR] ";

    public int validateBuyLotto(String input) {
        int money;
        try{
            money = Integer.parseInt(input);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ERROR + "금액은 숫자만 입력가능합니다.");
        }

        if (money < MIN_ORDER_UNIT){
            throw new IllegalArgumentException(ERROR + "구입 금액은 1000원 이상이어야 합니다.");
        }

        if (money % MIN_ORDER_UNIT != 0){
            throw new IllegalArgumentException(ERROR + "구입 금액은 1000원 단위에 맞춰 구매 가능합니다");
        }

        return money / MIN_ORDER_UNIT;
    }

    public List<Integer> validateWinningLottoToParse(String input) {
        String[] parts = input.trim().split(",");
        List<Integer> winningLottos = new ArrayList<>();

        try {
            for(String part : parts){
                int number = Integer.parseInt(part.trim());
                validateNumberRange(number);
                winningLottos.add(number);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR + "당첨 번호는 숫자만 입력 가능합니다.");
        }


        return winningLottos;
    }

    public int validateBonusNumber(String input, List<Integer> winningLottos) {
        int bonusNumber;

        try{
             bonusNumber = Integer.parseInt(input);
             validateNumberRange(bonusNumber);
             validateNotDuplicationNumber(bonusNumber, winningLottos);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ERROR + "숫자만 입력 가능합니다.");
        }

        return bonusNumber;
    }

    private void validateNumberRange(int number) {
        if (MIN_NUM > number || number > MAX_NUM){
            throw new IllegalArgumentException(ERROR + "로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
    private void validateNotDuplicationNumber(int bonusNumber, List<Integer> winningLottos) {
        for(int number:winningLottos){
            if (number == bonusNumber){
                throw new IllegalArgumentException(ERROR + "보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            }
        }
    }
}
