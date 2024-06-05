package Ejercicio9;

public class Main {
    public static void main(String[] args) {
        Tanque tanque = new Tanque(80);
        
        ValvulaEntrada valvulaE1 = new ValvulaEntrada(tanque, 2);
        ValvulaSalida valvulaS1 = new ValvulaSalida(tanque, 6);
        ValvulaEntrada valvulaE2 = new ValvulaEntrada(tanque, 3);
        ValvulaSalida valvulaS2 = new ValvulaSalida(tanque, 7);
        //Se pueden agregar mas...

        Thread hiloEntrada1 = new Thread(valvulaE1);
        Thread hiloSalida1 = new Thread(valvulaS1);
        Thread hiloEntrada2 = new Thread(valvulaE2);
        Thread hiloSalida2 = new Thread(valvulaS2);

        hiloEntrada1.start();
        hiloSalida1.start();
        hiloEntrada2.start();
        hiloSalida2.start();
    }
}
