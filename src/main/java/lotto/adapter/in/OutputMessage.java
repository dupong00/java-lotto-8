package lotto.adapter.in;

enum OutputMessage {
    PURCHASE_MONEY("구입금액을 입력해 주세요."),
    WINNING_LOTTO("\n당첨번호를 입력해 주세요."),
    BONUS_NUMBER("\n보너스번호를 입력해 주세요."),

    MANUAL_COUNT("\n수동 구매 개수를 입력해 주세요. 자동으로만 구매하고 싶으시면 0을 입력해주세요."),
    MANUAL_INPUT("\n수동 로또 번호를 입력해 주세요. (%d/%d)"),

    PURCHASE_MANUAL_COUNT("\n수동 %d개를 구매했습니다."),
    PURCHASE_AUTO_COUNT("자동 %d개를 구매했습니다."),
    STATISTICS_HEADER("\n당첨 통계\n---"),

    WINNING_LOTTO_STATUS("\n당첨 번호를 외부 API에서 자동으로 가져오시겠습니까? (y/n)"),
    WINNING_LOTTO_ROUND("\n가져올 로또 회차를 입력해 주세요. (예: 1000) 0 입력시 최신 번호를 가져옵니다."),
    WINNING_LOTTO_AUTO_SUCCESS("%d 회차 당첨 번호를 성공적으로 불러왔습니다."),
    WINNING_LOTTO_NUMBERS("당첨 번호: %s"),
    WINNING_LOTTO_BONUS_NUMBER("보너스 번호: %d"),

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