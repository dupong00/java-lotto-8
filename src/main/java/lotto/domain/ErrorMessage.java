package lotto.domain;

public enum ErrorMessage {
    INVALID_PURCHASE_NOT_NUMBER("구입 금액은 숫자로만 입력 가능합니다."),
    INVALID_PURCHASE_NOT_MIN_ORDER("구입 금액은 1000원 이상 입력 가능합니다."),
    INVALID_PURCHASE_NOT_UNIT("구입 금액은 1000원 단위로 입력 가능합니다.(ex. 1000, 2000, ..."),

    INVALID_LOTTO_RANGE("로또 번호는 1에서 45 사이의 숫자만 가능합니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_DUPLICATE("로또 번호는 중복 될 수 없습니다."),

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
