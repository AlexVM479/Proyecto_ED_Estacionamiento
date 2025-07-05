package Registro_Alex;

public class SolicitudIngreso {
    private Vehiculo vehiculo;
    private int codigoConductor;

    public SolicitudIngreso(Vehiculo vehiculo, int codigoConductor) {
        this.vehiculo = vehiculo;
        this.codigoConductor = codigoConductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getCodigoConductor() {
        return codigoConductor;
    }
}