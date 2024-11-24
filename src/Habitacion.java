public class Habitacion {
    private int numero;
    private int personas;
    private double precioPorNoche;
    private boolean disponible;

    public Habitacion(int numero, int personas, double precioPorNoche) {
        this.numero = numero;
        this.disponible = true;
        this.personas = personas;
        this.precioPorNoche = precioPorNoche;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "Numero de Habitacion: " + numero +
                "\nCantidad de personas: " + personas +
                "\nPrecio por noche: " + precioPorNoche;
    }
}
