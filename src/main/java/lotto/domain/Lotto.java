package lotto.domain;

import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> setNumbers = Set.copyOf(numbers);
        if (setNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_DUPLICATE.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if(number < 1 || 45 < number) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_RANGE.getMessage());
            }
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE.getMessage());
        }
    }


    // TODO: 추가 기능 구현
}
