package lotto.adapter.in;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readPurchaseMoney(){
        OutputMessage.PURCHASE_MONEY.print();
        return Console.readLine();
    }

    public String readWinningLotto(){
        OutputMessage.WINNING_LOTTO.print();
        return Console.readLine();
    }

    public String readBonusNumber(){
        OutputMessage.BONUS_NUMBER.print();
        return Console.readLine();
    }
}
