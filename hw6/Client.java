package hw6;

import hw6.Exceptions.InsufficientFundsException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Client {


    private final int clientId;
    private final Map<String, Double> balances;
//    private final List<Transaction> transactionHistory;

    public Client(int clientId) {
        this.clientId = clientId;
        this.balances = new HashMap<>();
//        this.transactionHistory = new LinkedList<>();
    }

    public void exchangeCurrency(Pair<String, String> fromCurrencytoCurrency, double fromCurrencyAmount, double toCurrencyAmount, String bankName, LocalDateTime date) {

        if (!hasBalance(fromCurrencytoCurrency.getFirst(), fromCurrencyAmount)) {
            throw new InsufficientFundsException("Insufficient funds in " + fromCurrencytoCurrency.getFirst() + " for client " + clientId);
        }

        decreaseBalance(fromCurrencytoCurrency.getFirst(), fromCurrencyAmount);
        increaseBalance(fromCurrencytoCurrency.getSecond(), toCurrencyAmount);
//        System.out.println("Client has been exchanged from " + fromCurrencytoCurrency.getFirst() + " to " + fromCurrencytoCurrency.getSecond() + " Amount:" + toCurrencyAmount);

    }

    public double getBalance(String currency) {
        return balances.get(currency);
    }

    public boolean hasBalance(String fromCurrency, double amount) {
        return balances.getOrDefault(fromCurrency, 0.0) >= amount;
    }

    public void decreaseBalance(String currency, double amount) {
        Double oldAmount = balances.getOrDefault(currency, 0.0);
        balances.put(currency, oldAmount - amount);
//        System.out.println("Client has been decreased from " + oldAmount + " to " + getBalance(currency) + " In currency " + currency);
    }

    public void increaseBalance(String currency, double amount) {
        Double oldAmount = balances.getOrDefault(currency, 0.0);
        balances.put(currency, oldAmount + amount);
//        System.out.println("Client has been increased from " + oldAmount + " to " + getBalance(currency) + " In currency " + currency);
    }

    public static void main(String[] args) {
        if (1 > 2)
            System.out.println(LocalDateTime.now());
    }

//    public void getTransactionHistory() {
//        System.out.println(transactionHistory.toString());
//    }

}
