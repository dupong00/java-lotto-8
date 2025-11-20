package lotto.application.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lotto.application.port.in.LottoPurchaseUseCase;
import lotto.application.port.out.LottoRepository;
import lotto.domain.ErrorMessage;
import lotto.domain.Lotto;

public class LottoService implements LottoPurchaseUseCase {
    private final LottoRepository lottoRepository;
    final int MIN_ORDER_UNIT = 1000;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    @Override
    public List<Lotto> purchaseLottos(int money, List<List<Integer>> manualNumbers){
        validatePurchase(money);

        List<Lotto> manualLottos = manualNumbers.stream()
                .map(Lotto::new)
                .toList();

        validateManualPurchase(money, manualLottos.size());

        int remainingMoney = money - (manualLottos.size() * MIN_ORDER_UNIT);
        int autoCount = remainingMoney / MIN_ORDER_UNIT;

        List<Lotto> autoLottos = Stream.generate(this::generateAutoLotto)
                .limit(autoCount)
                .toList();

        List<Lotto> allLottos = new ArrayList<>(manualLottos);
        allLottos.addAll(autoLottos);

        lottoRepository.saveAll(allLottos);

        return allLottos;
    }

    private void validatePurchase(int money){
        if (money < MIN_ORDER_UNIT){
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_NOT_MIN_ORDER.getMessage());
        }
        if (money % MIN_ORDER_UNIT != 0){
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_NOT_UNIT.getMessage());
        }
    }

    private void validateManualPurchase(int money, int size){
        int manualCost = size * MIN_ORDER_UNIT;
        if (money < manualCost) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MANUAL_PURCHASE_OVER.getMessage());
        }
    }

    private Lotto generateAutoLotto() {
        List<Integer> numbers =  Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

}
