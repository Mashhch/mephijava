package hw6;

import java.time.LocalDateTime;

public record Transaction(String bankName,
                          LocalDateTime date,
                          double fromCurrencyAmount,
                          double toCurrencyAmount,
                          String fromCurrency,
                          String toCurrency) {

    //компактный конструктор
    public Transaction {
        if (fromCurrencyAmount < 0 || toCurrencyAmount < 0)
            throw new IllegalArgumentException();

    }
}
