package lotto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final String ERROR = "ERROR ";
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        validateUniqueNumber(numbers);

        List<Integer> sortedNumbers = new ArrayList<>(numbers);

        Collections.sort(sortedNumbers);

        this.numbers = sortedNumbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR + "로또 번호는 6개여야 합니다.");
        }
    }

    private void validateUniqueNumber(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        for(int number:numbers){
            if(uniqueNumbers.contains(number)){
                throw new IllegalArgumentException(ERROR + "당첨 번호는 중복될 수 없습니다.");
            }
            uniqueNumbers.add(number);
        }
    }

    public  List<Integer> getNumbers() {
        return numbers;
    }

    public int getMatchCount(List<Integer> winningLottos) {
        int currectCount = 0;

        for(int lottoNumber: winningLottos) {
            if (numbers.contains(lottoNumber)){
                currectCount ++;
            }
        }
        return currectCount;
    }

    public boolean hasBonus(int bonusNumber){
        return this.numbers.contains(bonusNumber);
    }
}
