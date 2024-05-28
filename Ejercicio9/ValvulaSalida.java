package Ejercicio9;

public class ValvulaSalida implements Runnable{
    private Tanque tanque;
    private int cantidad;

    public ValvulaSalida(Tanque t, int cant) {
        tanque = t;
        cantidad = cant;
    }

    @Override
    public void run() {
        try {
            while (true) {
                tanque.ExtraerAgua(cantidad);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
