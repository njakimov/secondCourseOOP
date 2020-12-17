package interactive.lesson2;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Задание 2.1
        //На основе программного кода из домашнего задания №1 реализуйте массив на основе существующих примитивных или ссылочных типов данных.
        //Выполните обращение к массиву и базовые операции класса Arrays.
        //Оценить выполненные методы с помощью базового класса System.nanoTime().
        int[] intArray = new int[10];

        long timeStart = System.nanoTime();
        Arrays.fill(intArray, new Random().nextInt(50));
        System.out.println("Arrays.fill: " + (System.nanoTime() - timeStart));

        timeStart = System.nanoTime();
        System.out.println("intArray: " + Arrays.toString(intArray));
        System.out.println("Arrays.toString: " + (System.nanoTime() - timeStart));
        int[] intArr = {7, 6, 4, 5, 3, 2, 1, 0, 0, 1};

        timeStart = System.nanoTime();
        Arrays.sort(intArr);
        System.out.println("Arrays.sort: " + (System.nanoTime() - timeStart));
        System.out.println("intArray: " + Arrays.toString(intArr));

        timeStart = System.nanoTime();
        intArray = Arrays.copyOf(intArr, intArr.length);
        System.out.println("Arrays.copyOf: " + (System.nanoTime() - timeStart));
        System.out.println("intArray: " + Arrays.toString(intArray));

        //Задание 2.2
        //На основе написанного кода в задании 2.1 реализуйте линейный и двоичный поиск.
        //Оценить алгоритмы линейного и двоичного поиска с помощью базового класса System.nanoTime(), при необходимости расширьте уже существующий массив данных.

        timeStart = System.nanoTime();
        int idxSearch = Arrays.binarySearch(intArr, 3);
        System.out.println("Arrays.binarySearch: " + (System.nanoTime() - timeStart));
        System.out.println("idxSearch: " + idxSearch);

        idxSearch = -1;
        timeStart = System.nanoTime();
        for (int i = 0; i < intArr.length; i++) {
            if (intArr[i] == 3) {
                idxSearch = i;
                break;
            }
        }
        System.out.println("Arrays.linearSearch: " + (System.nanoTime() - timeStart));
        System.out.println("idxSearch: " + idxSearch);

        //Задание 2.3
        //
        //Создайте массив размером 400 элементов.
        //Выполните сортировку с помощью метода sort().
        //Оцените сортировку с помощью базового класса System.nanoTime().

        int[] intArr400 = new int[400];
        int[] intArrBubble = new int[400];
        int[] intArrCheck = new int[400];
        int[] intArrInsert = new int[400];
        for (int i = 0; i < intArr400.length; i++) {
            intArr400[i] = new Random().nextInt(50);
        }
        intArrBubble = Arrays.copyOf(intArr400,intArr400.length);
        intArrCheck = Arrays.copyOf(intArr400,intArr400.length);
        intArrInsert = Arrays.copyOf(intArr400,intArr400.length);

        timeStart = System.nanoTime();
        Arrays.sort(intArr400);
        System.out.println("Arrays.sort 400: " + (System.nanoTime() - timeStart));
        System.out.println("intArr400: " + Arrays.toString(intArr400));

        //Задание 2.4
        //На основе существующего массива данных из задания 2.3 реализуйте алгоритм сортировки пузырьком.
        //Оцените сортировку с помощью базового класса System.nanoTime().
        //Сравните время выполнения алгоритмы сортировки методом sort() из задания 2.1 и сортировку пузырьком.

        timeStart = System.nanoTime();
        int j;
        boolean flag = true;
        int temp;

        while (flag) {
            flag = false;
            for (j = 0; j < intArrBubble.length - 1; j++) {
                if (intArrBubble[j] > intArrBubble[j + 1]) {
                    temp = intArrBubble[j];
                    intArrBubble[j] = intArrBubble[j + 1];
                    intArrBubble[j + 1] = temp;
                    flag = true;
                }
            }
        }
        System.out.println("Bubble 400: " + (System.nanoTime() - timeStart));
        System.out.println("intArrBubble: " + Arrays.toString(intArrBubble));

        //Задание 2.5
        //На основе массива данных из задания 2.3 реализуйте алгоритм сортировки методом выбора.
        //Оцените сортировку с помощью базового класса System.nanoTime().
        //Сравните с временем выполнения алгоритмов сортировки из прошлых заданий 2.3 и 2.4.
        timeStart = System.nanoTime();
        for (int i = 0; i < intArrCheck.length; i++) {
            int min = intArrCheck[i];
            int minId = i;
            for (j = i + 1; j < intArrCheck.length; j++) {
                if (intArrCheck[j] < min) {
                    min = intArrCheck[j];
                    minId = j;
                }
            }
            // замена
            int temp1 = intArrCheck[i];
            intArrCheck[i] = min;
            intArrCheck[minId] = temp1;
        }
        System.out.println("Check 400: " + (System.nanoTime() - timeStart));
        System.out.println("intArrCheck: " + Arrays.toString(intArrCheck));
        //Задание 2.6
        //На основе массива данных из задания 2.3 реализуйте алгоритм сортировки методом вставки.
        //Оцените сортировку с помощью базового класса System.nanoTime().
        //Сравните с временем выполнения алгоритмов сортировки из прошлых заданий 2.3, 2.4 и 2.5.

        timeStart = System.nanoTime();
        for (int i = 1; i < intArrInsert.length; i++) {
            int current = intArrInsert[i];
            j = i - 1;
            while(j >= 0 && current < intArrInsert[j]) {
                intArrInsert[j+1] = intArrInsert[j];
                j--;
            }
            // в этой точке мы вышли, так что j так же -1
            // или в первом элементе, где текущий >= a[j]
            intArrInsert[j+1] = current;
        }
        System.out.println("Insert 400: " + (System.nanoTime() - timeStart));
        System.out.println("intArrInsert: " + Arrays.toString(intArrInsert));
    }
}
