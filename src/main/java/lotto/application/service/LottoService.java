package lotto.application.service;

import camp.nextstep.edu.missionutils.Randoms;
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
    public List<Lotto> purchaseLottos(String amount){
        int money = validatePurchase(amount);

        int count = money / MIN_ORDER_UNIT;

        List<Lotto> autoLottos = Stream.generate(this::generateAutoLotto)
                .limit(count)
                .toList();

        lottoRepository.saveAll(autoLottos);

        return autoLottos;
    }

    private int validatePurchase(String amount){
        int money;

        try{
            money = Integer.parseInt(amount);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_NOT_NUMBER.getMessage());
        }
        if (money < MIN_ORDER_UNIT){
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_NOT_MIN_ORDER.getMessage());
        }
        if (money % MIN_ORDER_UNIT != 0){
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_NOT_UNIT.getMessage());
        }

        return money;
    }

    private Lotto generateAutoLotto() {
        List<Integer> numbers =  Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

}
