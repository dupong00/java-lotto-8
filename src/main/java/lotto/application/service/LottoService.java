package lotto.application.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lotto.application.port.in.LottoPurchaseUseCase;
import lotto.application.port.out.LottoRepository;
import lotto.domain.Lotto;
import lotto.domain.Money;

public class LottoService implements LottoPurchaseUseCase {
    private final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    @Override
    public List<Lotto> purchaseLottos(int amount, List<List<Integer>> manualNumbers){
        Money money =  new Money(amount);

        List<Lotto> manualLottos = manualNumbers.stream()
                .map(Lotto::new)
                .toList();

        Money remainingMoney = money.spend(manualLottos.size());
        int autoCount = remainingMoney.calculateTicketCount();

        List<Lotto> autoLottos = Stream.generate(this::generateAutoLotto)
                .limit(autoCount)
                .toList();

        List<Lotto> allLottos = new ArrayList<>(manualLottos);
        allLottos.addAll(autoLottos);

        lottoRepository.saveAll(allLottos);

        return allLottos;
    }

    private Lotto generateAutoLotto() {
        List<Integer> numbers =  Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

}
