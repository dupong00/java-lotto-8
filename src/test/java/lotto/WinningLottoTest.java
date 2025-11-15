package lotto;
import lotto.domain.ErrorMessage;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    private final List<Integer> validMainNumbers = List.of(1, 2, 3, 4, 5, 6);

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다")
    void bonusNumberOutOfRange(int bonusNumber) {
        assertThatThrownBy(() -> new WinningLotto(1, validMainNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    void bonusNumberDuplicate() {
        // 보너스 번호 6이 당첨 번호 6과 중복
        assertThatThrownBy(() -> new WinningLotto(1, validMainNumbers, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}