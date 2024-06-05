package Ejercicio8;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulacionTeatro {
    public static void main(String[] args) {
        Teatro teatro = new Teatro(5);

        Runnable lector = () -> {
            for (int i = 0; i < 10; i++) {
                teatro.mostrarReservas();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable escritor = () -> {
            for (int i = 0; i < 10; i++) {
                int platea = (int) (Math.random() * 5);
                teatro.reservarPlatea(platea);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //teatro.cancelarReserva(platea);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(lector);
        executor.submit(escritor);
        executor.submit(lector);

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulación de reservas finalizada.");
    }
}
