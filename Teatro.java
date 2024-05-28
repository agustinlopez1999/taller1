import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Teatro {
    private final boolean[] plateas;
    private final Lock lock = new ReentrantLock();

    public Teatro(int numPlatea) {
        plateas = new boolean[numPlatea];
    }

    public void mostrarReservas() {
        lock.lock();
        try {
            System.out.println("Estado de las reservas:");
            for (int i = 0; i < plateas.length; i++) {
                System.out.println("Platea " + i + ": " + (plateas[i] ? "Reservada" : "Disponible"));
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean reservarPlatea(int numPlatea) {
        lock.lock();
        try {
            if (!plateas[numPlatea]) {
                plateas[numPlatea] = true;
                System.out.println("Platea " + numPlatea + " reservada.");
                return true;
            } else {
                System.out.println("Platea " + numPlatea + " ya está reservada.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean cancelarReserva(int numPlatea) {
        lock.lock();
        try {
            if (plateas[numPlatea]) {
                plateas[numPlatea] = false;
                System.out.println("Reserva de platea " + numPlatea + " cancelada.");
                return true;
            } else {
                System.out.println("Platea " + numPlatea + " no está reservada.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
}
