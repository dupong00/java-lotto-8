package lotto.adapter.in;

enum OutputMessage {
    PURCHASE_MONEY("구입금액을 입력해 주세요."),
    WINNING_LOTTO("\n당첨번호를 입력해 주세요."),
    BONUS_NUMBER("\n보너스번호를 입력해 주세요.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public void print(){
        System.out.printf(this.message + "\n");
    }
}