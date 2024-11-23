import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final String nombre;
    private String direccion;
    private String telefono;
    private final List<Habitacion> habitaciones;
    private final List<Reserva> reservas;

    public Hotel(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public boolean crearReserva(String cliente, int numeroHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numeroHabitacion && habitacion.isDisponible()) {
                Reserva nuevaReserva = new Reserva(cliente, habitacion, fechaInicio, fechaFin);
                reservas.add(nuevaReserva);
                habitacion.setDisponible(false); // Marcar como ocupada
                return true;
            }
        }
        return false; // No se encontró la habitación o no está disponible
    }

    public boolean consultarDisponibilidad(int numeroHabitacion) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numeroHabitacion) {
                return habitacion.isDisponible();
            }
        }
        return false; // Habitación no existe
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }
}
