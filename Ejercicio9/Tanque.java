package Ejercicio9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tanque {
    private int CapacidadMax;
    private int CantidadActual;
    private final Lock lock = new ReentrantLock();
    private final Condition puedeAgregarAgua = lock.newCondition();
    private final Condition puedeExtraerAgua = lock.newCondition();

    public Tanque(int CapMax){
        CapacidadMax = CapMax;
        CantidadActual = 0;
    }

    public void AgregarAgua(int cantidad) throws InterruptedException{
        lock.lock();
        try {
            while (CantidadActual + cantidad > CapacidadMax) {
                puedeAgregarAgua.await(); //Espero, (No hay espacio para agregar)
            }
            CantidadActual += cantidad;
            System.out.println("Se agrego " + cantidad + " litros de agua. Cant. Actual: " + CantidadActual);
            puedeExtraerAgua.signalAll(); //Notificacion de que hay agua disponible para extraer
        } finally {
            lock.unlock();
        }
    }

    public void ExtraerAgua(int cantidad) throws InterruptedException{
        lock.lock();
        try {
            while (CantidadActual < cantidad) {
                puedeExtraerAgua.await(); // Espero, (No hay agua necesaria para extraer)
            }
            CantidadActual -= cantidad;
            System.out.println("Se extrajo " + cantidad + " litros de agua. Cant. actual: " + CantidadActual);
            puedeAgregarAgua.signalAll(); //Notificacion de que hay espacio disponible para agregar
        } finally {
            lock.unlock();
        }
    }
}
