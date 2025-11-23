package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_NOT_MIN_ORDER.getMessage());
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_NOT_UNIT.getMessage());
        }
    }

    public Money spend(int ticketCount) {
        int cost = ticketCount * LOTTO_PRICE;
        if (amount < cost) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_OVER.getMessage());
        }
        if (amount - cost == 0) {
            return new Money(0);
        }
        return new Money(amount - cost);
    }

    public int calculateTicketCount() {
        return this.amount / LOTTO_PRICE;
    }
}