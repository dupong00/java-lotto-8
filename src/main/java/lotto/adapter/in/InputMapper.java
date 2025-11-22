package lotto.adapter.in;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.ErrorMessage;

public class InputMapper {
    public int parseIntMoney(String input) {
        int money;
        try{
            money = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_NOT_NUMBER.getMessage());
        }
        return money;
    }

    public int parseIntManualCount(String input) {
        int count;
        try{
            count = Integer.parseInt(input);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_MANUAL_NOT_NUMBER.getMessage());
        }
        return count;
    }

    public List<Integer> toLottoList(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NOT_NUMBER.getMessage());
        }
    }

    public int parseIntRound(String input) {
        int round;
        try{
            round = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_LOTTO_ROUND.getMessage());
        }
        return round;
    }

}
