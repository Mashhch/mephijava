package hw6;

import hw6.Exceptions.ClientNotFoundException;
import hw6.Exceptions.ExchangeRateNotFoundException;
import hw6.Exceptions.UnsupportedCurrencyException;
import hw6.Interfaces.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank {

    String bankName;
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    ConcurrentHashMap<Integer, Client> clients = new ConcurrentHashMap<>();
    List<Cashier> cashiers = new ArrayList<>();
    ConcurrentHashMap<Pair<String, String>, Double> exchangeRates = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Currency> currencies = new ConcurrentHashMap<>();
    private final List<Observer> observers = new ArrayList<>();
    private final BlockingQueue<Callable<String>> transactionQueue = new LinkedBlockingQueue<>();


    {
        currencies.put("USD", new Currency("USD", "USD"));
        currencies.put("RUB", new Currency("RUB", "RUB"));
        currencies.put("EURO", new Currency("EURO", "EURO"));
        exchangeRates.put(new Pair<>("USD", "RUB"), 100.0);
        exchangeRates.put(new Pair<>("RUB", "USD"), 0.1);
        exchangeRates.put(new Pair<>("EURO", "USD"), 120.0);
        exchangeRates.put(new Pair<>("RUB", "EURO"), 0.50);
    }

    public Bank(String bankName) {

        this.bankName = bankName;
        cashierGenerator();
        exchangeRatesGenerator();

    }

    void cashierGenerator() {
        Random random = new Random();
        for (int i = 0; i < random.nextInt(10); i++) {
            Cashier cashier = new Cashier(transactionQueue, "Cashier-" + i);
            cashiers.add(cashier);
            cashier.start();
        }
    }

    void exchangeRatesGenerator() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        executor.scheduleWithFixedDelay(() -> {
            List<Pair<String, String>> keys = new ArrayList<>(exchangeRates.keySet());

            if (keys.isEmpty()) {
                //System.out.println("No exchange rates available to update.");
                return; // Если нет данных для обновления, пропускаем итерацию
            }

            Pair<String, String> randomPair = keys.get(new Random().nextInt(keys.size()));

            Double randomRate = exchangeRates.get(randomPair);
            if (randomRate == null) {
                System.out.println("Exchange rate for pair " + randomPair + " not found.");
                return;
            }

            createExchangeRatesTask(randomPair, randomRate).run();
        }, 0, 5, TimeUnit.SECONDS);
    }


    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public void addClient() {
        int clientId = idCounter.getAndIncrement();
        clients.put(clientId, new Client(clientId));
    }

    public void exchangeCurrency(int clientId, Pair<String, String> fromCurrencyToCurrency, double fromCurrencyAmount) throws InterruptedException {
        Client client = clients.get(clientId);
        if (client == null) {
            throw new ClientNotFoundException("Client with ID " + clientId + " not found.");
        }

        if (currencies.get(fromCurrencyToCurrency.getFirst()) == null) {
            throw new UnsupportedCurrencyException("Currency " + fromCurrencyToCurrency.getFirst() + " is not supported.");
        }

        if (currencies.get(fromCurrencyToCurrency.getSecond()) == null) {
            throw new UnsupportedCurrencyException("Currency " + fromCurrencyToCurrency.getSecond() + " is not supported.");
        }

        Double rate = exchangeRates.get(fromCurrencyToCurrency);
        if (rate == null) {
            throw new ExchangeRateNotFoundException("Exchange currencies " + fromCurrencyToCurrency.getFirst() + " to " + fromCurrencyToCurrency.getSecond() + " is not supported.");
        }

        Callable<String> task = () -> {
            double toCurrencyAmount = fromCurrencyAmount * rate;
            client.exchangeCurrency(fromCurrencyToCurrency, fromCurrencyAmount, toCurrencyAmount, bankName, LocalDateTime.now());
            return "Exchanged " + fromCurrencyAmount + " " + fromCurrencyToCurrency.getFirst() + " to " +
                    toCurrencyAmount + " " + fromCurrencyToCurrency.getSecond() + " with rate: " + rate +
                    " for client " + clientId;
        };

        transactionQueue.put(task);
    }

    public void transferFunds(int senderClientId, int receiverClientId, double amount, String currency) throws InterruptedException {
        if (currencies.get(currency) == null) {
            throw new UnsupportedCurrencyException("Currency " + currency + " is not supported.");
        }

        Client senderClient = clients.get(senderClientId);
        Client receiverClient = clients.get(receiverClientId);
        if (senderClient == null) {
            throw new ClientNotFoundException("Client with ID " + senderClientId + " not found.");
        }
        if (receiverClient == null) {
            throw new ClientNotFoundException("Client with ID " + receiverClientId + " not found.");
        }

        Callable<String> task = () -> {
            try {
                senderClient.decreaseBalance(currency, amount);
                receiverClient.increaseBalance(currency, amount);

                Thread.sleep(3000); // Симуляция обработки
                return "Transferred " + amount + " " + currency + " from client " + senderClientId + " to client " + receiverClientId;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Task interrupted: " + e.getMessage();
            }
        };

        transactionQueue.put(task);
    }

    public void increaseBalance(int clientId, double amount, String currency) throws InterruptedException {
        if (currencies.get(currency) == null) {
            throw new UnsupportedCurrencyException("Currency " + currency + " is not supported.");
        }

        Client client = clients.get(clientId);
        if (client == null) {
            throw new ClientNotFoundException("Client with ID " + clientId + " not found.");
        }

        Callable<String> task = () -> {
            try {
                client.increaseBalance(currency, amount);
                Thread.sleep(1000); // Симуляция обработки
                return "Increased balance by " + amount + " " + currency + " for client " + clientId;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Task interrupted: " + e.getMessage();
            }
        };

        transactionQueue.put(task);
    }

    public void decreaseBalance(int clientId, double amount, String currency) throws InterruptedException {
        if (currencies.get(currency) == null) {
            throw new UnsupportedCurrencyException("Currency " + currency + " is not supported.");
        }

        Client client = clients.get(clientId);
        if (client == null) {
            throw new ClientNotFoundException("Client with ID " + clientId + " not found.");
        }

        Callable<String> task = () -> {
            try {
                client.decreaseBalance(currency, amount);
                Thread.sleep(1000);
                return "Decreased balance by " + amount + " " + currency + " for client " + clientId;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Task interrupted: " + e.getMessage();
            }
        };

        transactionQueue.put(task);
    }

    public void addCurrency(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    public void updExchangeRates(Pair<String, String> fromCurrencyToCurrency, double rate) {
        Double oldRate = exchangeRates.get(fromCurrencyToCurrency);
        if (oldRate == null) {
            throw new ExchangeRateNotFoundException("Exchange currencies " + fromCurrencyToCurrency.getFirst() + " to " + fromCurrencyToCurrency.getSecond() + " is not supported.");
        }

        exchangeRates.put(fromCurrencyToCurrency, rate);
        notifyObservers("Currency " + fromCurrencyToCurrency.getFirst() + " to " + fromCurrencyToCurrency.getSecond() + " updated to rate " + rate);
    }

    public Runnable createExchangeRatesTask(Pair<String, String> fromCurrencytoCurrency, double baseRate) {
        return () -> {
            double newRate = baseRate + Math.random();
            updExchangeRates(fromCurrencytoCurrency, newRate);
        };
    }

}


