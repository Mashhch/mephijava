package hw6;

import hw6.Logs.Logger;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("bank1");
        Random rand = new Random();
        try {
            bank.addObserver(new Logger());
            bank.addObserver(new Logger());
            bank.addObserver(new Logger());
            for (int i = 0; i < 10; i++) {
                boolean hasError = false;
                try {
                    bank.addClient();
                    bank.increaseBalance(i, rand.nextInt(1000), "USD");
                    bank.decreaseBalance(i, rand.nextInt(1000), "USD");
                    bank.increaseBalance(i, rand.nextInt(1000), "RUB");
                    bank.increaseBalance(i, rand.nextInt(100), "EURO");
                    bank.decreaseBalance(i, rand.nextInt(100), "EURO");
                    bank.decreaseBalance(i, rand.nextInt(100), "RUB");

                    bank.exchangeCurrency(i, new Pair<>("USD", "RUB"), 100);
                    bank.exchangeCurrency(i, new Pair<>("EURO", "RUB"), 200);
                    bank.transferFunds(i, 0, rand.nextInt(5), "USD");
                } catch (Exception e) {
                    hasError = true;
                    System.out.println("Error for client " + i + ": " + e.getMessage());
                }
                if (!hasError) {
                    System.out.println("Successfully processed client " + i);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in main execution: " + e.getMessage());
        }
    }
}
