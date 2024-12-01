package hw1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Введите размер массива (или 0 для выхода):");
                int size = scanner.nextInt();

                if (size == 0) {
                    System.out.println("Выход из программы.");
                    break;
                }

                if (size <= 0) {
                    throw new InvalidArraySizeException("Размер массива должен быть натуральным числом.");
                }

                System.out.println("Введите минимально возможное число в массиве:");
                int min = scanner.nextInt();
                System.out.println("Введите максимально возможное число в массиве:");
                int max = scanner.nextInt();

                if (min >= max) {
                    throw new InvalidRangeException("Минимальное число не может превышать максимальное.");
                }

                System.out.println("Выберите 1 для int, 2 для double:");
                int choice = scanner.nextInt();

                if (choice != 1 && choice != 2) {
                    throw new InvalidChoiceException("Неверный ввод.");
                }

                if (choice == 1) {
                    Integer[] intArray = ArrayExtend.generateIntArray(size, min, max);
                    ArrayExtend.printInfo(intArray);

                } else {
                    Double[] doubleArray = ArrayExtend.generateDoubleArray(size, min, max);
                    ArrayExtend.printInfo(doubleArray);
                }

            } catch (InvalidArraySizeException | InvalidRangeException | InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
