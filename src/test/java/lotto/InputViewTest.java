package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputViewTest {

    private InputStream originalIn;

    @BeforeEach
    void setUp(){
        originalIn = System.in;
    }

    @AfterEach
    void tearDown(){
        System.setIn(originalIn);
        Console.close();
    }

    private void setFakeInput(String input) {
        InputStream fakeIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(fakeIn);
    }

    @Test
    @DisplayName("구매 금액에 숫자가 아닌 값을 입력한 경우")
    public void readBuyLotto_NotNumber()
    {
        String input = "오천원";
        setFakeInput(input);

        assertThatThrownBy(InputView::readBuyLotto)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매 금액이 최소 금액보다 작은 경우")
    public void readBuyLotto_LessMinMoney()
    {
        String input = "120";
        setFakeInput(input);

        assertThatThrownBy(InputView::readBuyLotto)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위에 맞지 않는 경우")
    public void readBuyLotto_NotMatchUnit()
    {
        String input = "1200";
        setFakeInput(input);

        assertThatThrownBy(InputView::readBuyLotto)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
