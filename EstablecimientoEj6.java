import java.util.concurrent.Semaphore;

public class EstablecimientoEj6 {
    private static final int MAX_CAPACITY = 5;
    private static final Semaphore semaphore = new Semaphore(MAX_CAPACITY, true); // fair en true para q los hilos accedan en el orden solicitaod
    private static int contadorActual = 0;

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            int personaId = i;
            new Thread(() -> entraPersona(personaId)).start();
            new Thread(() -> salePersona(personaId)).start();
        }
    }

    private static void entraPersona(int personId) {
        try {
            semaphore.acquire(); // metodo para adquirir el semaforo
            synchronized (EstablecimientoEj6.class) {
                contadorActual++;
                System.out.println("Persona " + personId + " entra. Personas dentro: " + contadorActual);
            }
            Thread.sleep((long) (Math.random() * 6000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void salePersona(int personId) {
        try {
            Thread.sleep((long) (Math.random() * 6000));
            semaphore.release(); // libera
            synchronized (EstablecimientoEj6.class) {
                if (contadorActual > 0) {
                    contadorActual--;
                    System.out.println("Persona " + personId + " sale. Personas dentro: " + contadorActual);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
