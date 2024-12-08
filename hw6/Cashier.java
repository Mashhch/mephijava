package hw6;

import hw6.Exceptions.InsufficientFundsException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Cashier extends Thread {

    private final BlockingQueue<Callable<String>> transactionQueue; // Очередь транзакций
    private final String cashierID;

    public Cashier(BlockingQueue<Callable<String>> transactionQueue, String cashierID) {
        this.transactionQueue = transactionQueue;
        this.cashierID = cashierID;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Callable<String> task = transactionQueue.take();
                FutureTask<String> futureTask = new FutureTask<>(task);
                futureTask.run();

                String result = futureTask.get();
                System.out.println("Cashier " + cashierID + " finished task: " + result);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Cashier " + cashierID + " interrupted.");
        } catch (ExecutionException e) {
            System.err.println("Task execution failed: " + e.getMessage());
        } catch (InsufficientFundsException e){
            System.err.println("Insufficient funds: " + e.getMessage());
        }
    }

}
