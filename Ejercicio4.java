import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ejercicio4 {
    public static void main(String[] args) {
        int Max = 50;
        Random aleatorio = new Random();

        List<Integer> NumerosAleatorios = new ArrayList<>();
        for(int i = 0; i < Max; i++){
            NumerosAleatorios.add(aleatorio.nextInt(101));
        }
        // Vector
        long InicioTiempo = System.nanoTime();
        Vector<Integer> vector = new Vector<>(NumerosAleatorios);
        long FinTiempo = System.nanoTime();
        System.out.println("El Vector se crea en: " + (FinTiempo-InicioTiempo) + " nanosegundos");
        // ArrayList
        InicioTiempo = System.nanoTime();
        ArrayList<Integer> arraylist = new ArrayList<>(NumerosAleatorios);
        FinTiempo = System.nanoTime();
        System.out.println("El ArrayList se crea en: " + (FinTiempo-InicioTiempo) + " nanosegundos");
        // ArrayList sincronizado
        InicioTiempo = System.nanoTime();
        List<Integer> arraylistSincronizado = Collections.synchronizedList(new ArrayList<>(NumerosAleatorios));
        FinTiempo = System.nanoTime();
        System.out.println("El ArrayList Sincronizado se crea en: " + (FinTiempo-InicioTiempo) + " nanosegundos");
        // ConcurrentHashMap
        InicioTiempo = System.nanoTime();
        ConcurrentHashMap<Integer, Boolean> concurrentHashMap = new ConcurrentHashMap<>(); 
        for(Integer numero : NumerosAleatorios){
            concurrentHashMap.put(numero, true);
        }
        FinTiempo = System.nanoTime();
        System.out.println("El ConcurrentHashMap se crea en: " + (FinTiempo-InicioTiempo) + " nanosegundos");
        // CopyOnWriteArrayList
        InicioTiempo = System.nanoTime();
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(NumerosAleatorios);
        FinTiempo = System.nanoTime();
        System.out.println("El CopyOnWriteArrayList se crea en: " + (FinTiempo-InicioTiempo) + " nanosegundos");

        // Mediciones de Busquedas
        int NumeroEncontrar = NumerosAleatorios.get(aleatorio.nextInt(Max));
        // Busqueda en Vector
        InicioTiempo = System.nanoTime();
        boolean BusquedaVector = vector.contains(NumeroEncontrar);
        FinTiempo = System.nanoTime();
        System.out.println("El Vector busca en: " + (FinTiempo-InicioTiempo) + " nanosegundos");
        // Busqueda en Arraylist
        InicioTiempo = System.nanoTime();
        boolean BusquedaArrayList = arraylist.contains(NumeroEncontrar);
        FinTiempo = System.nanoTime();
        System.out.println("El Arraylist busca en: " + (FinTiempo-InicioTiempo) + " nanosegundos");
        // Busqueda en ArrayList Sincronizado
        InicioTiempo = System.nanoTime();
        boolean BusquedaArrayListSinc = arraylistSincronizado.contains(NumeroEncontrar);
        FinTiempo = System.nanoTime();
        System.out.println("El ArrayList Sincronizado busca en: " + (FinTiempo-InicioTiempo) + " nanosegundos");
        // Busqueda em ConcurrentHashMap
        InicioTiempo = System.nanoTime();
        boolean BusquedaConcurrentHashMap = concurrentHashMap.containsKey(NumeroEncontrar);
        FinTiempo = System.nanoTime();
        System.out.println("El ConcurrentHashMap busca en: " + (FinTiempo-InicioTiempo) + " nanosegundos");
        // CopyOnWriteArrayList
        InicioTiempo = System.nanoTime();
        boolean BusquedaCopyOnWriteArrayList = copyOnWriteArrayList.contains(NumeroEncontrar);
        FinTiempo = System.nanoTime();
        System.out.println("El CopyOnWriteArrayList busca en: " + (FinTiempo-InicioTiempo) + " nanosegundos");

        // Calculo Promedios
        double PromedioSinP = arraylist.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Promedio (Sin paralelismo): " + PromedioSinP);

        double PromedioConP = arraylist.parallelStream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Promedio (Con paralelismo): " + PromedioConP);
    }
    
}