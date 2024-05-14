public class SumaClaseImplements implements Runnable{

    private int k, n, id;

    public SumaClaseImplements(int id, int k, int n){
        this.id = id;
        this.k = k;
        this.n = n;
    }

    public int getSuma(){
        return k;
    }

    @Override
    public void run() {
        int suma = k;
        k++;
        long tiempoInicio = System.currentTimeMillis();
        while(k <= n){
            suma += k;
            k++;
            try {
                //System.out.println("Suma Thread "+id+ ": " + suma);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        k = suma;
        

        long tiempoFin = System.currentTimeMillis() - tiempoInicio;
        System.out.println("Tiempo total del Tread "+id+ ": " + tiempoFin + " ms");
    }


}