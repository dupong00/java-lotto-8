package lotto.application.port.out;

import java.util.List;
import lotto.domain.Lotto;

public interface LottoRepository {
    void save(Lotto lotto);
    void saveAll(List<Lotto> lottos);
    List<Lotto> findAll();
}