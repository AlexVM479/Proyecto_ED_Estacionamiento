package Registro_Alex;

import java.time.LocalDate;
import java.time.LocalTime;

public class HistorialEstacionamiento {

    private String placa;
    private String tipoVehiculo;
    private String zona;
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    private int codigoConductor;
    private String nombreConductor;
    private String apellidosConductor;

    public HistorialEstacionamiento() {
    }

    public HistorialEstacionamiento(String placa, String tipoVehiculo, String zona,
                                    LocalDate fecha, LocalTime horaEntrada, LocalTime horaSalida,
                                    int codigoConductor, String nombreConductor, String apellidosConductor) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.zona = zona;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.codigoConductor = codigoConductor;
        this.nombreConductor = nombreConductor;
        this.apellidosConductor = apellidosConductor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getCodigoConductor() {
        return codigoConductor;
    }

    public void setCodigoConductor(int codigoConductor) {
        this.codigoConductor = codigoConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getApellidosConductor() {
        return apellidosConductor;
    }

    public void setApellidosConductor(String apellidosConductor) {
        this.apellidosConductor = apellidosConductor;
    }

    @Override
    public String toString() {
        return "HistorialEstacionamiento{" +
                "placa='" + placa + '\'' +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", zona='" + zona + '\'' +
                ", fecha=" + fecha +
                ", horaEntrada=" + horaEntrada +
                ", horaSalida=" + horaSalida +
                ", codigoConductor=" + codigoConductor +
                ", nombreConductor='" + nombreConductor + '\'' +
                ", apellidosConductor='" + apellidosConductor + '\'' +
                '}';
    }
}