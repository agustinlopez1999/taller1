package Ejercicio3;
public final class Persona {
    private String nombre;
    private int edad;
    private String estadoCivil;

    private Persona(String n, int e, String eC){
        this.nombre = n;
        this.edad = e;
        this.estadoCivil = eC;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public int getEdad() {
        return edad;
    }

    public Persona cambiarEstadoCivil(String nuevoEstadoCivil) {
        return new Persona(this.nombre, this.edad, nuevoEstadoCivil);
    }


    public static void main(String[] args){
        Persona p1 = new Persona("Juan", 32, "Soltero");
        System.out.println(p1.getEstadoCivil());
        p1.cambiarEstadoCivil("Casado");
        System.out.println(p1.getEstadoCivil());

        Persona personaModificada = p1.cambiarEstadoCivil("Casado");
        System.out.println(personaModificada.getEstadoCivil());
    }
}
