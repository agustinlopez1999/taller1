package Ejercicio1;

import java.util.Scanner;

public class Ejercicio1 {
    
    public static int leerNumero(){
        int num;
        @SuppressWarnings("resource")
        Scanner leer = new Scanner(System.in);
        num = leer.nextInt();
        return num;
    }

    public static void main(String[] args) {
        int partes;
        int sumaTotal = 0;
        System.out.println("Ingrese primer numero rango");
        int numero1 = leerNumero();
        System.out.println("Ingrese ultimo numero rango");
        int numero2 = leerNumero();
        partes = (numero2 - numero1)/3;
        System.out.println("Partes: " + partes);
        
        System.out.println("EJECUCION EN 1 SOLO HILO:");
        long tiempoInicio2 = System.currentTimeMillis();
        SumaClase Ejecucion1Hilo = new SumaClase(4, numero1, numero2);
        Ejecucion1Hilo.start();
        try{
            Ejecucion1Hilo.join();
            System.out.println("Suma de ejecucion de Un Hilo: " + Ejecucion1Hilo.getSuma());
            long tiempoFin2 = System.currentTimeMillis() - tiempoInicio2;
        System.out.println("Tiempo total q hil " + tiempoFin2 + " ms");
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        long tiempoInicio = System.currentTimeMillis();

        SumaClase hilo1 = new SumaClase(1,numero1,numero1 + partes);
        System.out.println("HILO 1 ("+ numero1 + " , " + (numero1 + partes) + ")");
        hilo1.start();

        numero1 += partes; 
        SumaClase hilo2 = new SumaClase(2, numero1 + 1, numero1 + partes);
        System.out.println("HILO 2 ("+ (numero1 + 1) + " , " + (numero1 + partes) + ")");
        hilo2.start();

        numero1 += partes;
        SumaClase hilo3 = new SumaClase(3, numero1 + 1 , numero2);
        System.out.println("HILO 3 ("+ (numero1 + 1) + " , " + numero2 + ")");
        hilo3.start();
        

        try{
            hilo1.join();
            System.out.println("Hilo 1: " + hilo1.getSuma());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        try{
            hilo2.join();
            System.out.println("Hilo 2: " + hilo2.getSuma());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        try{
            hilo3.join();
            System.out.println("Hilo 3: " + hilo3.getSuma());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        sumaTotal = hilo1.getSuma() + hilo2.getSuma() + hilo3.getSuma();

        long tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total MultiThread " + tiempoFin + " ms");

        System.out.println(" ");
        System.out.println("Suma Total: " + sumaTotal);
    }   
}