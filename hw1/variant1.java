package hw1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;


public class variant1 {

    public static int[] generateIntArray(int size, int min, int max) {
        return IntStream.generate(() -> min + (int) (Math.random() * (max - min)))
                .limit(size)
                .toArray();
    }

    public static double[] generateDoubleArray(int size, double min, double max) {
        return DoubleStream.generate(() -> min + (Math.random() * (max - min)))
                .limit(size)
                .toArray();
    }

    public static int getMax(int[] array) {
        return Arrays.stream(array).max().orElseThrow();
    }

    public static double getMax(double[] array) {
        return Arrays.stream(array).max().orElseThrow();
    }

    public static int getMin(int[] array) {
        return Arrays.stream(array).min().orElseThrow();
    }

    public static double getMin(double[] array) {
        return Arrays.stream(array).min().orElseThrow();
    }

    public static double getAverage(int[] array) {
        return Arrays.stream(array).average().orElseThrow();
    }

    public static double getAverage(double[] array) {
        return Arrays.stream(array).average().orElseThrow();
    }

    public static int[] sortArray(int[] array) {
        return RadixSort.sort(array);
    }

    public static int[] sortArrayDescending(int[] array) {
        return RadixSort.sortDescending(array);
    }

    public static double[] sortArray(double[] array) {
        BubbleSort.BubbleSort(array);
        return array;
    }

    public static double[] sortArrayDescending(double[] array) {
        BubbleSort.BubbleSortDescending(array);
        return array;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер массива:");
        int size = scanner.nextInt();
        if (size <= 0) {
            System.out.println("Размер массива должен быть натуральным числом.");
            return;
        }

        System.out.println("Введите минимально возможное число в массиве:");
        int min = scanner.nextInt();
        System.out.println("Введите максимально возможное число в массиве:");
        int max = scanner.nextInt();

        if (min >= max) {
            System.out.println("Минимальное число не может превышать максимальное.");
            return;
        }

        System.out.println("Выберите 1 для int, 2 для double:");
        int choice = scanner.nextInt();

        if (choice == 1) {
            int[] intArray = generateIntArray(size, min, max);
            PrintInfo(Arrays.toString(intArray), getMax(intArray), getMin(intArray), getAverage(intArray), Arrays.toString(sortArray(intArray)), Arrays.toString(sortArrayDescending(intArray)));

        } else if (choice == 2) {
            double[] doubleArray = generateDoubleArray(size, min, max);
            PrintInfo(Arrays.toString(doubleArray), getMax(doubleArray), getMin(doubleArray), getAverage(doubleArray), Arrays.toString(sortArray(doubleArray)), Arrays.toString(sortArrayDescending(doubleArray)));

        } else {
            System.out.println("Неверный ввод.");
        }
    }

    private static void PrintInfo(String string, double max2, double min2, double average, String sorted, String sortedDesc) {
        System.out.println("Массив чисел: " + string);
        System.out.println("Max: " + max2);
        System.out.println("Min: " + min2);
        System.out.println("Среднее: " + average);
        System.out.println("Отсортированный массив по возрастанию:");
        System.out.println(sorted);
        System.out.println("Отсортированный массив по убыванию:");
        System.out.println(sortedDesc);
    }

}
