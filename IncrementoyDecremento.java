public class IncrementoyDecremento {
    private static final int N = 100;
    private static int contadorIncremento = 0;
    private static int contadorDecremento = N;
    private static int ejecucionesIncremento = 0;
    private static int ejecucionesDecremento = 0;
    private static int ejecucionesObservador = 0;

    public static void main(String[] args) {
        Runnable incrementador = () -> {
            for (int i = 0; i < N; i++) {
                synchronized (IncrementoyDecremento.class) {
                    contadorIncremento++;
                    ejecucionesIncremento++;
                }
                System.out.println("Incrementado a: " + contadorIncremento);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable decrementador = () -> {
            for (int i = 0; i < N; i++) {
                synchronized (IncrementoyDecremento.class) {
                    contadorDecremento--;
                    ejecucionesDecremento++;
                }
                System.out.println("Decrementado a: " + contadorDecremento);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable observador = () -> {
            for (int i = 0; i < N; i++) {
                synchronized (IncrementoyDecremento.class) {
                    ejecucionesObservador++;
                }
                System.out.println("Observador: Incremento = " + contadorIncremento + ", Decremento = " + contadorDecremento);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hiloIncrementador = new Thread(incrementador);
        Thread hiloDecrementador = new Thread(decrementador);
        Thread hiloObservador = new Thread(observador);

        //prioridades a) termina primero el decrementador
        hiloIncrementador.setPriority(Thread.MIN_PRIORITY);
        hiloDecrementador.setPriority(Thread.MAX_PRIORITY);
        hiloObservador.setPriority(Thread.NORM_PRIORITY);

        hiloIncrementador.start();
        hiloDecrementador.start();
        hiloObservador.start();

        try {
            hiloIncrementador.join();
            hiloDecrementador.join();
            hiloObservador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final de contadorIncremento: " + contadorIncremento);
        System.out.println("Valor final de contadorDecremento: " + contadorDecremento);
        System.out.println("Ejecuciones totales del Incrementador: " + ejecucionesIncremento);
        System.out.println("Ejecuciones totales del Decrementador: " + ejecucionesDecremento);
        System.out.println("Ejecuciones totales del Observador: " + ejecucionesObservador);
    }
}

//Synchronized asegura la exclusión mutua