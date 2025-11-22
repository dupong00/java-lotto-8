package lotto.domain;

public enum ErrorMessage {
    INVALID_PURCHASE_NOT_NUMBER("구입 금액은 숫자로만 입력 가능합니다."),
    INVALID_PURCHASE_NOT_MIN_ORDER("구입 금액은 1000원 이상 입력 가능합니다."),
    INVALID_PURCHASE_NOT_UNIT("구입 금액은 1000원 단위로 입력 가능합니다.(ex. 1000, 2000, ..."),

    INVALID_MANUAL_NOT_NUMBER("구매 개수는 숫자로만 입력 가능합니다."),
    INVALID_MANUAL_PURCHASE_OVER("구입 금액보다 수동 로또 장수가 더 많습니다."),

    INVALID_LOTTO_NOT_NUMBER("로또 번호는 숫자여야 합니다."),
    INVALID_LOTTO_RANGE("로또 번호는 1에서 45 사이의 숫자만 가능합니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_DUPLICATE("로또 번호는 중복 될 수 없습니다."),

    INVALID_WINNING_LOTTO_ROUND("유효하지 않은 로또 회차입니다."),
    INVALID_WINNING_LOTTO_NOT_NUMBER("당첨 번호는 숫자여야 합니다."),
    INVALID_WINNING_AUTO_LOTTO("y 또는 n 만 입력가능합니다."),
    INVALID_WINNING_LOTTO_AUTO_FAIL("해당 회차 정보를 가져올 수 없습니다"),
    INVALID_WINNING_LOTTO_API_FAIL("API 연결에 실패했습니다."),

    INVALID_BONUS_NOT_NUMBER("보너스 번호는 숫자여야 합니다."),
    INVALID_BONUS_RANGE("보너스 번호는 1에서 45 사이의 숫자만 가능합니다."),
    INVALID_BONUS_DUPLICATE("보너스 번호는 로또 번호와 중복 될 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
