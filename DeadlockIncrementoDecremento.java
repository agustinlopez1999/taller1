public class DeadlockIncrementoDecremento {
    private static final int N = 100;
    private static int contadorIncremento = 0;
    private static int contadorDecremento = N;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Runnable incrementador = () -> {
            for (int i = 0; i < N; i++) {
                System.out.println("Hilo incrementador intentando adquirir lock1");
                synchronized (lock1) {
                    System.out.println("Hilo incrementador adquirió lock1");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Hilo incrementador intentando adquirir lock2");
                    synchronized (lock2) {
                        System.out.println("Hilo incrementador adquirió lock2");
                        contadorIncremento++;
                        System.out.println("Incrementado a: " + contadorIncremento);
                    }
                    System.out.println("Hilo incrementador liberó lock2");
                }
                System.out.println("Hilo incrementador liberó lock1");
            }
        };

        Runnable decrementador = () -> {
            for (int i = 0; i < N; i++) {
                System.out.println("Hilo decrementador intentando adquirir lock2");
                synchronized (lock2) {
                    System.out.println("Hilo decrementador adquirió lock2");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Hilo decrementador intentando adquirir lock1");
                    synchronized (lock1) {
                        System.out.println("Hilo decrementador adquirió lock1");
                        contadorDecremento--;
                        System.out.println("Decrementado a: " + contadorDecremento);
                    }
                    System.out.println("Hilo decrementador liberó lock1");
                }
                System.out.println("Hilo decrementador liberó lock2");
            }
        };

        Thread hiloIncrementador = new Thread(incrementador);
        Thread hiloDecrementador = new Thread(decrementador);

        hiloIncrementador.start();
        hiloDecrementador.start();

        try {
            hiloIncrementador.join();
            hiloDecrementador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final de contadorIncremento: " + contadorIncremento);
        System.out.println("Valor final de contadorDecremento: " + contadorDecremento);
    }
}

//Synchronized con dos bloques que se bloquean mutuamente