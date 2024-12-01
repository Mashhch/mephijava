package hw1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class ArrayExtend {

    public static Integer[] generateIntArray(int size, int min, int max) {
        return IntStream.generate(() -> min + (int) (Math.random() * (max - min)))
                .limit(size)
                .boxed()
                .toArray(Integer[]::new);
    }

    public static Double[] generateDoubleArray(int size, double min, double max) {
        return DoubleStream.generate(() -> min + (Math.random() * (max - min)))
                .limit(size)
                .boxed()
                .toArray(Double[]::new);
    }


    public static <T extends Number & Comparable<T>> T getMax(T[] array) {
        return Arrays.stream(array)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("Массив пуст."));
    }


    public static <T extends Number & Comparable<T>> T getMin(T[] array) {
        return Arrays.stream(array)
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("Массив пуст."));
    }

    public static <T extends Number> double getAverage(T[] array) {
        return Arrays.stream(array)
                .mapToDouble(Number::doubleValue)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Массив пуст."));
    }


    public static Integer[] sortArray(Integer[] array) {
        return RadixSort.sort(array);
    }

    public static Integer[] sortArrayDescending(Integer[] array) {
        return RadixSort.sortDescending(array);
    }

    public static Double[] sortArray(Double[] array) {
        BubbleSort.BubbleSort(array);
        return array;
    }

    public static Double[] sortArrayDescending(Double[] array) {
        BubbleSort.BubbleSortDescending(array);
        return array;
    }

    static <T extends Number & Comparable<T>> T[] SortedArray(T[] array, boolean ascending) {
        if (array instanceof Integer[]) {
            return (T[]) (ascending ? sortArray((Integer[]) array) : sortArrayDescending((Integer[]) array));
        } else if (array instanceof Double[]) {
            return (T[]) (ascending ? sortArray((Double[]) array) : sortArrayDescending((Double[]) array));
        }

        throw new IllegalArgumentException("Неверный тип данных: массив должен быть типа Integer[] или Double[].");
    }


    static <T extends Number & Comparable<T>> void printInfo(T[] array) {
        try {
            System.out.println("Массив чисел: " + Arrays.toString(array));
            System.out.println("Max: " + ArrayExtend.getMax(array));
            System.out.println("Min: " + ArrayExtend.getMin(array));
            System.out.println("Среднее: " + ArrayExtend.getAverage(array));

            if (array instanceof Integer[]) {
                Integer[] sortedAsc = ArrayExtend.sortArray((Integer[]) array);
                System.out.println(Arrays.toString(sortedAsc));
            } else if (array instanceof Double[]) {
                Double[] sortedAsc = ArrayExtend.sortArray((Double[]) array);
                System.out.println(Arrays.toString(sortedAsc));
            }

            System.out.println("Отсортированный массив по возрастанию:");
            System.out.println(Arrays.toString(SortedArray(array, true)));

            System.out.println("Отсортированный массив по убыванию:");
            System.out.println(Arrays.toString(SortedArray(array, false)));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }


}
