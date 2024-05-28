package Ejercicio7;
import java.util.concurrent.CyclicBarrier;
import java.util.Scanner;

public class Ejercicio7 {

    public static int leerNumero() {
        int num;
        Scanner leer = new Scanner(System.in);
        num = leer.nextInt();
        return num;
    }

    public static void main(String[] args) {
        final int partes;
        int sumaTotal = 0;
        System.out.println("Ingrese primer numero rango");
        int numero1 = leerNumero();
        System.out.println("Ingrese ultimo numero rango");
        int numero2 = leerNumero();
        partes = (numero2 - numero1) / 3;
        System.out.println("Partes: " + partes);

        // Crear una barrera cíclica con 4 partes (hilos principales + 3 hilos de suma)
        CyclicBarrier barrier = new CyclicBarrier(4);

        // Obtengo tiempo inicio sistema
        long tiempoInicio = System.currentTimeMillis();

        // Hilo 1 (principal): responsable de iniciar los hilos de suma
        new Thread(() -> {
            System.out.println("HILO PRINCIPAL: iniciando hilos de suma");
            SumaClase2 hilo1 = new SumaClase2(1, numero1, numero1 + partes, barrier);
            SumaClase2 hilo2 = new SumaClase2(2, numero1 + partes + 1, numero1 + partes * 2, barrier);
            SumaClase2 hilo3 = new SumaClase2(3, numero1 + partes * 2 + 1, numero2, barrier);
            hilo1.start();
            hilo2.start();
            hilo3.start();
        
            try {
                barrier.await(); // Esperar a que los hilos de suma terminen
            } catch (Exception e) {
                e.printStackTrace();
            }
        
            int totalSum = hilo1.getSuma() + hilo2.getSuma() + hilo3.getSuma();
            long tiempoFin = System.currentTimeMillis() - tiempoInicio;
            System.out.println("Tiempo total MultiThread " + tiempoFin + " ms");
            System.out.println("Suma Total: " + totalSum);
        }).start();
    }
}

class SumaClase2 extends Thread {
    private final int id;
    private final int inicio;
    private final int fin;
    private int suma;
    private final CyclicBarrier barrier;

    public SumaClase2(int id, int inicio, int fin, CyclicBarrier barrier) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.suma = 0;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("HILO " + id + " (" + inicio + " , " + fin + ")");
        for (int i = inicio; i <= fin; i++) {
            suma += i;
        }
        try {
            barrier.await(); // Esperar a que todos los hilos terminen de sumar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSuma() {
        return suma;
    }
}
