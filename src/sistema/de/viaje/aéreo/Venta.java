package sistema.de.viaje.aéreo;

public class Venta {
    Pasajero pasajero;
    String origen;
    String destino;
    double costo;
    String asiento;
    boolean equipaje;
    double subtotal;
    double igv;
    double total;
    String formaPago;
    boolean confirmada;

    public Venta(Pasajero pasajero, String origen, String destino, double costo, String asiento, boolean equipaje) {
        this.pasajero = pasajero;
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
        this.asiento = asiento;
        this.equipaje = equipaje;
        this.subtotal = costo;
        this.igv = subtotal * 0.18;
        this.total = subtotal + igv;
        this.formaPago = "";
        this.confirmada = false;
    }

    public String getResumen() {
        return "Pasajero: " + pasajero.getNombreCompleto() + "\n" +
               "Ruta: " + origen + " -> " + destino + "\n" +
               "Asiento: " + asiento + "\n" +
               "Equipaje: " + (equipaje ? "Si" : "No") + "\n" +
               "Subtotal: S/" + String.format("%.2f", subtotal) + "\n" +
               "IGV (18%): S/" + String.format("%.2f", igv) + "\n" +
               "TOTAL: S/" + String.format("%.2f", total) + "\n" +
               "Forma de pago: " + formaPago;
    }
}