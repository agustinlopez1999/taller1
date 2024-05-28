package Ejercicio1;
public class SumaClase extends Thread{

    private int k, n, id;
    
    public SumaClase(int id, int k, int n){
        this.id = id;
        this.k = k;
        this.n = n;
    }

    public int getSuma(){
        return k;
    }

    @Override
    public void run(){
        int suma = k;
        k++;
        /*long tiempoInicio = System.currentTimeMillis();*/
        while(k <= n){
            suma += k;
            k++;
            try {
                //System.out.println("Suma Thread "+id+ ": " + suma);
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        k = suma;
        

         /* 
        long tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del Tread "+id+ ": " + tiempoFin + " ms");*/
    }
}