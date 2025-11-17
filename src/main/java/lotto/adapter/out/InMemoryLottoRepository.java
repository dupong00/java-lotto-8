package lotto.adapter.out;

import java.util.ArrayList;
import java.util.List;
import lotto.application.port.out.LottoRepository;
import lotto.domain.Lotto;

public class InMemoryLottoRepository implements LottoRepository {
    private final List<Lotto> storage = new ArrayList<>();

    @Override
    public void save(Lotto lotto) {
        storage.add(lotto);
    }

    @Override
    public void saveAll(List<Lotto> lottos){
        storage.addAll(lottos);
    }

    @Override
    public List<Lotto> findAll() {
        return new ArrayList<>(storage);
    }
}
