package lotto.adapter.out;

import java.util.HashMap;
import java.util.Map;
import lotto.adapter.out.dto.LottoApiResponseDto;
import lotto.application.port.out.WinningNumberPort;
import lotto.domain.ErrorMessage;
import lotto.domain.WinningLotto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LottoApiAdapter implements WinningNumberPort {

    private static final String API_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";
    private static final LocalDate START_DATE = LocalDate.of(2002, 12, 7);
    private static final LocalTime DRAW_TIME = LocalTime.of(20, 45);

    private final HttpClient client;

    public LottoApiAdapter() {
        this.client = HttpClient.newHttpClient();
    }

    @Override
    public WinningLotto getLatestWinningLotto() {
        int latestRound = calculateLatestRound();
        return getWinningLotto(latestRound); // 계산된 회차로 재호출
    }

    @Override
    public WinningLotto getWinningLotto(int round) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + round))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            Map<String, String> jsonMap = parseJsonToMap(responseBody);

            LottoApiResponseDto dto = LottoApiResponseDto.from(jsonMap);

            if (dto.isFail()) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_LOTTO_AUTO_FAIL.getMessage());
            }

            return dto.toDomain();
        } catch (IOException | InterruptedException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_LOTTO_API_FAIL.getMessage());
        }
    }

    private int calculateLatestRound() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        LocalTime time = now.toLocalTime();

        if (today.getDayOfWeek() == DayOfWeek.SATURDAY && time.isBefore(DRAW_TIME)) {
            today = today.minusDays(1);
        }

        long daysBetween = ChronoUnit.DAYS.between(START_DATE, today);

        return (int) (daysBetween / 7) + 1;
    }

    private Map<String, String> parseJsonToMap(String json) {
        Map<String, String> map = new HashMap<>();
        String cleanJson = json.replace("{", "").replace("}", "");
        String[] pairs = cleanJson.split(",");

        for (String pair : pairs) {
            String[] entry = pair.split(":");
            if (entry.length < 2) continue; // 안전장치

            String key = entry[0].trim().replace("\"", "");
            String value = entry[1].trim().replace("\"", "");
            map.put(key, value);
        }
        return map;
    }
}