import java.util.Scanner;

public class threadsTest {
    //FUNCIONA SI DIFERENCIA ENTRE N Y K ES MULTIPLO DE 3
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

        System.out.println(" ");
        System.out.println("Suma Total: " + sumaTotal);

        System.out.println("EJECUCION EN 1 SOLO HILO:");
        SumaClase hilo4 = new SumaClase(4, 30, 51);
        hilo4.start();
        try{
            hilo4.join();
            System.out.println("Hilo 4: " + hilo4.getSuma());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        

        //SumaClase sumaParte1 = new SumaClase(1, 5, 7);
        //SumaClase sumaParte2 = new SumaClase(2, 8, 10);
        //SumaClase sumaTotal = new SumaClase(3,5,10);
        //SumaClaseImplements sumaTotal = new SumaClaseImplements(4, 8, 10);

        //sumaParte1.start();
        //sumaParte2.start();
        //sumaTotal.start();

        //try {
            //sumaParte1.join();
            //sumaParte2.join();
        //    sumaTotal.join();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
       //}

        //System.out.println("SUMA PARTE 1 (5,7) = " + sumaParte1.getSuma());
        //System.out.println("SUMA PARTE 2 (8,10) = " + sumaParte2.getSuma());
        //System.out.println("");
        //System.out.println("SUMA TOTAL (5,10) = " + sumaTotal.getSuma());
    }   
}