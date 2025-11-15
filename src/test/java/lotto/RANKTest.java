package lotto;

import lotto.domain.RANK;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RANKTest {
    @DisplayName("일치 개수와 보너스 여부에 따라 정확한 등수를 반환한다")
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "6, true, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, MISS",
            "1, false, MISS",
            "0, false, MISS"
    })
    void determineRank(int matchCount, boolean bonusMatch, RANK expectedRank) {
        RANK actualRank = RANK.valueOf(matchCount, bonusMatch);
        assertThat(actualRank).isEqualTo(expectedRank);
    }
}