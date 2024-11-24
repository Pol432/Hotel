import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private static int contadorId = 1; // Generador automático de IDs
    private final int idReserva;
    private String cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Habitacion habitacion;
    private double precioTotal;

    public Reserva(String cliente, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        // Validar fechas
        if (fechaInicio.isBefore(LocalDate.now()) || fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("Las fechas no son válidas.");
        }
        if (!habitacion.isDisponible()) {
            throw new IllegalArgumentException("La habitación seleccionada no está disponible.");
        }

        this.idReserva = contadorId++;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitacion = habitacion;

        // Marcar la habitación como no disponible
        habitacion.setDisponible(false);

        long noches = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        this.precioTotal = noches * habitacion.getPrecioPorNoche();
    }

    // Getters
    public int getIdReserva() {
        return idReserva;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    // Metodo para cancelar la reserva
    public void cancelarReserva() {
        this.habitacion.setDisponible(true); // Hacer la habitación disponible nuevamente
        System.out.println("La reserva ha sido cancelada.");
    }

    @Override
    public String toString() {
        return "Reserva ID: " + idReserva +
                "\nCliente: " + cliente +
                "\nHabitación Número: " + habitacion.getNumero() +
                "\nFecha Inicio: " + fechaInicio +
                "\nFecha Fin: " + fechaFin +
                "\nCosto Total: $" + precioTotal;
    }
}
