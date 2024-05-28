package Ejercicio10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;



public class Main {
    
    
    static int tamanioStream = 3;
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(tamanioStream); //Creamos un pool de 3 hilos 
        for (int i = 0; i < 10; i++) {  
            Runnable cliente = new Cliente(nombres.get(i));
            executor.execute(cliente);
        }  
        executor.shutdown();
        while (!executor.isTerminated()) {   }  
            System.out.println("Se terminaron todos los hilos (clientes)");  
    }

    static List<String> nombres = new ArrayList<>(Arrays.asList(
            "Jorge",
            "Bob",
            "Charlie",
            "Diana",
            "Jose",
            "Guille",
            "Santi",
            "Marta",
            "Ian",
            "Monica"
    ));
    
}




