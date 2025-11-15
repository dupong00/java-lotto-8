package lotto.domain;

public enum ErrorMessage {
    INVALID_LOTTO_RANGE("로또 번호는 1에서 45 사이의 숫자만 가능합니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_DUPLICATE("로또 번호는 중복 될 수 없습니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
