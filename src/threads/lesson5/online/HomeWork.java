package threads.lesson5.online;

import java.util.Arrays;

public class HomeWork {
    /**
     * 1. Необходимо написать два метода, которые делают следующее:
     * <p>
     * 1) Создают одномерный длинный массив, например:
     * static final int size = 10000000;
     * static final int h = size / 2;
     * float[] arr = new float[size];
     * 2) Заполняют этот массив единицами;
     * 3) Засекают время выполнения: long a = System.currentTimeMillis();
     * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
     * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
     * 5) Проверяется время окончания метода System.currentTimeMillis();
     * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
     * Отличие первого метода от второго:
     * Первый просто бежит по массиву и вычисляет значения.
     * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
     * <p>
     * Пример деления одного массива на два:
     * System.arraycopy(arr, 0, a1, 0, h);
     * System.arraycopy(arr, h, a2, 0, h);
     * <p>
     * Пример обратной склейки:
     * System.arraycopy(a1, 0, arr, 0, h);
     * System.arraycopy(a2, 0, arr, h, h);
     * <p>
     * Примечание:
     * System.arraycopy() копирует данные из одного массива в другой:
     * System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
     * По замерам времени:
     * Для первого метода надо считать время только на цикл расчета:
     * for (int i = 0; i < size; i++) {
     * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
     * }
     * Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
     *
     */

    static final int size = 10000000;               // длина вычисляемого массива
    static final int h = size / 2;                  // длина массива для обработки в потоках

    public static void main(String[] args) {
        float[] arr = new float[size];
        float[] arr1 = new float[size];

        // расчет массива без потоков
        initArray(arr);
        long timeStart = System.currentTimeMillis();
        createArray(arr);
        System.out.println("Время работы без потоков, сек: " + (float) (System.currentTimeMillis() - timeStart) / 1000);

        //расчет массива в потоках
        initArray(arr1);
        timeStart = System.currentTimeMillis();
        createArrayThread(arr1);
        System.out.println("Время работы в 2 потока, сек: " + (float) (System.currentTimeMillis() - timeStart) / 1000);

        System.out.println("Массивы равны?: " + Arrays.equals(arr, arr1));
    }

    /**
     * инициализация массива 1
     * @param arr
     */
    public static void initArray(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
    }

    /**
     * метод вычисления массива без потока, последовательно
     * @param arr
     */
    public static void createArray(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    /**
     * метод вычисления массива в 2 потока
     * не стал создавать массив массовов для универсальности разбиения - h. т.к. при разбиение исходного масива на 3 части такой подход не сработает.
     * т.к. задача была понять суть потоков и работы со сслыками, то выполнил ее простым не универсальным способом
     * @param arr
     */
    public static void createArrayThread(float[] arr) {
        float[] a1 = new float[size];
        float[] a2 = new float[size];

        // бъем массив на части
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, h, h);

        // запускаем потоки на вычисление каждой части
        HomeWork.MyThread t0 = new HomeWork.MyThread("MyThread1", a1,0);
        HomeWork.MyThread t1 = new HomeWork.MyThread("MyThread2", a2, h);

        try {
            // ждем пока выполнятся потоки
            t0.join();
            t1.join();

            // соединяем массивы в один
            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, h, arr, h, h);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        float[] arr;
        int offset;

        MyThread(String name, float[] arr, int offset) {
            super(name);
            this.arr = arr;
            this.offset = offset;
            start();
        }

        @Override
        public void run() {
            for (int i = 0; i < h; i++) {
                arr[i+offset] = (float) (arr[i+offset] * Math.sin(0.2f + (i+offset) / 5) * Math.cos(0.2f + (i+offset) / 5) * Math.cos(0.4f + (i+offset) / 2));
            }
        }
    }

    /**
     * Метод распечатки массива в консоле
     * @param arr - выводимый массив
     */
    public static void printArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " | ");
        }
        System.out.println("");
    }
}
