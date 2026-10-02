package sistema.de.viaje.aéreo;

public class Pasajero {
    String nombre;
    String apellido;
    String dni;
    int edad;
    String direccion;

    public Pasajero(String nombre, String apellido, String dni, int edad, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.direccion = direccion;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}