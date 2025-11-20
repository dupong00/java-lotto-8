package lotto.adapter.in;

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
}
