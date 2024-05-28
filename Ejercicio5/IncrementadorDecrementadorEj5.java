package Ejercicio5;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IncrementadorDecrementadorEj5 {
    private static final int N = 100;
    private static final AtomicInteger contadorIncremento = new AtomicInteger(0);
    private static final AtomicInteger contadorDecremento = new AtomicInteger(N);
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable incrementador = () -> {
            for (int i = 0; i < N; i++) {
                lock.lock();
                try {
                    contadorIncremento.incrementAndGet();
                    System.out.println("Incrementado a: " + contadorIncremento.get());
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable decrementador = () -> {
            for (int i = 0; i < N; i++) {
                lock.lock();
                try {
                    contadorDecremento.decrementAndGet();
                    System.out.println("Decrementado a: " + contadorDecremento.get());
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable observador = () -> {
            for (int i = 0; i < N; i++) {
                lock.lock();
                try {
                    System.out.println("Observador: Incremento = " + contadorIncremento.get() + ", Decremento = " + contadorDecremento.get());
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Thread hiloIncrementador = new Thread(incrementador);
        Thread hiloDecrementador = new Thread(decrementador);
        Thread hiloObservador = new Thread(observador);

        hiloIncrementador.setPriority(Thread.MIN_PRIORITY);
        hiloDecrementador.setPriority(Thread.MAX_PRIORITY);
        hiloObservador.setPriority(Thread.NORM_PRIORITY);

        executor.submit(hiloIncrementador);
        executor.submit(hiloDecrementador);
        executor.submit(hiloObservador);

        executor.shutdown(); // funcion que apaga al Executor
        while (!executor.isTerminated()) {
           // System.out.println("Esperando hilos");
        }

        System.out.println("Valor final de contadorIncremento: " + contadorIncremento.get());
        System.out.println("Valor final de contadorDecremento: " + contadorDecremento.get());
    }
}
