package Ejercicio10;

class Cliente implements Runnable {  
    
    private String id;

    public Cliente(String id){  
        this.id = id;  
    }  

    

     public void run() {  
        System.out.println("Usuario: " + id+ " - Conectado");  
        conectadoStream();// Dejamos conectado el ciente tantos segundos como querramos 
        System.out.println("Usuario: " + id + " - Desconectado");
    }  
    private void conectadoStream() {  
        try {  Thread.sleep(3000);  } catch (InterruptedException e) { e.printStackTrace(); }  
    }  
}  