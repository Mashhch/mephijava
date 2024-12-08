package hw6.Exceptions;

public class ExchangeRateNotFoundException extends RuntimeException {
    public ExchangeRateNotFoundException(String message) {
        super(message);
    }
}
