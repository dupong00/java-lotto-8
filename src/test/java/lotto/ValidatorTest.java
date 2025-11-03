package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"오천원", "200", "1200"})
    public void validateBuyLotto_InvalidInput(String input) {
        Validator validator = new Validator();

        assertThatThrownBy(() -> validator.validateBuyLotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,2,3,4,5,6", "46,1,2,3,4, 5", "-1,1,2,3,4,5"})
    public void validateWinningLotto(String input) {
        Validator validator = new Validator();

        assertThatThrownBy(() -> validator.validateBuyLotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "46", "-1"})
    public void validateBonusNumber(String input){
        Validator validator = new Validator();

        assertThatThrownBy(() -> validator.validateBuyLotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
