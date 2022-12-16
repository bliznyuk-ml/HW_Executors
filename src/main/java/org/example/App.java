package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Задание 1
 * Предположим, что два потока «t1» и «t2» обращаются к общему целому числу «x». Поток «t1» бесконечно увеличивает «x»,
 * а поток «t2» бесконечно печатает значение «x». То есть потоки работают в бесконечном цикле.
 * Однако «t1» не должно увеличивать «x» до тех пор, пока это значение «x» не будет напечатано «t2»,
 * а «t2» не должно печатать одно и то же значение «x» более одного раза. Определите классы для реализации «t1»,
 * «t2» и «x». Напишите соответствующие методы для этих классов.
 * <p>
 * Задание 2
 * <p>
 * Предположим, что общий двумерный массив чисел должен быть записан в общий одномерный массив.
 * Это должно быть выполнено с использованием 4 потоков, где каждый поток записывает один столбец двумерного массива
 * за раз в одномерный массив. Определите классы Java и основной метод для выполнения поставленной задачи.
 */
public class App {
    public static int x = 0;
    public static Object locker = new Object();

    public static void main(String[] args) {

        T1 t1 = new T1(x);
        T2 t2 = new T2(x);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
