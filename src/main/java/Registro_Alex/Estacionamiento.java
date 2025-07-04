package Registro_Alex;

public class Estacionamiento {
    private int id;
    private String lugar;
    private int estado; // 0: libre, 1: ocupado

    // Constructor
    public Estacionamiento(int id, String lugar, int estado) {
        this.id = id;
        this.lugar = lugar;
        this.estado = estado;
    }
    
    public Estacionamiento() {
    // Constructor vacío, útil si quieres crear un objeto sin pasar argumentos.
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}