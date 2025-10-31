package lotto;

public class Application {
    public static void main(String[] args) {
        //객체 생성
        LottoService lottoService = new LottoService();
        Validator validator = new Validator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Controller controller = new Controller(inputView,outputView, validator, lottoService);

        controller.run();
    }
}