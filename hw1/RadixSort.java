package hw1;

import java.util.Arrays;

public class RadixSort {

    public static Integer[] sort(Integer[] numbers) {
        int maximumNumber = findMaximumNumberIn(numbers);

        int numberOfDigits = calculateNumberOfDigitsIn(maximumNumber);

        int placeValue = 1;

        while (numberOfDigits-- > 0) {
            applyCountingSortOn(numbers, placeValue);
            placeValue *= 10;
        }

        return numbers;
    }

    public static Integer[] sortDescending (Integer[] numbers) {
        int maximumNumber = findMaximumNumberIn(numbers);

        int numberOfDigits = calculateNumberOfDigitsIn(maximumNumber);

        int placeValue = 1;

        while (numberOfDigits-- > 0) {
            applyCountingSortOnDescending(numbers, placeValue);
            placeValue *= 10;
        }

        return numbers;
    }

    private static void applyCountingSortOn(Integer[] numbers, int placeValue) {
        int range = 10; // radix or the base

        int length = numbers.length;
        int[] frequency = new int[range];
        Integer[] sortedValues = new Integer[length];

        for (int number : numbers) {
            int digit = (number / placeValue) % range;
            frequency[digit]++;
        }

        for (int i = 1; i < range; i++) {
            frequency[i] += frequency[i - 1];
        }

        for (int i = length - 1; i >= 0; i--) {
            int digit = (numbers[i] / placeValue) % range;
            sortedValues[frequency[digit] - 1] = numbers[i];
            frequency[digit]--;
        }

        System.arraycopy(sortedValues, 0, numbers, 0, length);
    }

    private static void applyCountingSortOnDescending (Integer[] numbers, int placeValue) {
        int range = 10; // radix or the base

        int length = numbers.length;
        int[] frequency = new int[range];
        Integer[] sortedValues = new Integer[length];

        for (int number : numbers) {
            int digit = (number / placeValue) % range;
            frequency[digit]++;
        }

        for (int i = range - 2; i >= 0; i--) {
            frequency[i] += frequency[i + 1];
        }

        for (int i = length - 1; i >= 0; i--) {
            int digit = (numbers[i] / placeValue) % range;
            sortedValues[frequency[digit] - 1] = numbers[i];
            frequency[digit]--;
        }

        System.arraycopy(sortedValues, 0, numbers, 0, length);
    }

    private static int calculateNumberOfDigitsIn(int number) {
        return (int) Math.log10(number) + 1; // valid only if number > 0
    }

    private static int findMaximumNumberIn(Integer[] arr) {
        return Arrays.stream(arr)
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Array is empty."));
    }

//    public static void main(String[] args) {
//        int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7};
//        RadixSort.sort(numbers);
//    }

}
