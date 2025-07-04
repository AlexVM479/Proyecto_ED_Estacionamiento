package Registro_Alex;

public class Vehiculo {

    private String Placa;
    private String tipo;

    // Constructor por defecto
    public Vehiculo() {
    }

    // Constructor con parámetros
    public Vehiculo(String placa, String tipo) {
        this.Placa = placa;
        this.tipo = tipo;
    }

    // Getters y Setters
    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        this.Placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + Placa + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}