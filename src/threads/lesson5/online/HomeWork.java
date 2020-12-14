package threads.lesson5.online;

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
     * @param args
     */

    static final int size = 10;
    static final int h = size / 2;
//    float[] arr = new float[size];
//static float[] a1 = new float[size/2];
//    static float[] a2 = new float[size/2];

    public static void main(String[] args) {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        System.out.println(arr.);
        long timeStart = System.currentTimeMillis();
        createArray(arr);
        System.out.println("Время работы без потоков, сек: " + (float) (System.currentTimeMillis() - timeStart) / 1000);
        System.out.println(arr);

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        System.out.println(arr);
        timeStart = System.currentTimeMillis();
        createArrayThread(arr);
        System.out.println("Время работы в 2 потока, сек: " + (float) (System.currentTimeMillis() - timeStart) / 1000);

        System.out.println(arr);
    }

    public static void createArray(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void createArrayThread(float[] arr) {
        float[] a1 = new float[size / 2];
        float[] a2 = new float[size / 2];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
//        Runnable r = HomeWork::calcArray;
//        new Thread(r, "Thread#0").start();
//        new Thread(r, "Thread#1").start();
        HomeWork.MyThread t0 = new HomeWork.MyThread("MyThread1", a1);
        HomeWork.MyThread t1 = new HomeWork.MyThread("MyThread1", a2);
        try {
            t0.join();
            t1.join();
            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, 0, arr, h, h);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized static void calcArray(float[] arr) {
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }


    static class MyThread extends Thread {

        float[] arr;

        MyThread(String name, float[] arr) {
            super(name);
            this.arr = arr;
            start();
        }

        @Override
        public void run() {
            for (int i = 0; i < size/2; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }
}
