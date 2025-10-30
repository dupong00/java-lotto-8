package lotto;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final int MIN_ORDER_UNIT = 1000;

    public int readBuyLotto(){
        String input = Console.readLine();
        int money;
        try{
            money = Integer.parseInt(input);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 금액은 숫자만 입력가능합니다.");
        }

        if (money < MIN_ORDER_UNIT){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 이상이어야 합니다.");
        }

        if (money % MIN_ORDER_UNIT != 0){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위에 맞춰 구매 가능합니다");
        }

        return money / MIN_ORDER_UNIT;
    }
}