package sistema.de.viaje.aéreo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SistemaDeViajeAéreo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();
        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE VIAJE AEREO =====");
            System.out.println("1. Registrar pasajero");
            System.out.println("2. Comprar boleto de avion");
            System.out.println("3. Confirmacion de compra");
            System.out.println("4. Reporte de ventas");
            System.out.println("5. Salir del sistema");
            System.out.print("Elija una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del pasajero: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese apellido del pasajero: ");
                    String apellido = sc.nextLine();
                    System.out.print("Ingrese DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("Ingrese edad: ");
                    int edad;
                    try {
                        edad = Integer.parseInt(sc.nextLine());
                        if (edad <= 0) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.println("Edad invalida. Debe ser un numero positivo.");
                        break;
                    }
                    System.out.print("Ingrese direccion: ");
                    String direccion = sc.nextLine();

                    if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                        System.out.println("Nombre, apellido y DNI son obligatorios.");
                        break;
                    }

                    pasajeros.add(new Pasajero(nombre, apellido, dni, edad, direccion));
                    System.out.println("Pasajero registrado con exito.");
                    break;

                case 2:
                    if (pasajeros.isEmpty()) {
                        System.out.println("No hay pasajeros registrados. Registre uno primero.");
                        break;
                    }

                    System.out.println("Seleccione un pasajero:");
                    for (int i = 0; i < pasajeros.size(); i++) {
                        System.out.println((i + 1) + ". " + pasajeros.get(i).getNombreCompleto() + " (DNI: " + pasajeros.get(i).dni + ")");
                    }
                    System.out.print("Opcion: ");
                    int idxPasajero = sc.nextInt() - 1;
                    sc.nextLine();

                    if (idxPasajero < 0 || idxPasajero >= pasajeros.size()) {
                        System.out.println("Opcion de pasajero invalida.");
                        break;
                    }

                    Pasajero pasajeroSeleccionado = pasajeros.get(idxPasajero);

                    System.out.print("Origen: ");
                    String origen = sc.nextLine();
                    System.out.print("Destino: ");
                    String destino = sc.nextLine();
                    System.out.print("Costo del boleto: ");
                    double costo;
                    try {
                        costo = Double.parseDouble(sc.nextLine());
                        if (costo <= 0) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        System.out.println("Costo debe ser un numero positivo.");
                        break;
                    }
                    System.out.print("Asiento (ej. 12A): ");
                    String asiento = sc.nextLine();
                    if (asiento.isEmpty()) {
                        System.out.println("El asiento es obligatorio.");
                        break;
                    }
                    System.out.print("Lleva equipaje? (s/n): ");
                    String respEquipaje = sc.nextLine().toLowerCase();
                    boolean equipaje = respEquipaje.startsWith("s");

                    Venta nuevaVenta = new Venta(pasajeroSeleccionado, origen, destino, costo, asiento, equipaje);
                    ventas.add(nuevaVenta);
                    System.out.println("Boleto generado (pendiente de confirmacion).");
                    System.out.println("\nResumen preliminar:");
                    System.out.println(nuevaVenta.getResumen());
                    break;

                case 3:
                    ArrayList<Venta> pendientes = new ArrayList<>();
                    for (int i = 0; i < ventas.size(); i++) {
                        if (!ventas.get(i).confirmada) {
                            pendientes.add(ventas.get(i));
                        }
                    }

                    if (pendientes.isEmpty()) {
                        System.out.println("No hay compras pendientes de confirmacion.");
                        break;
                    }

                    System.out.println("Seleccione la compra a confirmar:");
                    for (int i = 0; i < pendientes.size(); i++) {
                        System.out.println((i + 1) + ". " + pendientes.get(i).pasajero.getNombreCompleto() +
                                " - " + pendientes.get(i).origen + " -> " + pendientes.get(i).destino);
                    }
                    System.out.print("Opcion: ");
                    int idxVenta = sc.nextInt() - 1;
                    sc.nextLine();

                    if (idxVenta < 0 || idxVenta >= pendientes.size()) {
                        System.out.println("Opcion invalida.");
                        break;
                    }

                    Venta ventaAConfirmar = pendientes.get(idxVenta);

                    System.out.println("Formas de pago:");
                    System.out.println("1. Efectivo");
                    System.out.println("2. Credito");
                    System.out.print("Seleccione: ");
                    String opcionPago = sc.nextLine();

                    switch (opcionPago) {
                        case "1":
                            ventaAConfirmar.formaPago = "Efectivo";
                            break;
                        case "2":
                            ventaAConfirmar.formaPago = "Credito";
                            break;
                        default:
                            System.out.println("Forma de pago no valida.");
                            break;
                    }

                    if (!ventaAConfirmar.formaPago.isEmpty()) {
                        ventaAConfirmar.confirmada = true;
                        System.out.println("Compra confirmada con exito!");
                    }
                    break;

                case 4:
                    System.out.println("\n--------------------------------------------------");
                    System.out.println("           REPORTE DE VENTAS CONFIRMADAS");
                    System.out.println("--------------------------------------------------");

                    boolean hayConfirmadas = false;
                    int contador = 0;

                    for (Venta v : ventas) {
                        if (v.confirmada) {
                            hayConfirmadas = true;
                            contador++;
                            System.out.println("\nVENTA #" + contador);
                            System.out.println(v.getResumen());
                            System.out.println("--------------------------------------------------");
                        }
                    }

                    if (!hayConfirmadas) {
                        System.out.println("No hay ventas confirmadas aun.");
                    } else {
                        System.out.println("TOTAL DE VENTAS CONFIRMADAS: " + contador);
                    }
                    break;

                case 5:
                    System.out.println("Gracias por usar el sistema! Hasta luego.");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }

            System.out.println(); // Espacio entre iteraciones

        } while (opcion != 5);

        sc.close();
    }
}