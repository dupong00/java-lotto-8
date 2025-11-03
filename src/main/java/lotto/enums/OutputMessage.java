package lotto.enums;

public enum OutputMessage {
    MONEY_QUESTION("구입금액을 입력해 주세요."),
    WINNING_LOTTO_QUESTION("\n당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_QUESTION("\n보너스 번호를 입력해 주세요."),

    COUNT_MESSAGE("\n%d개를 구매했습니다."),
    STATUS_MESSAGE("\n당첨 통계\n---"),
    ROI_MESSAGE("총 수익률은 %.1f%%입니다."),

    RANK_STANDARD("%d개 일치 (%s원) - %d개"),
    RANK_SECOND("%d개 일치, 보너스 볼 일치 (%s원) - %d개"),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
