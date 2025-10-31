package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final Validator validator;
    private final LottoService lottoService;

    public Controller(InputView inputView, OutputView outputView, Validator validator, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validator = validator;
        this.lottoService = lottoService;
    }

    public void run(){
        int count = buyLotto();

        outputView.printCount(count);

        List<Lotto> lottos = generateLotto(count);

        List<Integer> winningLotto = winningLotto();

        int bonusNum = bonusNumber(winningLotto);

        Map<RANK, Integer> winningLottos = lottoService.countingLotto(lottos,winningLotto,bonusNum);

        printStatus(winningLottos);
    }

    private int buyLotto(){
        try{
            outputView.printMoneyQuestion();
            return validator.validateBuyLotto(inputView.read());
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private List<Lotto> generateLotto(int count){
        List<Lotto> lottos = lottoService.generateLottos(count);
        outputView.printLottos(lottos);

        return lottos;
    }

    private List<Integer> winningLotto(){
        while(true){
            try{
                outputView.printWinningLottoQuestion();
                List<Integer> winningLotto = lottoService.parse(inputView.read());
                validator.validateWinningLotto(winningLotto);

                return winningLotto;
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private int bonusNumber(List<Integer> winningLotto){
        while(true){
            try{
                outputView.printBonusNumberQuestion();
                int bonusNumber = validator.validateBonusNumber(inputView.read(), winningLotto);

                return bonusNumber;
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void printStatus(Map<RANK,Integer> winningCount){
        outputView.printTotalWinningStatus(winningCount);
        double roi = lottoService.calculateROI(winningCount);
        outputView.printROI(roi);
    }
}
