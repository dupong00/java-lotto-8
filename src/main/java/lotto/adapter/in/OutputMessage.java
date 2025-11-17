package lotto.adapter.in;

enum OutputMessage {
    PURCHASE_MONEY("구입금액을 입력해 주세요."),
    WINNING_LOTTO("\n당첨번호를 입력해 주세요."),
    BONUS_NUMBER("\n보너스번호를 입력해 주세요."),

    PURCHASE_COUNT("\n%d개를 구매했습니다."),
    STATISTICS_HEADER("\n당첨 통계\n---"),

    RANK_RESULT("%d개 일치 (%,d원) - %d개"),
    RANK_RESULT_SECOND("5개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    ROI("총 수익률은 %.1f%%입니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void print(Object... args) {
        System.out.printf(this.message + "\n", args);
    }

    public void print(){
        System.out.printf(this.message + "\n");
    }
}