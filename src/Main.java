import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("El Royal", "Quito, Ecuador", "+593 96 344 1176");
        Habitacion h1 = new Habitacion(100, 2, 20);
        Habitacion h2 = new Habitacion(101, 3, 35);
        Habitacion h3 = new Habitacion(200, 4, 40);
        Habitacion h4 = new Habitacion(201, 2, 20);

        hotel.agregarHabitacion(h1);
        hotel.agregarHabitacion(h2);
        hotel.agregarHabitacion(h3);
        hotel.agregarHabitacion(h4);

        Scanner in = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Listar las habitaciones
        System.out.println("Bienvenido al hotel " + hotel.getNombre() + "!\n");
        System.out.print("Ingrese su nombre: ");
        String nombre = in.nextLine();

        System.out.println("\n\nPor favor, eliga algunas de las siguientes habitaciones ingresando su numero: ");
        for (Habitacion h: hotel.getHabitaciones()) {
            if (h.isDisponible()) {
                System.out.println(h + "\n");
            }
        }

        // Seleccion de habitacion
        System.out.print("Numero de habitacion: ");
        int habSelec = in.nextInt();

        if (!hotel.consultarDisponibilidad(habSelec)) {
            System.out.println("Habitación no disponible");
            return;
        }

        // Cantidad de noches
        LocalDate fechaInicio = null;
        while (fechaInicio == null) {
            System.out.print("Por favor, ingrese una fecha en formato dd/MM/yyyy: ");
            String input = in.nextLine();
            try {
                fechaInicio = LocalDate.parse(input, formatter);
                System.out.println("Fecha ingresada correctamente: " + fechaInicio);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Inténtelo de nuevo.");
            }
        }

        LocalDate fechaFinal = null;
        while (fechaFinal == null) {
            System.out.print("Por favor, ingrese la fecha final en formato dd/MM/yyyy: ");
            String input = in.nextLine();
            try {
                fechaFinal = LocalDate.parse(input, formatter);
                // Validar que la fecha final sea posterior o igual a la fecha de inicio
                if (fechaFinal.isBefore(fechaInicio)) {
                    System.out.println("La fecha final no puede ser anterior a la fecha de inicio. Inténtelo de nuevo.");
                    fechaFinal = null; // Restablecer para seguir pidiendo
                } else {
                    System.out.println("Fecha final ingresada correctamente: " + fechaFinal);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Inténtelo de nuevo.");
            }
        }

        // Crear reserva
        Reserva r = hotel.crearReserva(nombre, habSelec, fechaInicio, fechaFinal);
        System.out.println("\nEstos son los detalles de su reserva: ");
        System.out.println(r);
    }
}