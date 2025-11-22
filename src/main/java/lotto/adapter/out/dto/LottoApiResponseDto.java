package lotto.adapter.out.dto;

import lotto.domain.WinningLotto;
import java.util.List;
import java.util.Map;

public record LottoApiResponseDto(
        int drwtNo1,
        int drwtNo2,
        int drwtNo3,
        int drwtNo4,
        int drwtNo5,
        int drwtNo6,
        int bnusNo,
        int drwNo,
        String returnValue
) {
    public static LottoApiResponseDto from(Map<String, String> map) {
        return new LottoApiResponseDto(
                Integer.parseInt(map.get("drwtNo1")),
                Integer.parseInt(map.get("drwtNo2")),
                Integer.parseInt(map.get("drwtNo3")),
                Integer.parseInt(map.get("drwtNo4")),
                Integer.parseInt(map.get("drwtNo5")),
                Integer.parseInt(map.get("drwtNo6")),
                Integer.parseInt(map.get("bnusNo")),
                Integer.parseInt(map.get("drwNo")),
                map.get("returnValue")
        );
    }

    public WinningLotto toDomain() {
        List<Integer> numbers = List.of(drwtNo1, drwtNo2, drwtNo3, drwtNo4, drwtNo5, drwtNo6);
        return new WinningLotto(drwNo, numbers, bnusNo);
    }

    public boolean isFail() {
        return "fail".equals(returnValue);
    }
}