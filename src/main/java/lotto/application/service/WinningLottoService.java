package lotto.application.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.application.port.in.WinningLottoUseCase;
import lotto.application.port.out.WinningLottoRepository;
import lotto.domain.ErrorMessage;
import lotto.domain.WinningLotto;

public class WinningLottoService implements WinningLottoUseCase {
    private final WinningLottoRepository winningLottoRepository;

    public WinningLottoService(WinningLottoRepository winningLottoRepository) {
        this.winningLottoRepository = winningLottoRepository;
    }

    @Override
    public void setupWinningLotto(String winningNumbers, String bonusNumber){
        List<Integer> numbers = parseNumbers(winningNumbers);
        int bonus = parseBonus(bonusNumber);

        WinningLotto winningLotto = new WinningLotto(1, numbers, bonus);

        winningLottoRepository.save(winningLotto);
    }

    private List<Integer> parseNumbers(String str) {
        try {
            return Stream.of(str.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_LOTTO_NOT_NUMBER.getMessage());
        }
    }

    private int parseBonus(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_NOT_NUMBER.getMessage());
        }
    }
}
