package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {
    @ParameterizedTest
    @DisplayName("유효하지 않은 구매 금액 입력 시 예외 발생")
    @ValueSource(strings = {"오천원", "200", "1200"})
    public void validateBuyLotto_InvalidInput(String input) {
        Validator validator = new Validator();

        assertThatThrownBy(() -> validator.validateBuyLotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("유효하지 않은 구매 금액 입력 시 예외 발생")
    @ValueSource(strings = {"a,2,3,4,5,6", "46,1,2,3,4, 5", "-1,1,2,3,4,5"})
    public void validateWinningLottoToParse(String input) {
        Validator validator = new Validator();

        assertThatThrownBy(() -> validator.validateBuyLotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("유효하지 않은 구매 금액 입력 시 예외 발생")
    @ValueSource(strings = {"a", "46", "-1"})
    public void validateBonusNumber(){
        String input = "오천원";
        Validator validator = new Validator();

        assertThatThrownBy(() -> validator.validateBuyLotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
