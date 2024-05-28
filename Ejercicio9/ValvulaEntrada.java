package Ejercicio9;

public class ValvulaEntrada implements Runnable{
    private Tanque tanque;
    private int cantidad;

    public ValvulaEntrada(Tanque t, int cant){
        tanque = t;
        cantidad = cant;
    }

    @Override
    public void run() {
        try {
            while (true) {
                tanque.AgregarAgua(cantidad);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
