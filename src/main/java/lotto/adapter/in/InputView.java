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

    public String readManualCount(){
        OutputMessage.MANUAL_COUNT.print();
        return Console.readLine();
    }

    public String readManualNumber(){
        return Console.readLine();
    }

    public String readAutoWinningLotto(){
        OutputMessage.WINNING_LOTTO_STATUS.print();
        return Console.readLine();
    }
    public String readWinningLottoRound(){
        OutputMessage.WINNING_LOTTO_ROUND.print();
        return Console.readLine();
    }
}
